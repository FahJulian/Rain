package com.github.fahjulian.rain.entity.projectile;

import com.github.fahjulian.rain.entity.Entity;
import com.github.fahjulian.rain.graphics.Sprite;

public class ArcherProjectile extends Projectile {

    public static class Specs extends Projectile.Specs<ArcherProjectile> {

        public Specs(float originX, float originY, float targetX, float targetY) {
            super(originX, originY, targetX, targetY, SPEED, RANGE, RATE_OF_FIRE, SIZE);
        }

        @Override
        public Class<? extends Entity> getType() {
            return ArcherProjectile.class;
        }

    }

    public static final float RANGE = 200.0f;
    public static final float RATE_OF_FIRE = 6.0f;
    public static final float SPEED = 1.8f;
    public static final int SIZE = 14;
    public static final Sprite SPRITE = Sprite.PROJECTILE_ARCHER;

    public ArcherProjectile(Entity.Specs<ArcherProjectile> specs) {
        super(specs, SPRITE);
    }
}
