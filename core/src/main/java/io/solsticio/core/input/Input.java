/*
 * Input.java
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

import java.util.List;
import java.util.Map;

import io.solsticio.core.geom.Vector2D;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public final class Input {
    
    private static InputAdapter adapter;
    private static Map<String, InputAction> actionEvents;
    
    private Input() {}
    
    public static void setAdapter(InputAdapter adapter) {
        Input.adapter = adapter;
        
    }

    public static boolean isPressed(Key key) {
        return adapter.isPressed(key);
    }
    
    public static boolean isClicked(Key key) {
        return adapter.isClicked(key);
    }
    
    public static boolean isTouchDown(int pointer) {
        return adapter.isTouchDown(pointer);
    }

    public static int getTouchX(int pointer) {
        return adapter.getTouchX(pointer);
    }

    public static int getTouchY(int pointer) {
        return adapter.getTouchY(pointer);
    }

    public List<TouchEvent> getTouchEvents() {
       return adapter.getTouchEvents(); 
    }

    public static Vector2D getAccelerometer() {
        return adapter.getAccelerometer();
    }
    
    public static Vector2D getGravity() {
        return adapter.getGravity();
    }
    
    public static Vector2D getMagnetometer() {
        return adapter.getMagnetometer();
    }
    
    public static boolean isActionClicked(String name) {
        InputAction action = actionEvents.get(name);
        if (action == null) return false;
        return action.isClicked();
    }
    
    public static boolean isActionPressed(String name) {
        InputAction action = actionEvents.get(name);
        if (action == null) return false;
        return action.isPressed();
    }
}
