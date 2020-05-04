package com.github.fahjulian.rain.level.tiles;

import com.github.fahjulian.rain.GridPosition;
import com.github.fahjulian.rain.graphics.Sprite;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.Position;

public abstract class Tile {

    private static int id = 0;
    private static Tile[] tiles = new Tile[128];
    public final int ID;

    public static Tile 
        voidTile = new NormalTile(Sprite.voidSprite),
        air = new NormalTile(Sprite.air),
        cloud = new NormalTile(Sprite.cloud),
        grass = new NormalTile(Sprite.grass),
        flower = new NormalTile(Sprite.flower),
        rock = new SolidTile(Sprite.stone),
        brick = new SolidTile(Sprite.brick),
        log = new SolidTile(Sprite.stem),
        leaves = new NormalTile(Sprite.leaves);
    
    public Sprite sprite;

    protected Tile(Sprite sprite) {
        this.sprite = sprite;
        this.ID = id++;
        tiles[this.ID] = this;
    }

    public boolean isSolid() {
        return this instanceof Collidable;
    }

    public void render(GridPosition pos, Screen screen) {
        screen.renderSprite(new Position(pos.col << 4, pos.row << 4), sprite);
    }

    public static Tile getTileByID(int id) {
        return tiles[id];
    }

}
