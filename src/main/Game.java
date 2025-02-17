package main;

public class Game implements Runnable {

    private final GamePanel gamePanel;
    private final GameWindow gameWindow;

    private Thread gameLoopThread;
    private final int FPS_SET = 120;

    // Instantiates a new class
    public Game() {
        this.gamePanel = new GamePanel();
        this.gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    // Separate thread for updating the game loop
    private void startGameLoop() {
        this.gameLoopThread = new Thread(this);
        this.gameLoopThread.start();
    }

    // This task is performed in the game loop thread
    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        long lastFrameTime = System.nanoTime();
        long lastFpsCheck = System.currentTimeMillis();
        int frames = 0;

        while (true) {
            long now = System.nanoTime();

            // Repaint UI after time per frame
            if (now - lastFrameTime >= timePerFrame) {
                gamePanel.updateRectangle();
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

            // Prevent overworking the CPU :(
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

}
