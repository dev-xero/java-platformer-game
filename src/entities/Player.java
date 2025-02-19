package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.*;

public class Player extends Entity {

    private String playerSpritesResource = "/player_sprites.png";
    private BufferedImage[][] animations;

    private int animationTick = 0;
    private int animationIndex = 0;
    private int animationSpeed = 15;

    private float playerSpeed = 2.0f;
    private boolean isMoving = false;
    private boolean isAttacking = false;
    private int playerAction = PlayerConstants.IDLE;
    private boolean left, up, right, down;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    /** Handles player animation and position updates. */
    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    /** Handles player graphics rendering. */
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, 256, 160, null);
    }

    /** Setters for direction booleans. */
    public void setLeft(boolean left) { this.left = left; }

    public void setUp(boolean up) { this.up = up; }

    public void setRight(boolean right) { this.right = right; }

    public void setDown(boolean down) { this.down = down; }

    /** Resets direction booleans to false. */
    public void resetDirectionBooleans() {
        left = false;
        up = false;
        right = false;
        down = false;
    }

    /** Setter for `isAttacking` field. */
    public void setIsAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
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
        isMoving = false;

        // Horizontal movements
        if (left && !right) {
            isMoving = true;
            x -= playerSpeed;
        } else if (right && !left) {
            isMoving = true;
            x += playerSpeed;
        }

        // Vertical movements
        if (up && !down) {
            isMoving = true;
            y -= playerSpeed;
        } else if (down && !up) {
            isMoving = true;
            y += playerSpeed;
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

            if (animationIndex >= PlayerConstants.GetSpriteCount(playerAction)) {
                animationIndex = 0;
                isAttacking = false;
            }
        }
    }

    /** Resets both animation tick and index. */
    private void resetAnimation() {
        animationTick = 0;
        animationIndex = 0;
    }

    /** Sets the appropriate player animation action. */
    private void setAnimation() {
        int startAnimation = playerAction;

        if (isMoving)
            playerAction = PlayerConstants.RUNNING;
        else
            playerAction = PlayerConstants.IDLE;

        if (isAttacking)
            playerAction = PlayerConstants.ATTACK_1;

        if (startAnimation != playerAction) {
            resetAnimation();
        }
    }

}
