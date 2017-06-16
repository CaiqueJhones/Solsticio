/*
 * Sprite.java
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
package io.solsticio.core.scene;

import io.solsticio.core.geom.Vector2D;
import io.solsticio.core.graphics.Image;
import io.solsticio.core.graphics.Painter;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class Sprite extends Node2D {
    
    private Image source;
    
    private int columns = 1;
    private int rows = 1;
    
    private int width;
    private int height;
    
    private int currentColumn = 1;
    private int currentRow = 1;
    
    private double angle;
    private double anchorX;
    private double anchorY;
    
    private void init() {
        if (source == null) return;
        width = source.getWidth() / columns;
        height = source.getHeight() / rows;
    }
    
    public void setSource(Image source) {
        this.source = source;
        init();
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
    
    public void setGrid(int columns, int rows) {
        this.columns = Math.max(1, columns);
        this.rows = Math.max(1, rows);
        init();
    }

    public void setColumns(int columns) {
        setGrid(columns, rows);
    }

    public void setRows(int rows) {
       setGrid(columns, rows);
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public Image getFirstColumn(int row) {
        return getImage(1, row);
    }
    
    public Image getFirstRow(int column) {
        return getImage(column, 1);
    }
    
    public Image getImage(int col, int row) {
        if (columns == 1 && rows == 1)
            return source;
        
        return source.subImage(width * col, height * row, width, height);
    }
    
    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = Math.max(1, Math.min(columns, currentColumn));
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = Math.max(1, Math.min(rows, currentRow));
    }
    
    public void rotate(double angleInDegress, double anchorX, double anchorY) {
        this.angle = angleInDegress;
        this.anchorX = anchorX;
        this.anchorY = anchorY;
    }
    
    public double getAngle() {
        return angle;
    }

    @Override
    public void setup() {
        setPaintable(true);
        setupChildren();
    }
    
    @Override
    public void paint(Painter painter) {
        painter.save();
        painter.rotate(angle, anchorX, anchorY).apply();
        
        Vector2D p = getGlobalPosition();
        painter.drawImage(getImage(currentColumn, currentRow), (float) p.x, (float) p.y); 
        
        painter.restore();
        
        paintChildren(painter);
    }
}
