/*
 * Node2D.java
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

import java.util.Map;

import io.solsticio.core.geom.Vector2D;
import io.solsticio.core.graphics.Painter;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class Node2D implements Node {
    
    protected String name = "node";
    protected boolean updatable;
    protected boolean paintable;
    protected Vector2D position = new Vector2D();
    protected Vector2D globalPosition = new Vector2D();
    
    private Map<String, Node> tree;
    
    public void add(Node node) {
        tree.put(node.getName(), node);
    }
    
    public Node getChild(String name) {
        return tree.get(name);
    }
     
    @Override
    public void setup() {
        setupChildren();
    }

    protected void setupChildren() {
        for (Node node : tree.values()) 
            node.setup();
    }

    @Override
    public void update(double delta) {
        updateChildren(delta);
    }

    protected void updateChildren(double delta) {
        for (Node node : tree.values()) 
            if (node.isUpdatable()) node.update(delta);
    }

    @Override
    public void paint(Painter painter) {
        paintChildren(painter);
    }

    protected void paintChildren(Painter painter) {
        for (Node node : tree.values()) 
            if (node.isPaintable()) node.paint(painter);
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public boolean isUpdatable() {
        return updatable;
    }

    @Override
    public boolean isPaintable() {
        return paintable;
    }
    
    @Override
    public void setPosition(Vector2D position) {
        this.position = position;
        this.globalPosition = globalPosition.add(position);
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public void setGlobalPosition(Vector2D globalPosition) {
        this.globalPosition = globalPosition.add(this.position);
    }

    @Override
    public Vector2D getGlobalPosition() {
        return globalPosition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    public void setPaintable(boolean paintable) {
        this.paintable = paintable;
    }
}
