/*
 * AudioManager.java
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

import io.solsticio.core.media.Music;
import io.solsticio.core.media.Sound;

/**
 * A classes que implementarem esta interface terá o objetivo de criar
 * {@link Music} e {@link Sound}
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public interface AudioManager {

    /**
     * Cria ou carrega uma som.
     * 
     * @param fileName
     *        nome do arquivo (ou stream) do som.
     * @return um som.
     */
    Sound createSound(String fileName);

    /**
     * Cria ou carrega uma música.
     * 
     * @param fileName
     *        nome do arquivo (ou stream) da música.
     * @return uma música.
     */
    Music createMusic(String fileName);

}
