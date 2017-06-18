/*
 * Cache.java
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
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Cache rudimentar para armazenamento de objetos. Os objetos são armazenados
 * por ordem de entrada e sempre que o limite de elementos é atigindo, o objeto
 * que está na posição zero é jogado no lixo. O novo objeto sempre é armazenado
 * no fim.
 * </p>
 * <p>
 * É possível navegar pelos itens utilizando os métodos {@link #current()},
 * {@link #down()}, e {@link #up()}. O primeiro retorna o objeto pelo qual o
 * cursor aponta no momento, o segundo move o cursor para trás e retorna o
 * objeto anterior e o último avança o cursor e retorna o objeto à frente.
 * </p>
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class Cache<E> implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<E> elements;
    private int cursor;
    private int maxLimit;

    /**
     * Construtor padrão para esta classe.
     * 
     * @param max
     *        limite máximo do cache.
     */
    public Cache(int max) {
        elements = new ArrayList<>(max);
        maxLimit = max;
    }

    /**
     * <p>
     * Adiciona o objeto no fim do cache, se o mesmo estiver cheio, então o
     * objeto da posição zero é jogado no lixo.
     * </p>
     * <p>
     * Esta implementação não aceita objetos nulos.
     * </p>
     * 
     * @param element
     *        o objeto que ficará em cache.
     * @throws IllegalArgumentException
     *         será lançada se o argumento for nulo.
     */
    public void add(E element) throws IllegalArgumentException {
        if (element == null)
            throw new IllegalArgumentException("Element cannot be null!");
        if (elements.size() == maxLimit)
            elements.remove(0);
        elements.add(element);
        cursor = elements.size() - 1;
    }

    /**
     * Retorna o objeto em que o cursor está apontando no momento da chamada
     * deste método.
     * 
     * @return o objeto em que o cursor está apontando no momento da chamada
     *         deste método ou <code>null</code> caso o cache esteja vazio.
     */
    public E current() {
        if (elements.isEmpty())
            return null;
        return elements.get(cursor);
    }

    /**
     * Move o cursor para o objeto anterior.
     * 
     * @return o objeto anterior ou <code>null</code> caso o cache esteja vázio,
     *         ou se o cursor estiver na posição zero.
     */
    public E down() {
        if (cursor > 0) {
            cursor--;
            return current();
        } else
            return null;
    }

    /**
     * Move o cursor para o objeto posterior.
     * 
     * @return o objeto posterior ou <code>null</code> caso o cursor estiver na
     *         posição máxima.
     */
    public E up() {
        if (cursor < elements.size() - 1) {
            cursor++;
            return current();
        } else
            return null;
    }

    /**
     * Retorna o primeiro objeto do cache.
     * 
     * @return o primeiro objeto do cache ou <code>null</code> caso o cache
     *         esteja vazio.
     */
    public E first() {
        if (elements.isEmpty())
            return null;
        return elements.get(0);
    }

    /**
     * Retorna o limite máximo do cache.
     * 
     * @return o limite máximo do cache.
     */
    public int getMaxLimit() {
        return maxLimit;
    }

    /**
     * Retorna a posição do cursor.
     * 
     * @return a posição do cursor.
     */
    public int getCursor() {
        return cursor;
    }

    /**
     * Verifica se um determinado objeto existe no cache.
     * 
     * @param element
     *        o objeto que será verificado.
     * @return <code>true</code> se o objeto estiver no cache ou
     *         <code>false</code> caso contrário.
     */
    public boolean contains(E element) {
        return elements.contains(element);
    }
}
