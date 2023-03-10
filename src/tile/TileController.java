package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import game.GamePanel;

import java.io.IOException;

public class TileController {
    private GamePanel gp;
    private BufferedImage[] tileImages;
    public Tile[][] map;

    public TileController(GamePanel gp) {
        this.gp = gp;
        map = new Tile[gp.mapRows][gp.mapCols];
        initializeTileImages();
        initializeMap();
    }

    private void initializeTileImages() {
        tileImages = new BufferedImage[2];
        try {
            tileImages[0] = ImageIO.read(getClass().getResourceAsStream("/res/tile/cobblestone.png"));
            tileImages[1] = ImageIO.read(getClass().getResourceAsStream("/res/tile/wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private void initializeMap() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/res/map/map.txt")));
            String line = reader.readLine();
            int row = 0;
            while (line != null) {
                String[] splitLine = line.split(" ");
                for (int col=0; col < gp.mapCols; col++) {
                    map[col][row] = new Tile(Integer.parseInt(splitLine[col]), col, row, gp.tileSize);
                }
                line = reader.readLine();
                row++;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTiles() {
        
    }

    public void drawTiles(Graphics2D g2d) {
        for (int row=0; row<gp.mapRows; row++) {
            for (int col=0; col<gp.mapCols; col++) {
                //only render visible tiles to improve rendering efficiency
                if (gp.camera.shouldRenderTile(col, row)) {
                    g2d.drawImage(tileImages[map[col][row].imageIndex], gp.camera.calculateTileX(col), gp.camera.calculateTileY(row), gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }

}
