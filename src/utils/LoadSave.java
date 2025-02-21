package utils;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";

    public static BufferedImage GetSpriteAtlas(String filename) {
        BufferedImage image;

        try (InputStream inputStream = LoadSave.class.getResourceAsStream("/" + filename)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + PLAYER_ATLAS);
            }

            image = ImageIO.read(inputStream);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load image", e);
        }

        return image;
    }

    /**
     * This method computes the level data pixel by pixel according
     * to the red color channel value.
     * */
    public static int[][] GetLevelData() {
        int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage image = GetSpriteAtlas(LEVEL_ONE_DATA);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int value = color.getRed();

                // Clipping
                if (value >= 48) {
                    value = 0;
                }

                levelData[y][x] = value;
            }
        }

        return levelData;
    }

}
