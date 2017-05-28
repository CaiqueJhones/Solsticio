package io.solsticio.core.kernel;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class CounterTest {

	private Counter counter;

	@Before
	public void setUp() throws Exception {
		counter = new Counter();
	}
	
	@Test
	public void countFrequencyWith10() throws Exception {
		long begin = System.nanoTime();
		while ((System.nanoTime() - begin) <= TimeUnit.SECONDS.toNanos(2L)) {
			counter.increment();
			Thread.sleep(100);
		}
		log("countFrequencyWith10", System.nanoTime() - begin);
		
		assertEquals(10, counter.getFrequency(), 3);
	}
	
	@Test
	public void countFrequencyWith20() throws Exception {
		long begin = System.nanoTime();
		while ((System.nanoTime() - begin) <= TimeUnit.SECONDS.toNanos(2L)) {
			counter.increment();
			Thread.sleep(50);
		}
		log("countFrequencyWith20", System.nanoTime() - begin);
		
		assertEquals(20, counter.getFrequency(), 3);
	}

	@Test
	public void countFrequencyWith30() throws Exception {
		long begin = System.nanoTime();
		long sleep = (long) (Counter.NANOS_IN_ONE / 30);
		while ((System.nanoTime() - begin) <= TimeUnit.SECONDS.toNanos(2L)) {
			counter.increment();
			TimeUnit.NANOSECONDS.sleep(sleep);
		}
		log("countFrequencyWith30", System.nanoTime() - begin);
		
		assertEquals(30, counter.getFrequency(), 3);
	}
	
	private void log(String methodName, long nanoTime) {
		String log = String.format("%s executado em %d segundos.", methodName, TimeUnit.NANOSECONDS.toSeconds(nanoTime));
		System.out.println(log);
	}
}
