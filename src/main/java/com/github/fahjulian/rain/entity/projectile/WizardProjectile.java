package com.github.fahjulian.rain.entity.projectile;

import com.github.fahjulian.rain.graphics.Sprite;
import com.github.fahjulian.rain.math.Position2f;

public class WizardProjectile extends Projectile {

    public static final float RANGE = 200.0f;
    public static final float RATE_OF_FIRE =  200.0f;
    public static final float SPEED = 1.8f;
    public static final Sprite SPRITE = Sprite.PROJECTILE_WIZARD;

    public WizardProjectile(Position2f origin, float angle, Sprite sprite) {
        super(origin, angle, SPEED, sprite);
    }

    @Override
    public void update() {
        move();
    }

}
