package com.github.fahjulian.rain;

public class GridPosition {

    public int row, col;

    public GridPosition() {
        
    }

    public GridPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position toPosition(int tileSize) {
        return new Position(col * tileSize, row * tileSize);
    }
}