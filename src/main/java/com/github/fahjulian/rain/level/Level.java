package com.github.fahjulian.rain.level;

import com.github.fahjulian.rain.Position;
import com.github.fahjulian.rain.GridPosition;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.level.tiles.Tile;

public abstract class Level {
    
    protected int rows, cols;
    protected int[] tiles;

    /**
     * Construct a randomly generated level
     * @param rows Rows of Tiles in the Map
     * @param cols Cols of Tiles in the Map
     */
    protected Level(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        tiles = new int[rows * cols];
        generateLevel();
    }

    /**
     * Construct a level from a file
     * @param path Path to the file relative to the Project folder
     */
    protected Level(String path) {
        loadLevel(path);
    }

    /**
     * Update the level and all its members
     */
    public void update() {

    }
    
    /**
     * Render the level and all its members to the screen
     * @param offset The camera's location relative to (0, 0)
     * @param screen The Screen to render to
     */
    public void render(Position offset, Screen screen) {
        GridPosition min = new GridPosition(offset.y >> 4, offset.x >> 4);
        GridPosition max = new GridPosition((offset.y + screen.height) >> 4, (offset.x + screen.width) >> 4);

        for (int row = min.row; row <= max.row; row++) {
            for (int col = min.col; col <= max.col; col++) {
                GridPosition tilePos = new GridPosition(row, col);
                getTile(tilePos).render(tilePos, screen);
            }
        }
    }

    /**
     * Get the Tile that is located at the given position on the map
     * @param pos Row and Column on the map
     * @return The Tile that is located at the given position
     */
    public Tile getTile(GridPosition pos) {
        if (pos.row < 0 || pos.col < 0 || pos.row >= rows || pos.col >= cols)
            return Tile.voidTile;

        int tileID = tiles[pos.col + pos.row * cols];
        switch (tileID) {
            case 0: return Tile.grass;
            case 1: return Tile.stone;
            default: return Tile.voidTile;
        }
    }

    private void time() {

    }

    private void loadLevel(String path) {

    }

    protected void generateLevel() {

    }

}
