/*
 * J2DPainter.java
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

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Deque;

import io.solsticio.core.geom.IPath;
import io.solsticio.core.geom.Shape;
import io.solsticio.core.graphics.Color;
import io.solsticio.core.graphics.Image;
import io.solsticio.core.graphics.Painter;
import io.solsticio.core.graphics.Stroke;
import io.solsticio.core.graphics.Transform;
import io.solsticio.java2d.geom.J2DPath;
import io.solsticio.java2d.geom.J2DShape;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class J2DPainter implements Painter {

    private Color background = Color.BLACK;
    private Graphics2D canvas;
    private Deque<AffineTransform> cache;
    private io.solsticio.core.graphics.Font font;

    public J2DPainter(Graphics2D canvas) {
        this.canvas = canvas;
        canvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        canvas.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        cache = new ArrayDeque<>(5);

        Font f = canvas.getFont();
        String fontName = f.getFontName();
        int size = f.getSize();
        int style = f.getStyle();
        font = new io.solsticio.core.graphics.Font(fontName, size,
                style == Font.PLAIN ? io.solsticio.core.graphics.Font.FontType.NORMAL
                        : style == Font.ITALIC ? io.solsticio.core.graphics.Font.FontType.ITALIC
                                : style == Font.BOLD ? io.solsticio.core.graphics.Font.FontType.BOLD
                                        : style == Font.BOLD + Font.ITALIC
                                                ? io.solsticio.core.graphics.Font.FontType.BOLD_ITALIC
                                                : io.solsticio.core.graphics.Font.FontType.NORMAL);
    }

    @Override
    public void clear() {
        clear(background);
    }

    @Override
    public void clear(Color color) {
        setCanvasBackground(background);
        canvas.fillRect(0, 0, getWidth(), getHeight());
    }

    private java.awt.Color getAwtColor(Color color) {
        return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    @Override
    public void setBackgroundColor(Color color) {
        background = color;
        setCanvasBackground(color);
    }

    private void setCanvasBackground(Color color) {
        canvas.setBackground(getAwtColor(color));
        canvas.setColor(getAwtColor(color));
    }

    private java.awt.Stroke getJavaStroke(Stroke stroke) {
        if (stroke == null)
            return null;
        int cap;
        int join;
        switch (stroke.cap) {
            case BUTT:
                cap = BasicStroke.CAP_BUTT;
                break;
            case ROUND:
                cap = BasicStroke.CAP_ROUND;
                break;
            case SQUARE:
            default:
                cap = BasicStroke.CAP_SQUARE;
        }
        switch (stroke.join) {
            case BEVEL:
                join = BasicStroke.JOIN_BEVEL;
                break;
            case MITER:
                join = BasicStroke.JOIN_MITER;
                break;
            case ROUND:
            default:
                join = BasicStroke.JOIN_ROUND;
        }
        return new BasicStroke(stroke.width, cap, join, stroke.miter);
    }

    @Override
    public void setTextSize(float size) {
        Font font = canvas.getFont();
        canvas.setFont(new Font(font.getName(), font.getStyle(), (int) size));
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2, Stroke stroke) {
        Runnable r = () -> {
            Line2D line2D = new Line2D.Float(x1, y1, x2, y2);
            canvas.draw(line2D);
        };
        drawTemplate(stroke, r);
    }

    @Override
    public void drawRect(float x, float y, float width, float height, Stroke stroke) {
        Runnable r = () -> {
            Rectangle2D rectangle2D = new Rectangle2D.Float(x, y, width, height);
            canvas.draw(rectangle2D);
        };
        drawTemplate(stroke, r);
    }

    @Override
    public void drawOval(float x, float y, float width, float height, Stroke stroke) {
        Runnable r = () -> {
            Ellipse2D ellipse2D = new Ellipse2D.Float(x, y, width, height);
            canvas.draw(ellipse2D);
        };
        drawTemplate(stroke, r);
    }

    @Override
    public void drawCircle(float x, float y, float radius, Stroke stroke) {
        radius *= 2;
        drawOval(x, y, radius, radius, stroke);
    }

    @Override
    public void fillRect(float x, float y, float width, float height, Stroke stroke) {
        Runnable r = () -> {
            Rectangle2D rectangle2D = new Rectangle2D.Float(x, y, width, height);
            canvas.fill(rectangle2D);
        };
        drawTemplate(stroke, r);
    }

    @Override
    public void fillOval(float x, float y, float width, float height, Stroke stroke) {
        Runnable r = () -> {
            Ellipse2D ellipse2D = new Ellipse2D.Float(x, y, width, height);
            canvas.fill(ellipse2D);
        };
        drawTemplate(stroke, r);
    }

    @Override
    public void fillCircle(float x, float y, float radius, Stroke stroke) {
        radius *= 2;
        fillOval(x, y, radius, radius, stroke);
    }

    @Override
    public void drawString(String text, float x, float y) {
        canvas.drawString(text, x, y);
    }

    @Override
    public void drawImage(Image image, float x, float y) {
        BufferedImage bufferedImage = ((J2DImage) image).getImage();
        canvas.drawImage(bufferedImage, (int) x, (int) y, null);
    }

    @Override
    public void drawPath(IPath path) {
         Path2D path2D = ((J2DPath) path).getPath2D();
         canvas.draw(path2D);
    }

    @Override
    public void fillPath(IPath path) {
        Path2D path2D = ((J2DPath) path).getPath2D();
        canvas.fill(path2D);
    }

    @Override
    public void draw(Shape shape) {
        java.awt.Shape s = ((J2DShape) shape).getJavaShape();
        canvas.draw(s);
    }

    @Override
    public void fill(Shape shape) {
        java.awt.Shape s = ((J2DShape) shape).getJavaShape();
        canvas.fill(s);
    }

    @Override
    public int getWidth() {
        return canvas.getClipBounds().width;
    }

    @Override
    public int getHeight() {
        return canvas.getClipBounds().height;
    }

    @Override
    public Transform rotate(double angleInDegrees) {
        Transform t = new J2DTransform(canvas);
        return t.rotate(StrictMath.toRadians(angleInDegrees));
    }

    @Override
    public Transform rotate(double angleInDegrees, double px, double py) {
        Transform t = new J2DTransform(canvas);
        return t.rotate(StrictMath.toRadians(angleInDegrees), px, py);
    }

    @Override
    public Transform translate(double dx, double dy) {
        Transform t = new J2DTransform(canvas);
        return t.translate(dx, dy);
    }

    @Override
    public Transform scale(double sx, double sy) {
        Transform t = new J2DTransform(canvas);
        return t.scale(sx, sy);
    }

    @Override
    public void save() {
        AffineTransform ot = canvas.getTransform();
        cache.add(ot);
    }

    @Override
    public void restore() {
        AffineTransform ot = cache.pollLast();
        if (ot != null)
            canvas.setTransform(ot);
    }

    @Override
    public void setFont(io.solsticio.core.graphics.Font font) {
        this.font = font;
        int style = 0;
        switch (font.getFontType()) {
            case BOLD:
                style = Font.BOLD;
                break;
            case ITALIC:
                style = Font.ITALIC;
                break;
            case NORMAL:
                style = Font.PLAIN;
                break;
            case BOLD_ITALIC:
                style = Font.BOLD + Font.ITALIC;
                break;
        }
        canvas.setFont(new Font(font.getName(), style, font.getSize()));
    }

    @Override
    public io.solsticio.core.graphics.Font getFont() {
        return font;
    }

    Graphics2D getCanvas() {
        return canvas;
    }

    private void drawTemplate(Stroke stroke, Runnable r) {
        java.awt.Stroke js = null;
        if (stroke != null) {
            js = canvas.getStroke();
            canvas.setStroke(getJavaStroke(stroke));
        }
        r.run();
        if (js != null)
            canvas.setStroke(js);
    }
}
