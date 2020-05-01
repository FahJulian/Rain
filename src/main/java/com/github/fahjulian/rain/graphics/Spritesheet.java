package com.github.fahjulian.rain.graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Spritesheet {

    private String path;
    public final int SIZE;
    public int[] pixels;

    public Spritesheet(String path, int size) {
        this.path = path;
        this.SIZE = size;
        this.pixels = new int[size * size];
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
            int width = image.getWidth();
            int height = image.getHeight();
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
