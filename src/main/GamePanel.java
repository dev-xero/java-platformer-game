package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private final KeyboardInputs keyboardInputs;
    private final MouseInputs mouseInputs;

    private final String playerSpritesResource = "/player_sprites.png";
    private BufferedImage bufImage;
    private BufferedImage[][] animations;

    private float xDelta = 100;
    private float yDelta = 100;

    private int animationTick = 0;
    private int animationIndex = 0;
    private int animationSpeed = 15;


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

    private void loadAnimations() {

        animations = new BufferedImage[9][6];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[j].length; j++) {
                animations[i][j] = bufImage.getSubimage(j * 64, i * 40, 64, 40);
            }
        }

    }

    private void updateAnimationTick() {

        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;

            if (animationIndex >= 6) {
                animationIndex = 0;
            }
        }

    }

    public void changeXDelta(int dx) {
        xDelta += dx;
    }

    public void changeYDelta(int dy) {
        yDelta += dy;
    }

    public void setRectPosition(int x, int y) {
        xDelta = x;
        yDelta = y;
    }

    // Paint component is called everytime the ui updates
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        updateAnimationTick();
        g.drawImage(animations[1][animationIndex], (int) xDelta, (int) yDelta, 128, 80, null);

    }

}
