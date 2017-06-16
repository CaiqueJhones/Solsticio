/*
 * Image.java
 */
package io.solsticio.core.graphics;

/**
 * @author Caique Jhones
 */
public interface Image {

	enum ImageFormat {
		ARGB8888, RGB565, UNKNOWN
	}

	int getWidth();

	int getHeight();

	ImageFormat getFormat();

	void dispose();

	/**
	 * Retorna um pedaço da imagem especificado pelos parâmetros.
	 * 
	 * @param x
	 *        posição <code>x</code> dentro desta imagem.
	 * @param y
	 *        posição <code>y</code> dentro desta imagem.
	 * @param width
	 *        altura da imagem.
	 * @param height
	 *        largura da imagem.
	 * @return uma nova imagem.
	 */
	Image subImage(int x, int y, int width, int height);
}
