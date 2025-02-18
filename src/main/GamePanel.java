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

    private BufferedImage bufImage;
    private BufferedImage[] idleAnimation;

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

        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        if (is != null) {
            try {
                bufImage = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadAnimations() {

        idleAnimation = new BufferedImage[5];

        for (int i = 0; i < idleAnimation.length; i++) {
            idleAnimation[i] = bufImage.getSubimage(i * 64, 0, 64, 40);
        }
    }

    private void updateAnimationTick() {

        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;

            // Idle animation length is 5
            if (animationIndex >= 5) {
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

        g.drawImage(idleAnimation[animationIndex], (int) xDelta, (int) yDelta, 128, 80, null);
    }

}
