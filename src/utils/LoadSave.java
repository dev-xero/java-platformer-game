package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";

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

}
