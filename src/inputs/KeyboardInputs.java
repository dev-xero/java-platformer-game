package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private final GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.changeYDelta(-5);
                break;

            case KeyEvent.VK_A:
                gamePanel.changeXDelta(5);
                break;

            case KeyEvent.VK_S:
                gamePanel.changeYDelta(5);
                break;

            case KeyEvent.VK_D:
                gamePanel.changeXDelta(-5);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

}
