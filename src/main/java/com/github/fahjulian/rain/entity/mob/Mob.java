package com.github.fahjulian.rain.entity.mob;

import com.github.fahjulian.rain.entity.Entity;
import com.github.fahjulian.rain.entity.projectile.Projectile;
import com.github.fahjulian.rain.graphics.Screen;

import java.util.ArrayList;

import com.github.fahjulian.rain.Direction;
import com.github.fahjulian.rain.math.Position;
import com.github.fahjulian.rain.math.GridPosition;
import com.github.fahjulian.rain.level.Level;
import com.github.fahjulian.rain.math.Vector;

public abstract class Mob extends Entity {
    
    protected Direction dir;
    protected boolean moving = false;
    protected Level level;
    protected ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    protected Mob(Level level) {
        this.level = level;
    }

    public abstract void render(Screen screen);
    public abstract void update();

    public void move(Vector vel) {
        adjustDirection(vel);

        if (!collision(new Vector(vel.x, 0)))
            pos.x += vel.x;
        if (!collision(new Vector(0, vel.y))) 
            pos.y += vel.y;
    }

    protected boolean collision(Vector vel) {
        Position center = new Position(pos.x + 8, pos.y + 8);

        for (int c = 0; c < 4; c++) {
            GridPosition pos = new GridPosition();
            pos.col = ((c >= 2 ? c * 2 : 0) + center.x + vel.x - 4) / 16;
            pos.row = ((c % 2 == 0 ? c * 2 : 0) + center.y + vel.y + 3) / 16;
            if (level.getTile(pos).isSolid()) return true;
        }

        return false;
    }

    private void adjustDirection(Vector vel) {
        if (vel.x == 0 && vel.y == 0) 
            dir = null;
        if (vel.x == 0 && vel.y <  0) dir = Direction.NORTH;
        if (vel.x >  0 && vel.y <  0) dir = Direction.NORTHEAST;
        if (vel.x >  0 && vel.y == 0) dir = Direction.EAST;
        if (vel.x >  0 && vel.y >  0) dir = Direction.SOUTHEAST;
        if (vel.x == 0 && vel.y >  0) dir = Direction.SOUTH;
        if (vel.x <  0 && vel.y >  0) dir = Direction.SOUTHWEST;
        if (vel.x <  0 && vel.y == 0) dir = Direction.WEST;
        if (vel.x <  0 && vel.y <  0) dir = Direction.NORTHWEST;
    }

}
