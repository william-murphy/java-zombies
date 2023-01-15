import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

    //screen dimensions
    private final int originalTileSize = 16;
    private final int scale = 3;
    public final int tileSize = 16 * 3;

    private final int maxColumns = 16;
    private final int maxRows = 12;
    private final int width = maxColumns * tileSize;
    private final int height = maxRows * tileSize;

    int FPS = 60;

    KeyHandler kh = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, kh);

    public GamePanel() {
        this.setPreferredSize(new Dimension(width, height));
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

        player.draw(g2d);

        g2d.dispose();
    }

}