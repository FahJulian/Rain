package com.github.fahjulian.rain.graphics;

import java.util.Random;

public class Screen {

  public static final int MAP_SIZE = 64;
  public static final int MAP_SIZE_MASK = MAP_SIZE - 1;

  private int width;
  private int height;
  public int[] pixels;

  public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
  
  private Random random = new Random();

  public Screen(int width, int height)
  {
    this.width = width;
    this.height = height;
    this.pixels = new int[width * height];

    for (int i = 0; i < tiles.length; i++)
      tiles[i] = random.nextInt(0x222222);
  }

  public void clear() {
    for (int i = 0; i < pixels.length; i++) 
      pixels[i] = 0;
  }

  public void render(int xOffset, int yOffset) {
    for (int y = 0; y < height; y++) {
      int modedY = y + yOffset;
      for (int x = 0; x < width; x++) {
        int movedX = x + xOffset;
        int tileIndex = (((modedY >> 4) & MAP_SIZE_MASK) * MAP_SIZE_MASK) + ((movedX >> 4) & MAP_SIZE_MASK);
        pixels[y * width + x] = tiles[tileIndex];
      }  
    }
  }
}
