/*
 * Counter.java
 *
 * Copyright 2016 Caique Jhones
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.solsticio.core.kernel;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class Counter {

	/**
	 * Quantidade de nanos segundos em um segundo.
	 */
	public static final double NANOS_IN_ONE = 1e9;

	private int frequency;
	private long previous;
	private int counted;

	private void update() {
		if (previous == 0) previous = System.nanoTime();

		if (System.nanoTime() - previous > NANOS_IN_ONE) {
			frequency = counted;
			counted = 0;
			previous = System.nanoTime();
		}
	}

	/**
	 * Incrementa o contador e retorna o seu valor.
	 * 
	 * @return o valor do contador.
	 */
	public int increment() {
		counted++;
		update();
		return counted;
	}

	/**
	 * Recupera o valor da frequência por segundo com base no contador.
	 * 
	 * @return o valor da frequência por segundo com base no contador.
	 */
	public int getFrequency() {
		return frequency;
	}
}
