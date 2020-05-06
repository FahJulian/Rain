package com.github.fahjulian.rain.math;

public class Vector2f {

    public float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f multiply(float a) {
        this.x *= a;
        this.y *= a;
        return this;
    }
}
