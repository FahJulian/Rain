package com.github.fahjulian.rain.level;

import com.github.fahjulian.rain.Position;
import com.github.fahjulian.rain.GridPosition;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.level.tiles.Tile;

public abstract class Level {
    
    public static final Level SPAWN_LEVEL = new SpawnLevel("src/main/resources/levels/castle.png");

    protected String path;
    protected int rows, cols;
    protected int[] tiles;

    /**
     * Construct a level with a given size. Only for randomly generated levels
     * @param rows Rows of Tiles in the Map
     * @param cols Cols of Tiles in the Map
     */
    protected Level(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        tiles = new int[rows * cols];
        load();
    }

    /**
     * Construct a level from a file
     * @param path Path to the file relative to the Project folder
     */
    public Level(String path) {
        this.path = path;
        load();
    }

    protected abstract void load();

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
        return Tile.getTileByID(tileID);
    }

    protected int rgbaToTileID(int rgba) {
        switch (rgba) {
            case 0xff008800: return Tile.grass.ID;
            case 0xffffff00: return Tile.flower.ID;
            case 0xff888888: return Tile.rock.ID;
            case 0xff880000: return Tile.brick.ID;
            case 0xff884400: return Tile.log.ID;
            case 0xff004400: return Tile.leaves.ID;
            case 0xff0088ff: return Tile.air.ID;
            case 0xffffffff: return Tile.cloud.ID;
            default: return Tile.voidTile.ID;
        }
    }

    @SuppressWarnings("unused")
    private void time() {

    }

}
