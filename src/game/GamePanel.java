package game;

import javax.swing.JPanel;

import tile.TileController;
import entity.EntityController;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

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
    public final Random random = new Random();

    public int round = 1;
    public KeyHandler keyHandler = new KeyHandler();
    public TileController tileController = new TileController(this);
    public EntityController entityController = new EntityController(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Camera camera = new Camera(this, entityController.player);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
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
        entityController.updateEntities();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        tileController.drawTiles(g2d);

        entityController.drawEntities(g2d);

        g2d.dispose();
    }

}