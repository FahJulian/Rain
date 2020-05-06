package com.github.fahjulian.rain.graphics;

import java.util.Arrays;

import com.github.fahjulian.rain.math.GridPosition;

public class Sprite {
    
    public final int SIZE;
    public int[] pixels;
    private Spritesheet sheet;
    private GridPosition sheetPosition;

    public static Sprite 
        VOID = new Sprite(16, 0x222222),
        AIR = new Sprite(16, 0x00ccff),
        CLOUD = new Sprite(16, 0xeeeeee),
        GRASS = new Sprite(16, 0, 0, Spritesheet.TILES),
        FLOWER = new Sprite(16, 0, 1, Spritesheet.TILES),
        STONE = new Sprite(16, 0, 2, Spritesheet.TILES),
        BRICK_RED = new Sprite(16, 2, 0, Spritesheet.TILES),
        BRICK_GREY = new Sprite(16, 3, 0, Spritesheet.TILES),
        STEM = new Sprite(16, 2, 2, Spritesheet.TILES),
        LEAVES = new Sprite(16, 1, 2, Spritesheet.TILES);

    public static Sprite 
        PLAYER_FRONT = new Sprite(16, 15, 1, Spritesheet.TILES),
        PLAYER_BACK = new Sprite(16, 15, 0, Spritesheet.TILES),
        PLAYER_RIGHT_1 = new Sprite(16, 15, 2, Spritesheet.TILES),
        PLAYER_RIGHT_2 = new Sprite(16, 15, 3, Spritesheet.TILES),
        PLAYER_LEFT_1 = new Sprite(16, 15, 2, Spritesheet.TILES).flipHorizontally(),
        PLAYER_LEFT_2 = new Sprite(16, 15, 3, Spritesheet.TILES).flipHorizontally();
    
    public static Sprite
        PROJECTILE_WIZARD = new Sprite(16, 0, 0, Spritesheet.PROJECTILES_WIZZARD);
    /**
     * Constructs a new Sprite from a Spritesheet
     * @param size Width & Height of the Sprite
     * @param row Row of the sprite on the {@code Spritesheet}
     * @param col Col of the sprite on the {@code Spritesheet}
     * @param sheet The spritesheet to load the sprite from
     */
    protected Sprite(int size, int row, int col, Spritesheet sheet) {
        this.SIZE = size;
        this.sheetPosition = new GridPosition(row * size, col * size);
        this.sheet = sheet;
        this.pixels = new int[SIZE * SIZE];
        load();
    }

    /**
     * Construct a new Sprite of only one color
     * @param size The size of the Sprite
     * @param color The color of the Sprite
     */
    public Sprite(int size, int color) {
        this.SIZE = size;
        this.pixels = new int[size * size];
        this.fill(color);
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[sheetPosition.col + x + (y + sheetPosition.row) * sheet.SIZE];
            }
        }
    }

    private void fill(int color) {
        for (int i = 0; i < pixels.length; i++) 
            pixels[i] = color;
    }

    private Sprite flipHorizontally() {
        for (int y = 0; y < SIZE; y++) {
            int[] row = Arrays.copyOfRange(pixels, y * SIZE, (y + 1) * SIZE);
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = row[SIZE - (1 + x)];
            }
        }
        return this;
    }
}
