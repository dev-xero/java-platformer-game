package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    private float xDelta = 100, yDelta = 100;
    private float xDirection = 1.0f, yDirection = 1.0f;
    private final Random random;
    private Color color = new Color(150, 20, 90);

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
        xDelta += dx;
    }

    // Increments y position by dy
    public void changeYDelta(int dy) {
        yDelta += dy;
    }

    // Sets rectangle position to (x, y) coordinates
    public void setRectPosition(int x, int y) {
        xDelta = x;
        yDelta = y;
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
        xDelta += xDirection;
        if (0 > xDelta || xDelta > 400) {
            xDirection *= -1;
            color = getRandomColor();
        }

        yDelta += yDirection;
        if (0 > yDelta || yDelta > 400) {
            yDirection *= -1;
            color = getRandomColor();
        }
    }

    // Paint component is called everytime the ui updates
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(color);
        g.fillRect((int) xDelta,  (int) yDelta, 200, 50);
    }

}
