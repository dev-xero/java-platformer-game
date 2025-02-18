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
    private BufferedImage bufImg;
    private float xDelta = 100;
    private float yDelta = 100;

    // Instantiates new game panel
    public GamePanel() {
        keyboardInputs = new KeyboardInputs(this);
         mouseInputs = new MouseInputs(this);

         importImage();
         setPanelSize();

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    // Sets preferred panel size
    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    // Loads image from resource folder into buffer
    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            if (is == null) {
                throw new IOException("Image not found.");
            }
            bufImg = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Increments x position by dx
    public void changeXDelta(int dx) {
        xDelta += dx;
    }

    // Increments y position by dy
    public void changeYDelta(int dy) {
        yDelta += dy;
    }

    // Paint component is called everytime the ui updates
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(bufImg, 0, 0, null);
    }

}
