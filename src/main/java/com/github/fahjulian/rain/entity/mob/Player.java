package com.github.fahjulian.rain.entity.mob;

import com.github.fahjulian.rain.Direction;
import com.github.fahjulian.rain.Position;
import com.github.fahjulian.rain.graphics.Screen;
import com.github.fahjulian.rain.graphics.Sprite;
import com.github.fahjulian.rain.input.Keyboard;
import com.github.fahjulian.rain.level.Level;

public class Player extends Mob {
    
    private Keyboard keyboard;
    private int animationCount = 0;

    public Player(Position pos, Level level, Keyboard keyboard) {
        super(level);
        this.pos = pos;
        this.keyboard = keyboard;

        sprite = Sprite.playerFront;
    }
    
    @Override
    public void update() {
        int velX = 0, velY = 0;
        if (keyboard.w) velY--;
        if (keyboard.a) velX--;
        if (keyboard.s) velY++;
        if (keyboard.d) velX++;

        move(velX, velY);

        animationCount++;
        if (animationCount % 10 == 0) 
            animate();
    }

    @Override
    public void render(Screen screen) {
        screen.renderPlayer(new Position(pos.x - 16, pos.y - 16), sprite);
    }

    private void animate() {
        if (dir == null || dir == Direction.SOUTH) {
            sprite = Sprite.playerFront;
            return;
        } else if (dir == Direction.NORTH) {
            sprite = Sprite.playerBack;
        } else if (dir == Direction.WEST || dir == Direction.NORTHWEST || dir == Direction.SOUTHWEST) {
            if (sprite == Sprite.playerLeft1) sprite = Sprite.playerLeft2;
            else sprite = Sprite.playerLeft1;
        } else if (dir == Direction.EAST || dir == Direction.NORTHEAST || dir == Direction.SOUTHEAST) {
            if (sprite == Sprite.playerRight1) sprite = Sprite.playerRight2;
            else sprite = Sprite.playerRight1;
        }
    }
}
