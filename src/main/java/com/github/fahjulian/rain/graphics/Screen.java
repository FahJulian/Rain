package com.github.fahjulian.rain.graphics;

public class Screen {

  private int width;
  private int height;
  public int[] pixels;

  public Screen(int width, int height)
  {
    this.width = width;
    this.height = height;
    this.pixels = new int[width * height];
  }

  public void clear() {
    for (int i = 0; i < pixels.length; i++) 
      pixels[i] = 0;
  }

  public void render() {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = pixels[y * width + x] = 0xff00ff;
      }
    }
  }
}
