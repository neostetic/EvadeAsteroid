package cz.polacek.engine.utils;

import cz.polacek.config.Config;

import java.util.Random;

public class Utils {

    public static int[] textSprites(String text) {
        char[] chars = text.toCharArray();
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ints[i] = (chars[i] - 32);
        }
        return ints;
    }

    public static double randomDouble(double min, double max) {
        return new Random().nextDouble(max - min + 1) + min;
    }

    public static int randomInteger(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static boolean randomBoolean() {
        return Math.random() < 0.5;
    }

    public static int[] randomSpawnLocation() {
        int[] ints = new int[2];
        if (randomBoolean()) {
            if (randomBoolean()) { ints[0] = -Config.TILE_COMPUTED; }
            else { ints[0] = Config.WINDOW_WIDTH; }
            ints[1] = randomInteger(0, Config.WINDOW_HEIGHT);
        } else {
            if (randomBoolean()) { ints[1] = -Config.TILE_COMPUTED; }
            else { ints[1] = Config.WINDOW_WIDTH; }
            ints[0] = randomInteger(0, Config.WINDOW_WIDTH);
        }
        return ints;
    }

    public static int[] randomArray(int length, int min, int max) {
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = randomInteger(min, max);
        }
        return ints;
    }

    public static int findInArray(String[] array, String target) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
