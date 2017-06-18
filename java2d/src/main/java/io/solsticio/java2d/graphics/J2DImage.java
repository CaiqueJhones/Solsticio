/*
 * J2DImage.java
 *
 * Copyright 2017 Caique Jhones
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.solsticio.java2d.graphics;

import java.awt.image.BufferedImage;

import io.solsticio.core.graphics.Image;

/**
 * Implementação de imagem para Java2D. Aqui é encapsulado a classe
 * {@link BufferedImage}.
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class J2DImage implements Image {

    private BufferedImage image;
    private ImageFormat format;

    public J2DImage(int width, int height, ImageFormat format) {
        this.format = format;
        switch (format) {
            case ARGB8888:
                image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                break;
            case RGB565:
                image = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_565_RGB);
                break;
            case UNKNOWN:
                throw new IllegalArgumentException("Formato inválido!");
        }
    }

    public J2DImage(BufferedImage image) {
        this.image = image;
        int type = image.getType();
        switch (type) {
            case BufferedImage.TYPE_INT_ARGB:
                format = ImageFormat.ARGB8888;
                break;
            case BufferedImage.TYPE_USHORT_565_RGB:
                format = ImageFormat.RGB565;
                break;
            default:
                format = ImageFormat.UNKNOWN;
                break;
        }
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public ImageFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        image.flush();
    }

    @Override
    public Image subImage(int x, int y, int width, int height) {
        return new J2DImage(image.getSubimage(x, y, width, height));
    }

    public BufferedImage getImage() {
        return image;
    }
}
