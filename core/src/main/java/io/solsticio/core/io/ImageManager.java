/*
 * ImageManager.java
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
package io.solsticio.core.io;

import java.io.InputStream;

import io.solsticio.core.graphics.Image;

/**
 * Gerenciador de imagens. Implementações desta interface devem ser responsáveis
 * por carregar imagens, estas podem vim do disco local, de um socket, dados em
 * bytes, criação dinâmica por meio de GUI's ou outra forma.
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public interface ImageManager {

    /**
     * Cria ou ou carrega uma imagem.
     * 
     * @param fileName
     *        nome da imagem.
     * @return uma imagem.
     */
    Image createImage(String fileName);

    /**
     * Carrega uma imagem a partir de uma stream de dados.
     * 
     * @param stream
     *        stream de dados contendo a imagem a ser criada.
     * @return uma imagem.
     */
    Image createImage(InputStream stream);

}
