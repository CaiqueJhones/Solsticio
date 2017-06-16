/*
 * Color.java
 */
package io.solsticio.core.graphics;

import java.io.Serializable;

/**
 * Representação básica de uma Cor em ARGB. Esta classe guardará o valor da cor
 * em um inteiro e dever ser utilizado em conjuto outra classe de cor da API de
 * implementação de interface gráfica (Java2D, OpenGL, Android, JavaFX, HTML5).
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class Color implements Serializable {

	public static final Color WHITE = new Color(255, 255, 255);

	public static final Color BLACK = new Color(0, 0, 0);

	public static final Color RED = new Color(255, 0, 0);

	public static final Color BLUE = new Color(0, 0, 255);

	public static final Color GREEN = new Color(0, 255, 0);

	public final static Color LIGHTGRAY = new Color(192, 192, 192);

	public final static Color GRAY = new Color(128, 128, 128);

	public final static Color DARK_GRAY = new Color(64, 64, 64);

	public final static Color PINK = new Color(255, 175, 175);

	public final static Color ORANGE = new Color(255, 200, 0);

	public final static Color YELLOW = new Color(255, 255, 0);

	public final static Color MAGENTA = new Color(255, 0, 255);

	public final static Color CYAN = new Color(0, 255, 255);

	public final static Color TRANSPARENT = new Color(0, 0, 0, 0);

	/**
	 * SeriaVersion gerado pelo JDK.
	 */
	private static final long serialVersionUID = -9186808467804064399L;

	private static final double FACTOR = 0.7;

	/**
	 * Armazena o valor da cor em um número hexadecimal.
	 */
	private int value;

	/**
	 * Cria uma representação de cor RGBA com valores entre 0-255.
	 * 
	 * @param r
	 *            valor da componente RED (0-255)
	 * @param g
	 *            valor da componente GREEN (0-255)
	 * @param b
	 *            valor da componente BLUE (0-255)
	 * @param a
	 *            valor da opacidade ALPHA (0-255)
	 * @throws IllegalArgumentException
	 *             caso o valor passado de alguma compoente esteja fora do range
	 *             (0-255).
	 * @since 1.0
	 */
	public Color(int r, int g, int b, int a) {
		testColorValueRange(r, g, b, a);
		value = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF) << 0);
	}

	/**
	 * Cria uma representa��o de cor RGBA com valores entre 0-255. Neste
	 * construtor a componente ALPHA � m�xima (255).
	 * 
	 * @param r
	 *            valor da componente RED (0-255)
	 * @param g
	 *            valor da componente GREEN (0-255)
	 * @param b
	 *            valor da componente BLUE (0-255)
	 * @throws IllegalArgumentException
	 *             caso o valor passado de alguma compoente esteja fora do range
	 *             (0-255).
	 * @since 1.0
	 */
	public Color(int r, int g, int b) {
		this(r, g, b, 255);
	}

	/**
	 * Cria uma representa��o de cor RGBA com um valor Hexadecimal.
	 * 
	 * @param rgb
	 *            valor Hexadecimal da cor no formato ARGB, exemplo: 0xFF00AAFF
	 *            <ul>
	 *            <li>0x<strong>FF</strong>00AAFF - ALPHA</li>
	 *            <li>0xFF<strong>00</strong>AAFF - RED</li>
	 *            <li>0xFF00<strong>AA</strong>FF - GREEN</li>
	 *            <li>0xFF00AA<strong>FF</strong> - BLUE</li>
	 *            </ul>
	 */
	public Color(int rgb) {
		value = 0xff000000 | rgb;
	}

	private static void testColorValueRange(int r, int g, int b, int a) {
		boolean rangeError = false;
		String badComponentString = "";

		if (a < 0 || a > 255) {
			rangeError = true;
			badComponentString = badComponentString + " Alpha";
		}
		if (r < 0 || r > 255) {
			rangeError = true;
			badComponentString = badComponentString + " Red";
		}
		if (g < 0 || g > 255) {
			rangeError = true;
			badComponentString = badComponentString + " Green";
		}
		if (b < 0 || b > 255) {
			rangeError = true;
			badComponentString = badComponentString + " Blue";
		}
		if (rangeError) { throw new IllegalArgumentException(
				"Color parameter outside of expected range:" + badComponentString); }
	}

	/**
	 * Returns the red component in the range 0-255 in the default sRGB space.
	 * 
	 * @return the red component.
	 * @see #getARGB
	 */
	public int getRed() {
		return (getARGB() >> 16) & 0xFF;
	}

	/**
	 * Returns the green component in the range 0-255 in the default sRGB space.
	 * 
	 * @return the green component.
	 * @see #getARGB
	 */
	public int getGreen() {
		return (getARGB() >> 8) & 0xFF;
	}

	/**
	 * Returns the blue component in the range 0-255 in the default sRGB space.
	 * 
	 * @return the blue component.
	 * @see #getARGB
	 */
	public int getBlue() {
		return (getARGB() >> 0) & 0xFF;
	}

	/**
	 * Returns the alpha component in the range 0-255.
	 * 
	 * @return the alpha component.
	 * @see #getARGB
	 */
	public int getAlpha() {
		return (getARGB() >> 24) & 0xff;
	}

	/**
	 * Retorna a representa��o da cor em um n�mero hexadecimal ARGB.
	 * 
	 * @return a representa��o da cor em um n�mero hexadecimal ARGB.
	 */
	public int getARGB() {
		return value;
	}

	public Color invert() {
		return new Color(255 - getRed(), 255 - getGreen(), 255 - getBlue(), getAlpha());
	}

	public Color darker() {
		return new Color((int) (getRed() * FACTOR), (int) (getGreen() * FACTOR),
				(int) (getBlue() * FACTOR), getAlpha());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Color)) return false;
		Color other = (Color) obj;
		return value == other.value;
	}

	/**
	 * Retorna a representa��o da cor em RGBA.
	 */
	@Override
	public String toString() {
		int r = getRed();
		int g = getGreen();
		int b = getBlue();
		int a = getAlpha();

		return String.format("Color {Red = %d, Green = %d, Blue = %d, Alpha = %d, Hexa = #%X}", r, g, b, a, value);
	}
}
