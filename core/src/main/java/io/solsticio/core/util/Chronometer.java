/*
 * Chronometer.java
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
package io.solsticio.core.util;

import java.io.Serializable;

/**
 * <p>
 * Representa um Cronômetro básico, porém <strong>diferente</strong> do
 * tradicional, aqui é utilizado a frenquência em que o método
 * {@link #update(double)} é executado para calcular os segundos, minutos e horas,
 * ou seja, não representa dados reais de tempo, por isso não é aconselhável a
 * utilização desta classe quando o objetivo for ter exatidão do tempo.
 * </p>
 * <p>
 * A justificativa de utilização desta classe é ter um cronômetro que se basei
 * na taxa de atualização da aplicação, para isso chame o método
 * {@link #update(double)} dentre de atualições contínuas.
 * </p>
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class Chronometer implements Serializable {

    private static final long serialVersionUID = 1L;
    private double time;

    public Chronometer() {
    }

    public Chronometer(double time) {
        this.time = time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public void update(double delta) {
        time += delta;
    }

    public int getSeconds() {
        return (int) time % 60;
    }

    public int getMinutes() {
        return (int) (time / 60 % 60);
    }

    public int getHours() {
        return (int) (time / 3600);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", getHours(), getMinutes(), getSeconds());
    }
}
