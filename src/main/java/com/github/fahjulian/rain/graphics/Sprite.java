package com.github.fahjulian.rain.graphics;

public class Sprite {
    
    private final int SIZE;
    private int x;
    private int y;
    public int[] pixels;
    private Spritesheet sheet;

    public Sprite(int size, int row, int col, Spritesheet sheet) {
        this.SIZE = size;
        this.x = col * size;
        this.y = row * size;
        this.sheet = sheet;
        this.pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[this.x + x + (y + this.y) * sheet.SIZE];
            }
        }
    }
}
