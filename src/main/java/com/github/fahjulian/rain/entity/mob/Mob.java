package com.github.fahjulian.rain.entity.mob;

import com.github.fahjulian.rain.entity.Entity;
import com.github.fahjulian.rain.entity.projectile.Projectile;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.graphics.Sprite;
import com.github.fahjulian.rain.Direction;
import com.github.fahjulian.rain.math.GridPosition;
import com.github.fahjulian.rain.math.Position;
import com.github.fahjulian.rain.math.Vector;

import java.util.ArrayList;

public abstract class Mob extends Entity implements Entity.Visible {
    
    public static abstract class Specs<T extends Mob> extends Entity.Specs<T> {
        private int x, y;

        protected Specs(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Position pos;
    public Sprite sprite;
    protected Direction dir;
    protected int updateCount;
    protected ArrayList<Projectile> projectiles;

    protected Mob(Entity.Specs<? extends Mob> specs) {
        Mob.Specs<? extends Mob> s = (Mob.Specs<? extends Mob>) specs;
        this.pos = new Position(s.x, s.y);
        projectiles = new ArrayList<Projectile>();
    }
    
    @Override
    public void render(Screen screen) {
        screen.render(sprite, pos.x, pos.y);

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(screen);
        }
    }

    public void update() {
        for (int i = 0; i < projectiles.size(); i++)
            if (projectiles.get(i).isRemoved()) projectiles.remove(i);
            else projectiles.get(i).update();
    }

    public void move(Vector vel) {
        adjustDirection(vel);

        if (!collision(new Vector(vel.x, 0)))
            pos.x += vel.x;
        if (!collision(new Vector(0, vel.y))) 
            pos.y += vel.y;
    }

    protected boolean collision(Vector vel) {
        for (int c = 0; c < 4; c++) {
            GridPosition pos = new GridPosition();
            pos.col = ((c >= 2 ? c * 2 : 0) + getCenter().x + vel.x - 4) / 16;
            pos.row = ((c % 2 == 0 ? c * 2 : 0) + getCenter().y + vel.y + 3) / 16;
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

    public Sprite getSprite() {
        return sprite;
    }

    public Position getCenter() {
        return new Position(pos.x + 8, pos.y + 8);
    }

    public Position getPos() {
        return pos.clone();
    }

}
