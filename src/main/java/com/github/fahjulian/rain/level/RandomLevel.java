package com.github.fahjulian.rain.level;

import java.util.Random;

public class RandomLevel extends Level {

    private static final Random r = new Random();

    /**
     * Construct a Random level with a given map size
     * @param rows The amount of rows of the map 
     * @param cols The amount of cols of the map
     */
    public RandomLevel(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    protected void generateLevel() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                tiles[x + y * cols] = r.nextInt(4);
            }
        }
    }
}
