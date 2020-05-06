package com.github.fahjulian.rain.graphics;

import java.util.Random;

import com.github.fahjulian.rain.entity.mob.Player;
import com.github.fahjulian.rain.entity.projectile.Projectile;
import com.github.fahjulian.rain.level.tiles.Tile;
import com.github.fahjulian.rain.math.Position;

public class Screen {

    public static final int MAP_SIZE = 64;
    public static final int MAP_SIZE_MASK = MAP_SIZE - 1;

    public int width;
    public int height;
    public int[] pixels;

    public Position cameraPos = new Position(0, 0);

    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    
    private Random random = new Random();
    
    /**
     * Construct a new screen with the size of width * height
     * @param width Width of the screen
     * @param height Height of the screen
     */
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];

        for (int i = 0; i < tiles.length; i++)
        tiles[i] = random.nextInt(0x222222);
    }  

    /**
     * Make the screen fully black
     */
    public void clear() {
        for (int i = 0; i < pixels.length; i++) 
        pixels[i] = 0x000000;
    }

    /**
     * Render a sprite to the screen
     * @param pos Top left position of the sprite
     * @param sprite The sprite to render
     */
    public void renderSprite(Position pos, Sprite sprite) {
        pos.x -= cameraPos.x;
        pos.y -= cameraPos.y;
        for (int y = 0; y < sprite.SIZE; y++) {
            int pixelY = y + pos.y;
            if (pixelY < 0 || pixelY >= height) continue;
            for (int x = 0; x < sprite.SIZE; x++) {
                int pixelX = x + pos.x;
                if (pixelX < 0 || pixelX >= width) continue;

                int color = sprite.pixels[x + y * sprite.SIZE];
                if (color != 0xffff00ff)
                    pixels[pixelX + pixelY * width] = color;
            }
        }
    }

    public void renderTile(Position pos, Tile tile) {
        renderSprite(pos, tile.getSprite());
    }

    public void renderPlayer(Position pos, Player player) {
        renderSprite(pos, player.getSprite());
    }

    public void renderProjectile(Position pos, Projectile p) {
        renderSprite(pos, p.getSprite());
    }

    public void setCameraPos(int x, int y) {
        cameraPos.x = x;
        cameraPos.y = y;
    }
}
