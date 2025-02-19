package inputs;

import main.Game;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.*;

public class KeyboardInputs implements KeyListener {

    private final GamePanel gamePanel;
    private final Game game;

    /** Constructor */
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.game = this.gamePanel.getGame();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    /**
     * Handles keyboard press event, particularly player
     * movement keys.
     *
     * @param keyEvent Keyboard press event.
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W:
                game.getPlayer().setUp(true);
                break;

            case KeyEvent.VK_A:
                game.getPlayer().setLeft(true);
                break;

            case KeyEvent.VK_S:
                game.getPlayer().setDown(true);
                break;

            case KeyEvent.VK_D:
                game.getPlayer().setRight(true);
                break;
        }
    }

    /**
     * Handles key release events.
     *
     * @param keyEvent Keyboard release event.
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W:
                game.getPlayer().setUp(false);
                break;

            case KeyEvent.VK_A:
                game.getPlayer().setLeft(false);
                break;

            case KeyEvent.VK_S:
                game.getPlayer().setDown(false);
                break;

            case KeyEvent.VK_D:
                game.getPlayer().setRight(false);
                break;
        }
    }

}
