package tile;

import game.GamePanel;

import java.awt.Graphics2D;

public class TileManager {
    GamePanel gp;
    Tile[] tiles = new Tile[1];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        setTiles();
    }

    public void setTiles() {
        tiles[0] = new Tile("/res/tile/cobblestone.png", false);
    }

    public void drawTiles(Graphics2D g2d) {
        for (int i=0; i<gp.maxColumns; i++) {
            for (int j=0; j<gp.maxRows; j++) {
                tiles[0].drawTile(g2d, i*gp.tileSize, j*gp.tileSize, gp.tileSize);
            }
        }
    }

}
