package com.github.fahjulian.rain.math;

public class Position {
    
    public int x, y;

    public Position() {
        
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GridPosition toGridPosition(int tileSize) {
        return new GridPosition(y / tileSize, x / tileSize);
    }

    public Position add(Position p) {
        return new Position(this.x + p.x, this.y + p.y);
    }

    public Position2f toFloat() {
        return new Position2f((float) x, (float) y);
    }
}