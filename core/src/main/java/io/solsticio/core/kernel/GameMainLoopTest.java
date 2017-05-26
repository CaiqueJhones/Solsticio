package io.solsticio.core.kernel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class GameMainLoopTest {

	private GameStep gameStep;

	@Before
	public void setUp() throws Exception {
		gameStep = mock(GameStep.class);
	}

	@Test
	public void verifyStepMethods() throws Exception {
		GameLoop gameLoop = new GameMainLoop(gameStep);
		new Thread(gameLoop).start();

		try {
			verify(gameStep).setup(gameLoop);
			verify(gameStep, atLeastOnce()).updateLogic(anyLong());
			verify(gameStep, atLeastOnce()).renderGraphics();
			verify(gameStep, atLeastOnce()).paintGraphics();
		} finally {
			gameLoop.finish();
			Thread.sleep(1000);
			verify(gameStep).terminate();
		}
	}

	@Test
	public void respectLimitOfTPS() throws Exception {
		GameLoop gameLoop = new GameMainLoop(new MockGameStep(), 60);
		new Thread(gameLoop).start();
		Thread.sleep(2000);
		try {
			for (int i = 0; i < 10; i++) {
				assertEquals(60, gameLoop.getUPS(), 1);
			}
		} finally {
			gameLoop.finish();
		}
	}

	@Test
	public void pauseLoop() throws Exception {
		GameLoop solstice = new GameMainLoop(gameStep, 60);
		new Thread(solstice).start();
		solstice.pause();
		try {
			assertTrue(solstice.isPause());
			TimeUnit.SECONDS.sleep(2);
			assertEquals(0, solstice.getUPS(), 1);
		} finally {
			solstice.finish();
		}
	}

	/**
	 * Mock do comportamento da interface GameStep.
	 */
	private class MockGameStep implements GameStep {

		@Override
		public void setup(GameLoop gameLoop) {
			try {
				TimeUnit.NANOSECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void updateLogic(long timeInMillis) {
			try {
				TimeUnit.NANOSECONDS.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void renderGraphics() {
			try {
				TimeUnit.NANOSECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void paintGraphics() {
			try {
				TimeUnit.NANOSECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void terminate() {
			try {
				TimeUnit.NANOSECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
