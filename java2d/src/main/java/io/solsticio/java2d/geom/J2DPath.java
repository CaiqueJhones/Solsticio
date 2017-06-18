/*
 * J2DPath.java
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
package io.solsticio.java2d.geom;

import java.awt.geom.Path2D;

import io.solsticio.core.geom.IPath;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class J2DPath implements IPath {

    private Path2D path2D;

    public J2DPath() {
        path2D = new Path2D.Float();
    }

    @Override
    public void curveTo(float x1, float y1, float x2, float y2, float x3, float y3) {
        path2D.curveTo(x1, y1, x2, y2, x3, y3);
    }

    @Override
    public void lineTo(float x, float y) {
        path2D.lineTo(x, y);
    }

    @Override
    public void moveTo(float x, float y) {
        path2D.moveTo(x, y);
    }

    @Override
    public void quadTo(float x1, float y1, float x2, float y2) {
        path2D.quadTo(x1, y1, x2, y2);
    }

    @Override
    public void reset() {
        path2D.reset();
    }

    @Override
    public void close() {
        path2D.closePath();
    }

    public Path2D getPath2D() {
        return path2D;
    }
}
