package tile;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;

public class Tile {
    
    public BufferedImage image = null;
    public boolean collision = false;

    public Tile(String imageName, boolean collision) {
        this.collision = collision;
        getTileImage(imageName);
    }

    private void getTileImage(String imageName) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTile(Graphics2D g2d, int x, int y, int tileSize) {
        g2d.drawImage(image, x, y, tileSize, tileSize, null);
    }

}
