package com.github.fahjulian.rain.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.io.File;

public class Spritesheet {

    public static Spritesheet TILES = new Spritesheet("src/main/resources/textures/spritesheet.png", 256);
    public static Spritesheet PROJECTILES_WIZZARD = new Spritesheet("src/main/resources/textures/projectiles/wizzard.png", 48);
    public static Spritesheet ARCHER = new Spritesheet("src/main/resources/textures/archer.png", 48);

    private String path;
    public final int SIZE;
    public int[] pixels;

    /**
     * Constructs a Spritesheet instance from a Spritesheet image file
     * @param path Path of the image file
     * @param size Width & Hight of the Image
     */
    public Spritesheet(String path, int size) {
        this.path = path;
        this.SIZE = size;
        this.pixels = new int[size * size];
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int width = image.getWidth();
            int height = image.getHeight();
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
