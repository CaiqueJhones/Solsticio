/*
 * Vector2D.java
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
package io.solsticio.core.geom;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.atan2;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;
import static java.lang.StrictMath.sqrt;

import java.io.Serializable;

/**
 * @author Caique Jhones
 */
public final class Vector2D implements Serializable {
	
	private static final long serialVersionUID = -3214134852252616336L;
	
	public final double x;
	public final double y;

	public Vector2D() {
		this(0, 0);
	}

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D(Vector2D cp) {
		this(cp.x, cp.y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double lengthSquare() {
		return x * x + y * y;
	}

	public double length() {
		return sqrt(lengthSquare());
	}

	public Vector2D setLength(double newLength) {
		double relation = newLength / length();
		return new Vector2D(dot(relation));
	}

	public double getAngle() {
		return atan2(y, x);
	}

	public Vector2D rotate(double angle) {
		double s = sin(angle);
		double c = cos(angle);

		double newX = x * c - y * s;
		double newY = x * s + y * c;

		return new Vector2D(newX, newY);
	}

	public double angleBetween(Vector2D other) {
		double angle = other.getAngle() - getAngle();

		if (StrictMath.abs(angle) < PI) return angle;

		return (angle + (angle < 0 ? 2 * PI : -2 * PI));
	}

	public Vector2D add(Vector2D other) {
		return new Vector2D(x + other.x, y + other.y);
	}

	public Vector2D sub(Vector2D other) {
		return new Vector2D(x - other.x, y - other.y);
	}

	public Vector2D dot(double scalar) {
		return new Vector2D(x * scalar, y * scalar);
	}

	public double dot(Vector2D other) {
		return Vector2D.dot(this, other);
	}

	public double cross(Vector2D other) {
		return Vector2D.cross(this, other);
	}

	public Vector2D negate() {
		return new Vector2D(-x, -y);
	}

	public Vector2D negateX() {
		return new Vector2D(-x, y);
	}

	public Vector2D negateY() {
		return new Vector2D(x, -y);
	}

	public Vector2D abs() {
		return new Vector2D(StrictMath.abs(x), StrictMath.abs(y));
	}

	public Vector2D skew() {
		return new Vector2D(-y, x);
	}

	public Vector2D normalize() {
		return dot(1.0 / length());
	}
	
	public Vector2D vectorX() {
		return new Vector2D(x, 0);
	}
	
	public Vector2D vectorY() {
		return new Vector2D(0, y);
	}
	
	public Vector2D cosDirector() {
		if (isZero()) return Vector2D.zero();
		double length = length();
		return new Vector2D(x/length, -y/length);
	}

	public boolean isValid() {
		return !Double.isNaN(x) && !Double.isInfinite(x) && !Double.isNaN(y) && !Double.isInfinite(y);
	}

	public boolean isZero() {
		return x == 0 && y == 0;
	}

	public double[] toArray() {
		return new double[] { x, y };
	}
	
	public static Vector2D zero() {
		return new Vector2D();
	}

	public static Vector2D max(Vector2D a, Vector2D b) {
		return new Vector2D(a.x > b.x ? a.x : b.x, a.y > b.y ? a.y : b.y);
	}

	public static Vector2D min(Vector2D a, Vector2D b) {
		return new Vector2D(a.x < b.x ? a.x : b.x, a.y < b.y ? a.y : b.y);
	}

	public static double cross(Vector2D a, Vector2D b) {
		return a.x * b.y - a.y * b.x;
	}

	public static Vector2D cross(Vector2D a, double s) {
		return new Vector2D(s * a.y, -s * a.x);
	}

	public static Vector2D cross(double s, Vector2D v) {
		double x = -s * v.y;
		double y = s * v.x;
		return new Vector2D(x, y);
	}

	public static double dot(Vector2D a, Vector2D b) {
		return a.x * b.x + a.y * b.y;
	}
	
	public static Vector2D cosDirector(double x1, double y1, double x2, double y2) {
		double x = x2-x1; double y = -(y2-y1);
		if(x == 0 && y == 0) return Vector2D.zero();
		double length = new Vector2D(x, y).length();
		return new Vector2D(x/length, y/length);
	}
	
	public static Vector2D of(double x, double y) {
		return new Vector2D(x, y);
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
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
        if (!(obj instanceof Vector2D)) {
            return false;
        }
        Vector2D other = (Vector2D) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vector2D [x=" + x + ", y=" + y + "]";
    }
}
