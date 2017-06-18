/*
 * J2DRectangle.java
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

import java.awt.geom.Rectangle2D;

import io.solsticio.core.geom.Rectangle;
import io.solsticio.core.geom.Shape;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class J2DRectangle implements Rectangle, J2DShape {
    
    private Rectangle2D rectangle;
    
    public J2DRectangle(double x, double y, double width, double height) {
        rectangle = new Rectangle2D.Double(x, y, width, height);
    }

    @Override
    public double getX() {
        return rectangle.getX();
    }

    @Override
    public double getY() {
        return rectangle.getY();
    }

    @Override
    public double getWidth() {
        return rectangle.getWidth();
    }

    @Override
    public double getHeight() {
        return rectangle.getHeight();
    }

    @Override
    public double getArea() {
        return getWidth() * getHeight();
    }

    @Override
    public boolean contains(double x, double y) {
        return rectangle.contains(x, y);
    }

    @Override
    public boolean intersect(Shape shape) {
        return rectangle.contains(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }

    @Override
    public java.awt.Shape getJavaShape() {
        return rectangle;
    }
}
