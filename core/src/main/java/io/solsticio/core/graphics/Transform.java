/*
 * Transform.java
 *
 * Copyright 2017 Caique Jhones
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package io.solsticio.core.graphics;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public interface Transform {

    Transform translate(double tx, double ty);

    /**
     *
     * @param theta Ângulo em radianos.
     * @return
     */
    Transform rotate(double theta);

    Transform rotate(double theta, double anchorX, double anchorY);

    Transform scale(double sx, double sy);

    void apply();
}
