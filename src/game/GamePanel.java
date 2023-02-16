package game;

import javax.swing.JPanel;

import entity.Player;
import tile.TileController;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

    //screen dimensions
    public final int tileSize = 64;

    public final int screenCols = 16;
    public final int screenRows = 12;
    public final int screenWidth = screenCols * tileSize;
    public final int screenHeight = screenRows * tileSize;

    public final int mapRows = 48;
    public final int mapCols = 48;
    public final int worldWidth = mapCols * tileSize;
    public final int worldHeight = mapRows * tileSize;
    public final int playerSpawnX = (worldWidth / 2) - (tileSize / 2);
    public final int playerSpawnY  = (worldHeight / 2) - (tileSize / 2);

    private int FPS = 60;
    private Thread gameThread;
    private KeyHandler kh = new KeyHandler();
    public TileController tc = new TileController(this);
    public Player player = new Player(this, kh);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //calls the run method on gameThread
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) { //game loop

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta > 1) {
                update();
                repaint(); //calls paintComponent
                delta--;
            }

        }

    }

    public void update() {
        player.move();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        tc.drawTiles(g2d);

        player.draw(g2d);

        g2d.dispose();
    }

}