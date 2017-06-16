/*
 * TouchEvent.java
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

import java.io.Serializable;

/**
 * @author Caique Jhones
 *
 */
public class TouchEvent implements Serializable {

    private static final long serialVersionUID = 6692442118494531592L;
    
    public static final int TOUCH_DOWN    = 0;
    public static final int TOUCH_UP      = 1;
    public static final int TOUCH_DRAGGED = 2;
    public static final int TOUCH_HOLD    = 3;

    public int type;
    public int x, y;
    public int pointer;

}
