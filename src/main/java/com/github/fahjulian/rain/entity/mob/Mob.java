package com.github.fahjulian.rain.entity.mob;

import com.github.fahjulian.rain.entity.Entity;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.graphics.Sprite;
import com.github.fahjulian.rain.Direction;
import com.github.fahjulian.rain.Position;
import com.github.fahjulian.rain.GridPosition;
import com.github.fahjulian.rain.level.Level;

public abstract class Mob extends Entity {
    
    protected Sprite sprite;
    protected Direction dir;
    protected boolean moving = false;
    protected Level level;

    protected Mob(Level level) {
        this.level = level;
    }

    public abstract void render(Screen screen);
    public abstract void update();

    public void move(int velX, int velY) {
        adjustDirection(velX, velY);

        if (!collision(velX, 0)) 
            pos.x += velX;
        if (!collision(0, velY)) 
            pos.y += velY;
    }

    protected boolean collision(int velX, int velY) {
        Position center = new Position(pos.x + 8, pos.y + 8);

        for (int c = 0; c < 4; c++) {
            GridPosition pos = new GridPosition();
            pos.col = ((c >= 2 ? c * 3 : 0) + center.x + velX - 20) / 16;
            pos.row = ((c % 2 == 0 ? c * 3 : 0) + center.y + velY - 15) / 16;
            if (level.getTile(pos).isSolid()) return true;
        }

        return false;
    }

    private void adjustDirection(int velX, int velY) {
        if (velX == 0 && velY == 0) 
            dir = null;
        if (velX == 0 && velY <  0) dir = Direction.NORTH;
        if (velX >  0 && velY <  0) dir = Direction.NORTHEAST;
        if (velX >  0 && velY == 0) dir = Direction.EAST;
        if (velX >  0 && velY >  0) dir = Direction.SOUTHEAST;
        if (velX == 0 && velY >  0) dir = Direction.SOUTH;
        if (velX <  0 && velY >  0) dir = Direction.SOUTHWEST;
        if (velX <  0 && velY == 0) dir = Direction.WEST;
        if (velX <  0 && velY <  0) dir = Direction.NORTHWEST;
    }

}
