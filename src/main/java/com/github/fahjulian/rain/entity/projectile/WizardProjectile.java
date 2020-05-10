package com.github.fahjulian.rain.entity.projectile;

import com.github.fahjulian.rain.graphics.Sprite;

public class WizardProjectile extends Projectile {

    public static class Specs extends Projectile.Specs {

        public Specs(float originX, float originY, float targetX, float targetY) {
            super(originX, originY, targetX, targetY, SPEED, RANGE, RATE_OF_FIRE, SIZE);
        }

    }

    public static final float RANGE = 200.0f;
    public static final float RATE_OF_FIRE =  4.0f;
    public static final float SPEED = 2.2f;
    public static final int SIZE = 10;
    public static final Sprite SPRITE = Sprite.PROJECTILE_WIZARD;

    public WizardProjectile(WizardProjectile.Specs specs) {
        super(specs, SPRITE);
    }
}
