package com.github.fahjulian.rain.level.tiles;

import com.github.fahjulian.rain.math.GridPosition;
import com.github.fahjulian.rain.math.Position;
import com.github.fahjulian.rain.graphics.Sprite;
import com.github.fahjulian.rain.graphics.Screen;

public abstract class Tile {
    
    private static int id = 0;
    private static Tile[] tiles = new Tile[128];
    
    public static Tile 
        VOID = new NormalTile(Sprite.VOID),
        AIR = new NormalTile(Sprite.AIR),
        CLOUD = new NormalTile(Sprite.CLOUD),
        GRASS = new NormalTile(Sprite.GRASS),
        FLOWER = new NormalTile(Sprite.FLOWER),
        ROCK = new SolidTile(Sprite.STONE),
        BRICK_RED = new SolidTile(Sprite.BRICK_RED),
        BRICK_GREY = new SolidTile(Sprite.BRICK_GREY),
        LOG = new SolidTile(Sprite.STEM),
        LEAVES = new NormalTile(Sprite.LEAVES);
    
    public final int ID;
    private Sprite sprite;

    protected Tile(Sprite sprite) {
        this.sprite = sprite;
        this.ID = id++;
        tiles[this.ID] = this;
    }

    public boolean isSolid() {
        return this instanceof Collidable;
    }

    public void render(GridPosition pos, Screen screen) {
        screen.renderTile(new Position(pos.col << 4, pos.row << 4), this);
    }

    public static Tile getTileByID(int id) {
        return tiles[id];
    }

    public Sprite getSprite() {
        return sprite;
    }
}
