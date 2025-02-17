package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        KeyboardInputs keyboardInputs = new KeyboardInputs();
        MouseInputs mouseInputs = new MouseInputs();

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    // Paint component is called everytime the ui updates
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(0, 0, 200, 200);
    }

}
