/*
 * Stroke.java
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
package io.solsticio.core.graphics;

import java.io.Serializable;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class Stroke implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Cap {
        BUTT, SQUARE, ROUND
    }

    public enum Join {
        MITER, ROUND, BEVEL
    }

    public final float width;
    public final float miter;
    public final float dashWidth;
    public final float dashGap;
    public final Cap cap;
    public final Join join;

    public Stroke(float width, float miter, float dashWidth, float dashGap, Cap cap, Join join) {
        this.width = width;
        this.miter = miter;
        this.dashWidth = dashWidth;
        this.dashGap = dashGap;
        this.cap = cap;
        this.join = join;
    }

    public Stroke(float width, float miter, Cap cap, Join join) {
        this (width, miter, 0, 0, cap, join);
    }

    public Stroke(float width, float miter) {
        this (width, miter, Cap.SQUARE, Join.ROUND);
    }
}
