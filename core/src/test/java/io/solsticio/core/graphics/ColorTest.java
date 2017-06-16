package io.solsticio.core.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ColorTest {
	
	Color color;
	
	@Before
	public void up() {
		color = new Color(100, 200, 255, 100);
	}
	
	@Test
	public void testColor() {
		Color color = new Color(77,208,159,0);
		assertEquals(0x4dd09f, color.getARGB());
		
		System.out.println("Construtor RGBA: "+color.toString());
	}
	
	@Test
	public void testColor2() {
		Color color = new Color(0xFFFF0000);
		assertEquals(0xFFFF0000, color.getARGB());
		
		System.out.println("Construtor Hexa: "+color.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testColorWithError() {
		new Color(300, 0, 0, 300);
	}
	
	@Test
	public void testGetRed() {
		assertEquals(100, color.getRed());
	}

	@Test
	public void testGetGreen() {
		assertEquals(200, color.getGreen());
	}

	@Test
	public void getBlue() {
		assertEquals(255, color.getBlue());
	}

	@Test
	public void getAlpha() {
		assertEquals(100, color.getAlpha());
	}

	@Test
	public void getARGB() {
		Color color = new Color(77,208,159,16);
		assertEquals(0x104dd09f, color.getARGB());
	}

	@Test
	public void equals() {
		assertTrue(color.equals(new Color(100, 200, 255, 100)));
	}

}
