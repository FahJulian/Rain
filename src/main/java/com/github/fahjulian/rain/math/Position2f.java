package com.github.fahjulian.rain.math;

public class Position2f {

    public float x, y;

    public Position2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position2f clone() {
        return new Position2f(x, y);
    }

    public Position toInt() {
        return new Position((int) x, (int) y);
    }
}
