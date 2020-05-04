package com.github.fahjulian.rain.level;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class SpawnLevel extends Level {
    
    public SpawnLevel(String path) {
        super(path);
    }

    @Override
    protected void load() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.cols = image.getWidth();
        this.rows = image.getHeight();
        tiles = new int[rows * cols];
        image.getRGB(0, 0, cols, rows, tiles, 0, cols);

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = rgbaToTileID(tiles[i]);
        }
    }
}
