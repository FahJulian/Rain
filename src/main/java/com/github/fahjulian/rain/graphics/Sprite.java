package com.github.fahjulian.rain.graphics;

public class Sprite {
    
    public final int SIZE;
    private int x;
    private int y;
    public int[] pixels;
    private Spritesheet sheet;

    protected static Sprite 
        voidSprite = new Sprite(16, 0x222222),
        grass = new Sprite(16, 0, 0, Spritesheet.tiles),
        stone = new Sprite(16, 0, 1, Spritesheet.tiles);

    /**
     * Constructs a new Sprite from a Spritesheet
     * @param size Width & Height of the Sprite
     * @param row Row of the sprite on the {@code Spritesheet}
     * @param col Col of the sprite on the {@code Spritesheet}
     * @param sheet The spritesheet to load the sprite from
     */
    protected Sprite(int size, int row, int col, Spritesheet sheet) {
        this.SIZE = size;
        this.x = col * size;
        this.y = row * size;
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
                pixels[x + y * SIZE] = sheet.pixels[this.x + x + (y + this.y) * sheet.SIZE];
            }
        }
    }

    private void fill(int color) {
        for (int i = 0; i < pixels.length; i++) 
            pixels[i] = color;
    }
}
