package com.github.fahjulian.rain.entity;

import java.util.Random;

public abstract class Entity {
    
    protected static final Random random = new Random();

    public int x, y;
    private boolean removed = false;

    public void remove() {
        this.removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

}