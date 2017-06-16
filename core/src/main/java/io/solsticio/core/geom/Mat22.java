/*
 * Mat22.java
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
package io.solsticio.core.geom;

import java.io.Serializable;

/**
 * @author Caique Jhones
 */
public class Mat22 implements Serializable {

    private static final long serialVersionUID = -6187454110111389377L;
    public final double m00, m01;
    public final double m10, m11;

    public Mat22() {
        this(0, 0, 0, 0);
    }

    public Mat22(double m00, double m01, double m10, double m11) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
    }

    public final Mat22 abs() {
        return new Mat22(StrictMath.abs(m00), StrictMath.abs(m01), StrictMath.abs(m10), StrictMath.abs(m11));
    }

    public final Mat22 transpose() {
        return new Mat22(m00, m10, m01, m11);
    }

    public final Mat22 inverse() {
        double invDet = 1 / (m00 * m11 - m01 * m10);
        return mult(invDet);
    }

    public final Mat22 mult(Mat22 b) {
        return Mat22.mult(this, b);
    }

    public final Mat22 mult(double s) {
        return Mat22.mult(s, this);
    }

    public final Vector2D getAxisX() {
        return new Vector2D(m00, m10);
    }

    public final Vector2D getAxisY() {
        return new Vector2D(m01, m11);
    }

    /**
     * Define out pela transformação de {x, y} por esta matriz.
     * 
     * @param x
     * @param y
     * @return
     */
    public final Vector2D mult(double x, double y) {
        double outX = m00 * x + m01 * y;
        double outY = m10 * x + m11 * y;
        return new Vector2D(outX, outY);
    }

    public final Vector2D mult(Vector2D v) {
        return mult(v.getX(), v.getY());
    }

    public final double[] toArray() {
        return new double[] { m00, m01, m10, m11 };
    }

    public final double[][] toMatrix() {
        return new double[][] { { m00, m01 }, { m10, m11 } };
    }

    public static Mat22 mult(Mat22 a, Mat22 b) {
        return new Mat22(a.m00 * b.m00 + a.m01 * b.m10, a.m00 * b.m01 + a.m01 * b.m11, a.m10 * b.m00 + a.m11 * b.m10,
                a.m10 * b.m01 + a.m11 * b.m11);
    }

    public static Mat22 mult(double s, Mat22 a) {
        return new Mat22(a.m00 * s, a.m01 * s, a.m10 * s, a.m11 * s);
    }

    public static Mat22 identity() {
        return new Mat22(1, 0, 0, 1);
    }

    public static Mat22 from(double radians) {
        double c = StrictMath.cos(radians);
        double s = StrictMath.sin(radians);
        return new Mat22(c, -s, s, c);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(m00);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m01);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m10);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m11);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Mat22)) {
            return false;
        }
        Mat22 other = (Mat22) obj;
        if (Double.doubleToLongBits(m00) != Double.doubleToLongBits(other.m00)) {
            return false;
        }
        if (Double.doubleToLongBits(m01) != Double.doubleToLongBits(other.m01)) {
            return false;
        }
        if (Double.doubleToLongBits(m10) != Double.doubleToLongBits(other.m10)) {
            return false;
        }
        if (Double.doubleToLongBits(m11) != Double.doubleToLongBits(other.m11)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("%f %f\n%f %f", m00, m01, m10, m11);
    }
}
