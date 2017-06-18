/*
 * Music.java
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
 * Representa uma música.
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public interface Music {

    /**
     * Inicia a reproduçao da música.
     */
    void play();

    /**
     * Pausa a execução da música, caso esteja executando.
     */
    void pause();

    /**
     * Pausa a musica e finaliza a execução, a música será colocada no seu ponto
     * inicial.
     */
    void stop();

    /**
     * Define se a música será tocada em um loop infinito, ou seja, após o seu
     * termino a execucão volta para o momento inicial.
     * 
     * @param loop
     *        define se a música ficará em loop ou não.
     */
    void setLooping(boolean loop);

    /**
     * Define o volume de execucão da música, esse valor pode variar entre 0 e
     * 100.
     * 
     * @param volume
     *        o valor do volume da música entre 0 e 100.
     */
    void setVolume(int volume);

    /**
     * Verifica se a música está tocando.
     * 
     * @return <code>true</code> se a música estiver tocando, <code>false</code>
     *         caso contrário.
     */
    boolean isPlaying();

    /**
     * Verifica se a música foi interrompida.
     * 
     * @return <code>true</code> se a música estiver interrompida,
     *         <code>false</code> caso contrário.
     */
    boolean isStopped();

    /**
     * Verifica se a música está em loop.
     * 
     * @return <code>true</code> se a música estiver em loop, <code>false</code>
     *         caso contrário.
     */
    boolean isLooping();

    /**
     * Descarrega da memória a música e todas as suas dependências.
     */
    void dispose();

}
