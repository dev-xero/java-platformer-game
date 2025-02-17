package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int xDelta, yDelta = 100;

    public GamePanel() {
        KeyboardInputs keyboardInputs = new KeyboardInputs(this);
        MouseInputs mouseInputs = new MouseInputs(this);

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int dx) {
        this.xDelta += dx;
        repaint();
    }

    public void changeYDelta(int dy) {
        this.yDelta += dy;
        repaint();
    }

    public void setRectPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;

        repaint();
    }

    // Paint component is called everytime the ui updates
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(xDelta,  yDelta, 200, 200);
    }

}
