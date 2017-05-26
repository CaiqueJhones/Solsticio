/*
 * GameLoop.java
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
 * Gerencia o Loop principal da aplicação.
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public interface GameLoop extends Runnable {

	/**
	 * Deve ser invocado para finalizar o ciclo de vida da aplicaçao e assim encerrar todas as
	 * atividades de maneira adequada.
	 */
	void finish();

	/**
	 * Paralisa o <strong>loop</strong> da aplicação.
	 */
	void pause();

	/**
	 * Retoma o loop da aplicação.
	 */
	void resume();

	/**
	 * Verifica se o <strong>loop</strong> está paralisado.
	 * 
	 * @return <code>true</code> se o loop estiver paralisado, <code>false</code> caso contrário.
	 */
	boolean isPause();

	/**
	 * Retorna a frequência de atualizações por segundo.
	 * 
	 * @return a frequência de atualizações por segundo.
	 */
	int getUPS();

	/**
	 * Retorna a frequência de atualizações dos frames por segundo.
	 * 
	 * @return a frequência de atualizações dos frames por segundo.
	 */
	int getFPS();

	// GameStep getGameStep();
}
