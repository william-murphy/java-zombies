package tile;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Tile {
    
    public BufferedImage image = null;
    public boolean collision = false;

    public Tile(String imageName, boolean collision) {
        this.collision = collision;
        getTileImage(imageName);
    }

    private void getTileImage(String imageName) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(String.format("/res/tile/%s.png", imageName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
