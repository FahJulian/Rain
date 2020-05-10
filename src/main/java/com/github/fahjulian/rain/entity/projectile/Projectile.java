package com.github.fahjulian.rain.entity.projectile;

import com.github.fahjulian.rain.entity.Entity;
import com.github.fahjulian.rain.entity.EntitySpawner;
import com.github.fahjulian.rain.entity.particle.Particle;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.graphics.Sprite;

public abstract class Projectile extends Entity implements Entity.Visible {

    public static abstract class Specs extends Entity.Specs {
        private float originX, originY;
        private float targetX, targetY;
        private float speed;
        private float range;
        private float rateOfFire;
        private int size;

        protected Specs(float originX, float originY, float targetX, float targetY, 
            float speed, float range, float rateOfFire, int size) {
            this.originX = originX;
            this.originY = originY;
            this.targetX = targetX;
            this.targetY = targetY;
            this.speed = speed;
            this.range = range;
            this.rateOfFire = rateOfFire;
            this.size = size;
        }
    }

    protected final float originX, originY;
    protected final float velX, velY;
    protected float x, y;
    protected float angle;
    private Sprite sprite;
    private final float RANGE;
    private final int SIZE;
    public final float RATE_OF_FIRE;

    protected Projectile(Projectile.Specs specs, Sprite sprite) {
        this.x = this.originX = specs.originX;
        this.y = this.originY = specs.originY;
        this.angle = (float) Math.atan2(specs.targetY - specs.originY, specs.targetX - specs.originX);
        this.velX = (float) Math.cos(angle) * specs.speed;
        this.velY = (float) Math.sin(angle) * specs.speed;
        this.RANGE = specs.range;
        this.RATE_OF_FIRE = specs.rateOfFire;
        this.SIZE = specs.size;
        this.sprite = sprite;
    }

    @Override
    public void update() {
        move();
    }
    
    @Override
	public void render(Screen screen) {
		screen.render(sprite, (int) x, (int) y);
    }
    
    protected void move() {
        float distance = (float) Math.sqrt((x - originX) * (x - originX) + (y - originY) * (y - originY));
        if (distance > RANGE) 
            this.remove();

        if (level.tileCollision(x, y, velX, velY, SIZE))
            this.remove();

        x += velX;
        y += velY;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void remove() {
        super.remove();

        level.add(new EntitySpawner(Particle.class, new Particle.Specs(x, y, 50), 15));
    }
}
