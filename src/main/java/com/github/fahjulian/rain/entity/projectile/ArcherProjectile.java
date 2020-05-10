package com.github.fahjulian.rain.entity.projectile;

import com.github.fahjulian.rain.graphics.Sprite;

public class ArcherProjectile extends Projectile {

    public static class Specs extends Projectile.Specs {

        public Specs(float originX, float originY, float targetX, float targetY) {
            super(originX, originY, targetX, targetY, SPEED, RANGE, RATE_OF_FIRE, SIZE);
        }
    }

    public static final float RANGE = 200.0f;
    public static final float RATE_OF_FIRE = 6.0f;
    public static final float SPEED = 1.8f;
    public static final int SIZE = 14;
    public static final Sprite SPRITE = Sprite.PROJECTILE_ARCHER;

    public ArcherProjectile(ArcherProjectile.Specs specs) {
        super(specs, SPRITE);
    }
}
