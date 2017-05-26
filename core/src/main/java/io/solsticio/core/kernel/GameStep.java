/*
 * GameStep.java
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
 * Esta Interface representa os passos que a aplicação irá seguir durante sua execução.
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public interface GameStep {

	/**
	 * Chamado no início da aplicação.
	 */
	void setup(GameLoop gameLoop);
	
	/**
	 * Atualiza lógica de negócios.
	 */
	void updateLogic(long timeInMillis);
	
	/**
	 * Renderiza os recursos visuais.
	 */
	void renderGraphics();
	
	/**
	 * Desenha gráficos. Responsável por exibir os compontes para o usuário.
	 */
	void paintGraphics();
	
	/**
	 * Este método é chamado quando a aplicação é finalizada, aqui deve conter a
	 * lógica antes que a aplicaçao termine. Por exemplo, pode ser útil para
	 * salvar os estados dos componentes.
	 */
	void terminate();

}
