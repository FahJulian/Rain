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

    public Position2f add(Position2f p) {
        this.x += p.x;
        this.y += p.y;
        return this;
    }

    public Position2f add(Vector2f v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public static Vector2f distance(Position2f p1, Position2f p2) {
        return new Vector2f(p1.x - p2.x, p1.y - p2.y);
    }
}
