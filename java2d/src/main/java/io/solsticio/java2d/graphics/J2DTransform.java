/*
 * J2DTransform.java
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
package io.solsticio.java2d.graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import io.solsticio.core.graphics.Transform;

/**
 * @author Caique Jhones
 * @version 1
 * @since 1
 */
public class J2DTransform implements Transform {

    private Graphics2D graphics2D;
    private AffineTransform transform;

    public J2DTransform(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        transform = new AffineTransform();
    }

    @Override
    public Transform translate(double tx, double ty) {
        transform.translate(tx, ty);
        return this;
    }

    @Override
    public Transform rotate(double theta) {
        transform.rotate(theta);
        return this;
    }

    @Override
    public Transform rotate(double theta, double anchorX, double anchorY) {
        transform.rotate(theta, anchorX, anchorY);
        return this;
    }

    @Override
    public Transform scale(double sx, double sy) {
        transform.scale(sx, sy);
        return this;
    }

    @Override
    public void apply() {
        graphics2D.setTransform(transform);
    }
}
