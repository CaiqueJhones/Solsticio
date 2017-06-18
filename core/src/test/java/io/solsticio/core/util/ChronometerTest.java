package io.solsticio.core.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChronometerTest {
	
	private Chronometer c;
	private final double delta = 1d / 60d;
	
	@Before
	public void setUp() {
		c = new Chronometer();
	}

	@Test
	public void calculatesSeconds() {
		for (int i = 0; i < 90; i++) {
            c.update(delta);
        }
		assertEquals(1, c.getSeconds());
	}
	
	@Test
	public void calculatesMinutes() {
	    for (int i = 0; i < 3650; i++) {
            c.update(delta);
        }
		assertEquals(1, c.getMinutes());
	}
	
	@Test
	public void calculatesHours() {
	    c.setTime(60 * 60);
		assertEquals(1, c.getHours());
	}
	
	@Test
	public void testToString() throws Exception {
		c.setTime(2 * 60 * 60);
		assertEquals("02:00:00", c.toString());
	}
}
