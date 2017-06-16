/*
 * GameMainLoop.java
 *
 * Copyright 2016 Caique Jhones
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.solsticio.core.kernel;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class GameMainLoop implements GameLoop {
	
	private static final Logger log = LoggerFactory.getLogger(GameMainLoop.class);
	
	public static final int DEFAULT_UPS = 60;
	public static final int DEFAULT_NO_DELAYS_PER_YIELD = 16;
	public static final int DEFAULT_MAX_FRAME_SKIPS = 5;

	/**
	 * Ativa ou desetiva o loop principal.
	 */
	private volatile boolean active;

	private GameStep gameStep;

	private Counter counterUPS;
	private Counter counterFPS;
	
	//private long begin;

	private long desiredUpdateTime;
	private long afterTime;
	private long beforeTime = System.currentTimeMillis();
	private long overSleepTime = 0;
	private long excessTime = 0;
	private int noDelaysPerYield = DEFAULT_NO_DELAYS_PER_YIELD;
	private int maxFrameSkips = DEFAULT_MAX_FRAME_SKIPS;
	private int noDelays = 0;
		
	private boolean pause;
	
	public GameMainLoop(GameStep gameStep, int expectedTicks, int maxFrameSkips, int noDelaysPerYield) {
		if (expectedTicks < 1) throw new IllegalArgumentException("You must display at least one frame per second!");

		this.gameStep = gameStep;
		this.maxFrameSkips = maxFrameSkips;
		this.noDelaysPerYield = noDelaysPerYield;
		this.desiredUpdateTime = (long) (Counter.NANOS_IN_ONE / expectedTicks);

		counterUPS = new Counter();
		counterFPS = new Counter();
	}

	public GameMainLoop(GameStep gameStep) {
		this(gameStep, DEFAULT_UPS);
	}

	public GameMainLoop(GameStep gameStep, int expectedTicks) {
		this(gameStep, expectedTicks, DEFAULT_MAX_FRAME_SKIPS, DEFAULT_NO_DELAYS_PER_YIELD);
	}

	private void sleep(long nanos) {
		try {
			noDelays = 0;
			long beforeSleep = System.nanoTime();
			TimeUnit.NANOSECONDS.sleep(nanos);
			overSleepTime = System.nanoTime() - beforeSleep - nanos;
		} catch (Exception e) {
		}
	}

	private void yieldIfNeed() {
		if (++noDelays == noDelaysPerYield) {
			Thread.yield();
			noDelays = 0;
		}
	}

	private long calculateSleepTime() {
		return desiredUpdateTime - (afterTime - beforeTime) - overSleepTime;
	}

	private void updateLogic() {
		counterUPS.increment();
		int frequency = counterUPS.getFrequency();
		double delta = frequency == 0 ? 0 : 1 / frequency;
		gameStep.updateLogic(delta);
	}

	private void renderGraphics() {
		counterFPS.increment();
		gameStep.renderGraphics();
		gameStep.paintGraphics();
	}

	private void skipFramesInExcessTime() {
		int skips = 0;
		while ((excessTime > desiredUpdateTime) && (skips < maxFrameSkips)) {
			excessTime -= desiredUpdateTime;
			updateLogic();
			skips++;
		}
	}
	
	@Override
	public void run() {
		log.info("Solstice Appliation started!");
		active = true;
		//begin = System.currentTimeMillis();
		try {
			gameStep.setup(this);
			while (active) {		
				if (pause) continue;
				
				beforeTime = System.nanoTime();
				skipFramesInExcessTime();

				updateLogic();
				renderGraphics();

				afterTime = System.nanoTime();
				long sleepTime = calculateSleepTime();
				if (sleepTime >= 0) sleep(sleepTime);
				else {
					excessTime -= sleepTime; // Sleep time is negative
					overSleepTime = 0L;
					yieldIfNeed();
				}
			}
		} finally {
			active = false;
			gameStep.terminate();
			log.info("Solstice Application finalized!");
		}
	}

	@Override
	public void finish() {
		active = false;
	}

	@Override
	public void pause() {
		pause = true;
	}

	@Override
	public void resume() {
		pause = false;
	}

	@Override
	public boolean isPause() {
		return pause;
	}

	@Override
	public int getUPS() {
		return counterUPS.getFrequency();
	}

	@Override
	public int getFPS() {
		return counterFPS.getFrequency();
	}
}
