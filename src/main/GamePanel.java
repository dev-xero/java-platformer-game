package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {

    }

    // Paint component is called everytime the ui updates
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(0, 0, 200, 200);
    }

}
