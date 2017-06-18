/*
 * J2DCircle.java
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
package io.solsticio.java2d.geom;

import java.awt.geom.Ellipse2D;

import io.solsticio.core.geom.Circle;
import io.solsticio.core.geom.Shape;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class J2DCircle implements Circle, J2DShape {
    
    private Ellipse2D ellipse;
    
    public J2DCircle(double x, double y, double radius) {
        ellipse = new Ellipse2D.Double(x, y, radius, radius);
    }
    
    @Override
    public double getX() {
        return ellipse.getX();
    }

    @Override
    public double getY() {
        return ellipse.getY();
    }

    @Override
    public double getWidth() {
        return ellipse.getWidth();
    }

    @Override
    public double getHeight() {
        return ellipse.getHeight();
    }

    @Override
    public double getArea() {
        double radius = getRadius();
        return 2 * StrictMath.PI * radius * radius;
    }

    @Override
    public boolean contains(double x, double y) {
        return ellipse.contains(x, y);
    }

    @Override
    public boolean intersect(Shape shape) {
        return ellipse.intersects(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }

    @Override
    public double getCenterX() {
        return ellipse.getCenterX();
    }

    @Override
    public double getCenterY() {
        return ellipse.getCenterY();
    }

    @Override
    public double getRadius() {
        return ellipse.getWidth() / 2;
    }

    @Override
    public java.awt.Shape getJavaShape() {
        return ellipse;
    }
}
