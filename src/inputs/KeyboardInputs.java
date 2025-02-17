package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W:
                System.out.println("W pressed.");
                break;

            case KeyEvent.VK_A:
                System.out.println("A pressed.");
                break;

            case KeyEvent.VK_S:
                System.out.println("S pressed.");
                break;

            case KeyEvent.VK_D:
                System.out.println("D pressed.");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

}
