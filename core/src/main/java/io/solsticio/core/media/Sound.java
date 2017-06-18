/*
 * Sound.java
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
package io.solsticio.core.media;

/**
 * Representa um som. Diferente da interface {@link Music}, um som é curto e
 * possui somente o gatilho de iniciar. Exemplos de sons são: um tiro de uma
 * arma, barulho de gotas de água caindo no solo, batida em portas e etc.
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public interface Sound {

    /**
     * Inicia a execução do som.
     * 
     * @param volume
     *        volume que o som será executado, valor de 0 a 100.
     */
    void play(int volume);

    /**
     * Descarrega o som.
     */
    void dispose();
}
