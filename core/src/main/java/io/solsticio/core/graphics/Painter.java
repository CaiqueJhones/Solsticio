/*
 * Painter.java
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
package io.solsticio.core.graphics;

import io.solsticio.core.geom.IPath;
import io.solsticio.core.geom.Shape;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public interface Painter {

    Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
    Stroke DEFAULT_STROKE = new Stroke(1, 1);

    void clear(Color color);

    void clear();

    void setBackgroundColor(Color color);
    
    void setTextSize(float size);

    void drawLine(float x1, float y1, float x2, float y2, Stroke stroke);

    void drawRect(float x, float y, float width, float height, Stroke stroke);

    void drawOval(float x, float y, float width, float height, Stroke stroke);

    void drawCircle(float x, float y, float radius, Stroke stroke);
    
    void drawString(String text, float x, float y);

    void drawImage(Image image, float x, float y);
    
    void drawPath(IPath path);
    
    void draw(Shape shape);

    void fillRect(float x, float y, float width, float height, Stroke stroke);

    void fillOval(float x, float y, float width, float height, Stroke stroke);

    void fillCircle(float x, float y, float radius, Stroke stroke);
    
    void fillPath(IPath path);
    
    void fill(Shape shape);

    int getWidth();

    int getHeight();

    /**
     * Rotaciona a área de pintura.
     * 
     * @param angleInDegrees
     *        Ângulo em graus.
     */
    Transform rotate(double angleInDegrees);

    /**
     * Rotaciona a área de pintura no ponto.
     * 
     * @param angleInDegrees
     *        Ângulo em graus.
     * @param px
     *        coordenada em x.
     * @param py
     *        coordenada em y.
     */
    Transform rotate(double angleInDegrees, double px, double py);

    Transform translate(double dx, double dy);

    Transform scale(double sx, double sy);

    void save();

    void restore();

    void setFont(Font font);
    
    Font getFont();
}
