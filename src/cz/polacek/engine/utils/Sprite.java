package cz.polacek.engine.utils;

import cz.polacek.config.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {

    private static BufferedImage spriteSheet;
    private static BufferedImage textSheet;
    private static final int TILE_SIZE = Config.TILE_SIZE;

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("src\\cz\\polacek\\assets\\" + Config.COLOR_PALETTE.file + "\\entity.png");
        }

        try {
            return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        } catch (Exception e) {
            return spriteSheet.getSubimage(0, 5 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    public static BufferedImage getTextSprite(int xGrid, int yGrid) {

        if (textSheet == null) {
            textSheet = loadSprite("src\\cz\\polacek\\assets\\" + Config.COLOR_PALETTE.file + "\\font.png");
        }

        try {
            return textSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        } catch (Exception e) {
            return textSheet.getSubimage(TILE_SIZE, 3 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

    }

}
