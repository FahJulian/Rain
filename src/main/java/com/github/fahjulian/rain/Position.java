package com.github.fahjulian.rain;

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
}