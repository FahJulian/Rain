package com.github.fahjulian.rain.level.tiles;

import com.github.fahjulian.rain.Position;
import com.github.fahjulian.rain.GridPosition;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.graphics.Sprite;

public class VoidTile extends Tile {
    
    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(GridPosition pos, Screen screen) {
        screen.renderSprite(new Position(pos.col << 4, pos.row << 4), sprite);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}