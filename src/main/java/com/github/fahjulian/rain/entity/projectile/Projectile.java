package com.github.fahjulian.rain.entity.projectile;

import com.github.fahjulian.rain.math.Position2f;
import com.github.fahjulian.rain.math.Vector2f;
import com.github.fahjulian.rain.entity.Entity;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.graphics.Sprite;

public abstract class Projectile extends Entity {

    protected final Position2f origin;
    protected Position2f pos;
    protected Vector2f speed;
    protected float angle;

    protected Projectile(Position2f origin, float angle, float speed, Sprite sprite) {
        this.pos = origin.clone();
        this.origin = origin.clone();
        this.angle = angle;
        this.sprite = sprite;
        this.speed = new Vector2f((float) -Math.cos(angle), (float) -Math.sin(angle)).multiply(speed);
    }

    protected void move() {
        pos.x += speed.x;
        pos.y += speed.y;
    }
    
	@Override
	public void render(Screen screen) {
		screen.renderProjectile(pos.toInt(), this);
	}
}
