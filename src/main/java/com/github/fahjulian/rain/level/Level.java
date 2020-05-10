package com.github.fahjulian.rain.level;

import com.github.fahjulian.rain.math.Position;
import com.github.fahjulian.rain.math.GridPosition;

import java.util.ArrayList;
import java.util.List;

import com.github.fahjulian.rain.entity.Entity;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.level.tiles.Tile;

public abstract class Level {
    
    public static final Level SPAWN_LEVEL = new SpawnLevel("src/main/resources/levels/castle.png");

    protected String path;
    protected int rows, cols;
    protected int[] tiles;

    private List<Entity> entities = new ArrayList<Entity>();

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
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (e.isRemoved()) {
                entities.remove(i);
                continue;
            }
            e.update();
        }
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

        for (Entity e: entities) {
            if (e instanceof Entity.Visible)
                ((Entity.Visible) e).render(screen);
        }
    }

    /**
     * Get the Tile that is located at the given position on the map
     * @param pos Row and Column on the map
     * @return The Tile that is located at the given position
     */
    public Tile getTile(GridPosition pos) {
        if (pos.row < 0 || pos.col < 0 || pos.row >= rows || pos.col >= cols)
            return Tile.VOID;

        int tileID = tiles[pos.col + pos.row * cols];
        return Tile.getTileByID(tileID);
    }

    /**
     * Check if an Enity would collide with a tile if moved
     * @param x The x-Position of the Entity
     * @param y The y-Position of the Entity
     * @param velX The x-Velocity of the Entity
     * @param velY The y-Velocity of the Entity
     * @param size The size of the Entity
     * @return Whether or not the Entity would collide if moved
     */
    public boolean tileCollision(int x, int y, int velX, int velY, int size) {
        for (int c = 0; c < 4; c++) {
            GridPosition pos = new GridPosition();
            pos.col = ((x + velX) + c % 2 * size) / 16;
            pos.row = ((y + velY) + c / 2 * size) / 16;
            if (getTile(pos).isSolid()) return true;
        }

        return false;
    }
    
    /**
     * Check if an Enity would collide with a tile if moved
     * @param x The x-Position of the Entity
     * @param y The y-Position of the Entity
     * @param velX The x-Velocity of the Entity
     * @param velY The y-Velocity of the Entity
     * @param size The size of the Entity
     * @return Whether or not the Entity would collide if moved
     */
    public boolean tileCollision(float x, float y, float velX, float velY, int size) {
        return tileCollision((int) x, (int) y, (int) velX, (int) velY, size);
    }

    protected int rgbaToTileID(int rgba) {
        switch (rgba) {
            case 0xff008800: return Tile.GRASS.ID;
            case 0xffffff00: return Tile.FLOWER.ID;
            case 0xff888888: return Tile.ROCK.ID;
            case 0xff880000: return Tile.BRICK_GREY.ID;
            case 0xff884400: return Tile.LOG.ID;
            case 0xff004400: return Tile.LEAVES.ID;
            case 0xff0088ff: return Tile.AIR.ID;
            case 0xffffffff: return Tile.CLOUD.ID;
            default: return Tile.VOID.ID;
        }
    }

    public void add(Entity e) {
        entities.add(e);
        e.setLevel(this);
    }

    @SuppressWarnings("unused")
    private void time() {

    }

}
