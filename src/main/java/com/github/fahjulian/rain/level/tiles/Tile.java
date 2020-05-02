package com.github.fahjulian.rain.level.tiles;

import com.github.fahjulian.rain.GridPosition;
import com.github.fahjulian.rain.graphics.Sprite;
import com.github.fahjulian.rain.graphics.Screen;

public abstract class Tile {

    public static Tile 
        voidTile = new VoidTile(Sprite.voidSprite),
        grass = new GrassTile(Sprite.grass),
        stone = new StoneTile(Sprite.stone);
    
    public Sprite sprite;

    protected Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public abstract boolean isSolid();
    public abstract void render(GridPosition pos, Screen screen);

}
