package com.github.fahjulian.rain.entity;

import java.util.Random;

import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.graphics.Sprite;
import com.github.fahjulian.rain.level.Level;

public abstract class Entity {

    public static abstract class Specs {
        
    }
    
    public interface Visible {
        public abstract void render(Screen screen);
        public abstract Sprite getSprite();
    }

    protected static final Random random = new Random();

    private boolean removed = false;
    protected Level level;

    public abstract void update();

    public void remove() {
        this.removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
