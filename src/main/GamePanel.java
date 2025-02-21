package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final Game game;
    private final KeyboardInputs keyboardInputs;
    private final MouseInputs mouseInputs;

    public GamePanel(Game game) {
        this.game = game;

        keyboardInputs = new KeyboardInputs(this);
        mouseInputs = new MouseInputs(this);

        setPanelSize(Game.GAME_WIDTH, Game.GAME_HEIGHT);

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /** Paints game components on every render. */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

    /** Updates game on every tick. */
    public void updateGame() {

    }

    /** Returns instantiated game object. */
    public Game getGame() {
        return game;
    }

    /** Sets panel size dimensions to the specified width x height. */
    private void setPanelSize(int width, int height) {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        System.out.println("Size: " + width + " x " + height);
    }

}
