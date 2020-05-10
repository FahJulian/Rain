package com.github.fahjulian.rain.entity.particle;

import java.util.Random;

import com.github.fahjulian.rain.entity.Entity;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.graphics.Sprite;

public class Particle extends Entity implements Entity.Visible {

    public static class Specs extends Entity.Specs {
        private float x, y;
        private int duration;

        public Specs(float x, float y, int duration) {
            this.x = x;
            this.y = y;
            this.duration = duration;
        }
    }

    private static final Sprite SPRITE = Sprite.PARTICLE_GREY;
    private static final Random random = new Random();

    protected Sprite sprite;
    protected float x, y;
    protected float velX, velY;
    protected int duration;

    public Particle(Particle.Specs specs) {
        this.x = specs.x;
        this.y = specs.y;
        this.duration = specs.duration;

        this.velX = (float) random.nextGaussian() * 0.1f;
        this.velY = (float) random.nextGaussian() * 0.1f;
        this.sprite = SPRITE;
    }


    @Override
    public void render(Screen screen) {
        screen.render(getSprite(), (int) x, (int) y);
    }

    @Override
    public void update() {
        x += velX;
        y += velY;

        if (duration-- < 0) {
            remove();
        }
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}