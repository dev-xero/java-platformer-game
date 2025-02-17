package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int xDelta, yDelta = 0;

    public GamePanel() {
        KeyboardInputs keyboardInputs = new KeyboardInputs(this);
        MouseInputs mouseInputs = new MouseInputs();

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int dx) {
        this.xDelta += dx;
    }

    public void changeYDelta(int dy) {
        this.yDelta += dy;
    }

    // Paint component is called everytime the ui updates
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(10 + xDelta, 10 + yDelta, 200, 200);
    }

}
