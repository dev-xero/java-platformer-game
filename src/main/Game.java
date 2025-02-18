package main;

public class Game implements Runnable {

    private final GamePanel gamePanel;
    private final GameWindow gameWindow;
    private Thread gameLoopThread;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;


    public Game() {

        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);

        gamePanel.requestFocus();

        startGameLoop();

    }

    private void startGameLoop() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    public void update() {
        gamePanel.updateGame();
    }

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

}
