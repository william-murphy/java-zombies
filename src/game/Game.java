package game;

import main.Window;
import tile.TileController;
import entity.EntityController;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Game extends JPanel implements Runnable {

    Window window;

    //screen dimensions
    public static final int tileSize = 64;
    public static final int screenCols = 16;
    public static final int screenRows = 12;
    public static final int screenWidth = screenCols * tileSize;
    public static final int screenHeight = screenRows * tileSize;

    static {
        TileController.setMapDimensions();
        TileController.initializeMap();
        TileController.loadImages();
        EntityController.loadImages();
        Hud.loadImages();
    }

    public static final int FPS = 60;
    public int tick = 0;
    private static final int ticksBeforeReset = 10 * FPS; // 10 seconds

    private Thread gameThread;
    public final Random random = new Random();

    public KeyHandler keyHandler = new KeyHandler(this);
    public TileController tileController = new TileController(this);
    public EntityController entityController = new EntityController(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Camera camera = new Camera(this);
    public Hud hud = new Hud(this);

    public int round = 1;

    public Game(Window window) {
        this.window = window;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }

    @Override
    public void run() {

        double tickInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) { //game loop

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / tickInterval;
            lastTime = currentTime;

            if (delta > 1) {
                tick();
                delta--;
            }

        }

    }

    private void tick() {
        tick++;
        update();
        repaint();
        if (tick >= ticksBeforeReset) {
            tick = 0;
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

        hud.drawHud(g2d);

        g2d.dispose();
    }

    //called from the window class to start / stop the game

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //calls the run method on gameThread
    }

    public void stopGameThread() {
        gameThread = null;
    }

}