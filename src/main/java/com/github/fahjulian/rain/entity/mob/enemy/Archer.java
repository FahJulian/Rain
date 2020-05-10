package com.github.fahjulian.rain.entity.mob.enemy;

import com.github.fahjulian.rain.Game;
import com.github.fahjulian.rain.entity.mob.Mob;
import com.github.fahjulian.rain.entity.projectile.Projectile;
import com.github.fahjulian.rain.entity.projectile.ArcherProjectile;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.graphics.Sprite;
import com.github.fahjulian.rain.math.Position;
import com.github.fahjulian.rain.math.Vector;

public class Archer extends Mob {

    public static class Specs extends Mob.Specs {
        public Specs(int x, int y) {
            super(x, y);
        }
    }

    public Archer(Archer.Specs specs) {
        super(specs);
        sprite = Sprite.ARCHER;
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite(pos, sprite);
    }

    @Override
    public void update() {
        super.update();
        
        updateCount++;
        Vector vel = new Vector(0, 0);
        move(vel);

        if (updateCount % (int) (60.0f / ArcherProjectile.RATE_OF_FIRE) == 0) {
            Position target = Game.get().getPlayer().getCenter().clone();
            shoot(target);
        }

        for (int i = 0; i < projectiles.size(); i++)
            if (projectiles.get(i).isRemoved()) projectiles.remove(i);
    }

    private void shoot(Position target) {
        Projectile p = new ArcherProjectile(new ArcherProjectile.Specs(getCenter().x, getCenter().y, target.x, target.y));
        p.setLevel(level);
        projectiles.add(p);
    }
    
}
