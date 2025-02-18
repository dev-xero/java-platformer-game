package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.*;

public class GamePanel extends JPanel {

    private final String playerSpritesResource = "/player_sprites.png";
    private final KeyboardInputs keyboardInputs;
    private final MouseInputs mouseInputs;

    private BufferedImage bufImage;
    private BufferedImage[][] animations;

    private float xDelta = 100;
    private float yDelta = 100;

    private int animationTick = 0;
    private int animationIndex = 0;
    private int animationSpeed = 15;

    private int playerAction = PlayerConstants.IDLE;
    private int playerDirection = -1;
    private boolean isMoving = false;


    public GamePanel() {

        keyboardInputs = new KeyboardInputs(this);
        mouseInputs = new MouseInputs(this);

        importImage();
        loadAnimations();
        setPanelSize();

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    // Loads image from resource folder into buffer
    private void importImage() {
        try (InputStream inputStream = getClass().getResourceAsStream(playerSpritesResource)){

            if (inputStream == null) {
                throw new IOException("Resource not found: " + playerSpritesResource);
            }

            bufImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image", e);
        }
    }

    public void setDirection(int direction) {
        playerDirection = direction;
        isMoving = true;
    }

    public void setIsMoving(boolean moving) {
        isMoving = moving;
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[j].length; j++) {
                animations[i][j] = bufImage.getSubimage(j * 64, i * 40, 64, 40);
            }
        }
    }

    private void updatePosition() {
        if (isMoving) {
            switch (playerDirection) {
                case Directions.LEFT:
                    xDelta -= 5;
                    break;
                case Directions.UP:
                    yDelta -= 5;
                    break;
                case Directions.RIGHT:
                    xDelta += 5;
                    break;
                case Directions.DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;

            if (animationIndex >= PlayerConstants.GetSpriteCount(playerAction)) {
                animationIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if (isMoving) {
            playerAction = PlayerConstants.RUNNING;
        } else {
            playerAction = PlayerConstants.IDLE;
        }
    }

    @Override
    // Paint component is called everytime the UI updates
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimationTick();
        setAnimation();
        updatePosition();

        g.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta, 256, 160, null);
    }

}
