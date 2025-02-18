package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.*;

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
                gamePanel.setDirection(Directions.UP);
                break;

            case KeyEvent.VK_A:
                gamePanel.setDirection(Directions.LEFT);
                break;

            case KeyEvent.VK_S:
                gamePanel.setDirection(Directions.DOWN);
                break;

            case KeyEvent.VK_D:
                gamePanel.setDirection(Directions.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                gamePanel.setIsMoving(false);
                break;
        }
    }

}
