package main;

public class Game implements Runnable {

    private final GamePanel gamePanel;
    private final GameWindow gameWindow;

    private Thread gameLoopThread;
    private final int FPS_SET = 120;


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

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;

        long lastFrameTime = System.nanoTime();
        long lastFpsCheck = System.currentTimeMillis();

        int frames = 0;

        while (true) {
            long now = System.nanoTime();

            if (now - lastFrameTime >= timePerFrame) {
                gamePanel.repaint();
                lastFrameTime = now;

                frames++;
            }

            // Print FPS every second
            if (System.currentTimeMillis() - lastFpsCheck >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                lastFpsCheck = System.currentTimeMillis();
            }
        }
    }

}
