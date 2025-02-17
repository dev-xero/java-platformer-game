package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    // Positioning and direction
    private float xDelta = 100, yDelta = 100;
    private float xDirection = 0.003f, yDirection = 0.003f;

    // Random colorization
    private final Random random;
    private Color color = new Color(150, 20, 90);

    // Frame rate
    private int frames = 0;
    private long lastFrameCheck = 0;

    // Instantiates new game panel
    public GamePanel() {
        KeyboardInputs keyboardInputs = new KeyboardInputs(this);
        MouseInputs mouseInputs = new MouseInputs(this);

        random = new Random();

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    // Increments x position by dx
    public void changeXDelta(int dx) {
        this.xDelta += dx;
    }

    // Increments y position by dy
    public void changeYDelta(int dy) {
        this.yDelta += dy;
    }

    // Sets rectangle position to (x, y) coordinates
    public void setRectPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    // Generates a random hex triplet color
    public Color getRandomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r, g, b);
    }

    // Updates rectangle direction and colors
    public void updateRectangle() {
        this.xDelta += xDirection;
        this.yDelta += yDirection;

        if (0 > this.xDelta || this.xDelta > 400) {
            xDirection *= -1;
            color = getRandomColor();
        }

        if (0 > this.yDelta || this.yDelta > 400) {
            yDirection *= -1;
            color = getRandomColor();
        }
    }

    // Paint component is called everytime the ui updates
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateRectangle();

        g.setColor(color);
        g.fillRect((int)xDelta,  (int)yDelta, 200, 50);

        // Calculate frames per second
        frames++;
        if (System.currentTimeMillis() - lastFrameCheck >= 1000) {
            lastFrameCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames);

            frames = 0;
        }

        repaint();
    }

}
