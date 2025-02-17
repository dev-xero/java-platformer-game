package main;

import javax.swing.*;

public class GameWindow {

    public GameWindow(GamePanel gamePanel) {
        JFrame jFrame = new JFrame();

        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.add(gamePanel);
        jFrame.setVisible(true);
    }

}

