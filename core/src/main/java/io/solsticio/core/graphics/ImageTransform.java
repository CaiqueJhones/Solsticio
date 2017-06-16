/*
 * ImageTransform.java
 *
 * Copyright 2016 Caique Jhones
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
package io.solsticio.core.graphics;

/**
 * Transformações para imagens.
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public interface ImageTransform {

    /**
     * Rotaciona a imagem
     * 
     * @param src
     *            Imagem para rotação.
     * @param angle
     *            Ângulo de rotação em radianos.
     * @param locationX
     *            Ponto de rotação em x.
     * @param locationY
     *            Ponto de rotação em y.
     * @return Uma imagem rotacionada.
     */
    Image rotate(Image src, double angle, double locationX, double locationY);

    /**
     * Espelha a imagem horizontalmente.
     * 
     * @param src
     *            A imagem para espelhar.
     * @return A imagem espelhada.
     */
    Image flipHorizontal(Image src);

    /**
     * Espelha a imagem verticalmente.
     * 
     * @param src
     *            A imagem para espelhar.
     * @return A imagem espelhada.
     */
    Image flipVertical(Image src);

    /**
     * Espelha a imagem verticalmente e horizontalmente.
     * 
     * @param src
     *            A imagem para espelhar.
     * @return A imagem espelhada.
     */
    Image flip(Image src);

    /**
     * Faz escala da imagem.
     * 
     * @param src
     *            A imagem a ser escalada.
     * @param dx
     *            Escala em x.
     * @param dy
     *            Escala em y.
     * @return A imagem escalada.
     */
    Image scale(Image src, double dx, double dy);
}
