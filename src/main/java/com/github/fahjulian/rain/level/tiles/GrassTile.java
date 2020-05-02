package com.github.fahjulian.rain.level.tiles;

import com.github.fahjulian.rain.graphics.Sprite;
import com.github.fahjulian.rain.Position;
import com.github.fahjulian.rain.GridPosition;
import com.github.fahjulian.rain.graphics.Screen;

public class GrassTile extends Tile {
    
    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    /**
     * Render the tile to the screen
     * @param pos The Position on the map 
     * @param screen The screen to render to
     */
    @Override
    public void render(GridPosition pos, Screen screen) {
        screen.renderSprite(new Position(pos.col << 4, pos.row << 4), sprite);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}