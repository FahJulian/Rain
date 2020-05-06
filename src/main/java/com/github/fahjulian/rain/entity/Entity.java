package com.github.fahjulian.rain.entity;

import java.util.Random;

import com.github.fahjulian.rain.math.Position;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.graphics.Sprite;

public abstract class Entity {
    
    protected static final Random random = new Random();

    public Position pos;
    private boolean removed = false;
    protected Sprite sprite;

    public abstract void update();
    public abstract void render(Screen screen);

    public void remove() {
        this.removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public Position getCenter() {
        return new Position(pos.x + 8, pos.y + 8);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
