package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {

    private Thread gameLoopThread;
    private final GamePanel gamePanel;
    private final GameWindow gameWindow;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;

    /** Constructor */
    public Game() {
        initializeClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    /** Synchronizes game entities update. */
    public void update() {
        player.update();
    }

    /** Synchronizes game  entities to render. */
    public void render(Graphics g) {
        player.render(g);
    }

    /** Returns instantiated player object. */
    public Player getPlayer() {
        return player;
    }

    /** Resets game state on focus lost. */
    public void windowFocusLost() {
        player.resetDirectionBooleans();
    }

    /**
     * This block of code is executed inside the game thread continuously.
     *
     * It holds the time per frame and time per update and calculates
     * when to re-render the game based on how much time has passed since
     * the last painting: deltaU and deltaF.
     *
     * The update ticks are responsible for synchronizing player movement,
     * keyboard events, mouse etc.
     *
     * The fps block repaints the game panel when delta frame exceeds
     * 1.0.
     *
    */
    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int updates = 0;
        int frames = 0;

        double delU = 0; // delta update time
        double delF = 0; // delta frame time

        long lastFpsCheck = System.currentTimeMillis();

        while (true) {
            long currentTime = System.nanoTime();

            delU += (currentTime - previousTime) / timePerUpdate;
            delF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            // Updating ticks
            if (delU >= 1.0) {
                update();
                updates++;
                delU--;
            }

            // Rendering
            if (delF >= 1.0) {
                gamePanel.repaint();
                frames++;
                delF--;
            }

            // Print FPS and UPS every second
            if (System.currentTimeMillis() - lastFpsCheck >= 1000) {
                lastFpsCheck = System.currentTimeMillis();

                System.out.println("FPS: " + frames + " | UPS: " + updates);

                frames = 0;
                updates = 0;
            }
        }
    }

    /** Starts game update loop on a separate thread. */
    private void startGameLoop() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    /** Responsible for initializing all entity classes. */
    private void initializeClasses() {
        player = new Player(200, 200);
    }

}
