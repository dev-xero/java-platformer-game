package entities;

import utils.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity {

    private String playerSpritesResource = "/player_sprites.png";
    private BufferedImage[][] animations;

    private int animationTick = 0;
    private int animationIndex = 0;
    private int animationSpeed = 15;

    private int playerAction = Constants.PlayerConstants.IDLE;
    private int playerDirection = -1;
    private boolean isMoving = false;

    public Player(float x, float y) {

        super(x, y);
        loadAnimations();

    }

    /** Handles player animation and position updates. */
    public void update() {
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    /** Handles player graphics rendering. */
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, 256, 160, null);
    }

    /** Handles player movement direction setting. */
    public void setDirection(int direction) {
        playerDirection = direction;
        isMoving = true;
    }

    /** Handles player movement state. */
    public void setIsMoving(boolean moving) {
        isMoving = moving;
    }

    /** Loads image from resource folder into buffer. */
    private void loadAnimations() {
        try (InputStream inputStream = getClass().getResourceAsStream(playerSpritesResource)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + playerSpritesResource);
            }

            BufferedImage bufImage = ImageIO.read(inputStream);

            animations = new BufferedImage[9][6];

            for (int i = 0; i < animations.length; i++) {
                for (int j = 0; j < animations[j].length; j++) {
                    animations[i][j] = bufImage.getSubimage(j * 64, i * 40, 64, 40);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image", e);
        }
    }

    /** Handles player position updates. */
    private void updatePosition() {
        if (isMoving) {
            switch (playerDirection) {
                case Constants.Directions.LEFT:
                    x -= 5;
                    break;
                case Constants.Directions.UP:
                    y -= 5;
                    break;
                case Constants.Directions.RIGHT:
                    x += 5;
                    break;
                case Constants.Directions.DOWN:
                    y += 5;
                    break;
            }
        }
    }

    /**
     * Handles animation play-through. On each animation tick,
     * we cycle through the selected animation.
     */
    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;

            if (animationIndex >= Constants.PlayerConstants.GetSpriteCount(playerAction)) {
                animationIndex = 0;
            }
        }
    }

    /** Sets the appropriate player animation action. */
    private void setAnimation() {
        if (isMoving) {
            playerAction = Constants.PlayerConstants.RUNNING;
        } else {
            playerAction = Constants.PlayerConstants.IDLE;
        }
    }

}
