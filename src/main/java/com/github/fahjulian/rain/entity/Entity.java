package com.github.fahjulian.rain.entity;

import java.util.Random;

import com.github.fahjulian.rain.Position;
import com.github.fahjulian.rain.graphics.Screen;

public abstract class Entity {
    
    protected static final Random random = new Random();

    public Position pos;
    private boolean removed = false;

    public abstract void render(Screen screen);

    public void remove() {
        this.removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

}