/*
 * InputAction.java
 *
 * Copyright 2017 Caique Jhones
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
package io.solsticio.core.input;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caique Jhones
 *
 */
public final class InputAction {

    private InputAdapter adapter;
    private List<Key> keys;
    
    public InputAction(InputAdapter adapter) {
        this.adapter = adapter;
        this.keys = new ArrayList<>();
    }

    public boolean isPressed() {
        for (Key key : keys) {
            if (adapter.isPressed(key)) return true;
        }
        return false;
    }

    public boolean isClicked() {
        for (Key key : keys) {
            if (!adapter.isClicked(key)) return true;
        }
        return false;
    }

    public boolean add(Key key) {
        return keys.add(key);
    }

    public boolean remove(Key key) {
        return keys.remove(key);
    }
}
