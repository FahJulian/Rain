package com.github.fahjulian.rain.entity.mob;

import com.github.fahjulian.rain.Direction;
import com.github.fahjulian.rain.Game;
import com.github.fahjulian.rain.entity.projectile.Projectile;
import com.github.fahjulian.rain.entity.projectile.WizardProjectile;
import com.github.fahjulian.rain.math.Position;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.graphics.Sprite;
import com.github.fahjulian.rain.input.Keyboard;
import com.github.fahjulian.rain.input.Mouse;
import com.github.fahjulian.rain.level.Level;
import com.github.fahjulian.rain.math.Vector;

public class Player extends Mob {
    
    private int updateCount = 0;
    private boolean shoot;

    public Player(Position pos, Level level) {
        super(level);
        this.pos = pos;

        sprite = Sprite.PLAYER_FRONT;
    }
    
    @Override
    public void update() {
        if (Mouse.left) shoot = true;

        if (shoot && updateCount % 10 == 0) {
            Position target = Game.cameraPos.add(Mouse.pos);
            shoot(target);
            shoot = false;
        }

        Vector vel = new Vector(0, 0);
        if (Keyboard.w) vel.y--;
        if (Keyboard.a) vel.x--;
        if (Keyboard.s) vel.y++;
        if (Keyboard.d) vel.x++;

        move(vel);

        updateCount++;
        if (updateCount % 10 == 0) 
            animate();
    }

    @Override
    public void render(Screen screen) {
        screen.renderPlayer(new Position(pos.x, pos.y), this);
    }

    private void shoot(Position target) {
        float angle = (float) Math.atan2(getCenter().y - target.y, getCenter().x - target.x);
        Projectile p = new WizardProjectile(pos.toFloat(), angle, Sprite.PROJECTILE_WIZARD);
        projectiles.add(p);
        level.add(p);
    }

    private void animate() {
        if (dir == null || dir == Direction.SOUTH) {
            sprite = Sprite.PLAYER_FRONT;
            return;
        } else if (dir == Direction.NORTH) {
            sprite = Sprite.PLAYER_BACK;
        } else if (dir == Direction.WEST || dir == Direction.NORTHWEST || dir == Direction.SOUTHWEST) {
            if (sprite == Sprite.PLAYER_LEFT_1) sprite = Sprite.PLAYER_LEFT_2;
            else sprite = Sprite.PLAYER_LEFT_1;
        } else if (dir == Direction.EAST || dir == Direction.NORTHEAST || dir == Direction.SOUTHEAST) {
            if (sprite == Sprite.PLAYER_RIGHT_1) sprite = Sprite.PLAYER_RIGHT_2;
            else sprite = Sprite.PLAYER_RIGHT_1;
        }
    }
}
