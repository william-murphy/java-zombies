package tile;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;

import game.GamePanel;

import java.io.BufferedReader;

public class TileController {
    GamePanel gp;
    private Tile[] tiles;
    public int[][] map;
    public int mapRows;
    public int mapCols;

    public TileController(GamePanel gp) {
        this.gp = gp;
        setTiles();
        setMap();
    }

    private void setTiles() {
        tiles = new Tile[7];
        tiles[0] = new Tile("cobblestone", false);
        tiles[1] = new Tile("barrier-vertical", true);
        tiles[2] = new Tile("barrier-horizontal", true);
        tiles[3] = new Tile("barrier-ne", true);
        tiles[4] = new Tile("barrier-nw", true);
        tiles[5] = new Tile("barrier-se", true);
        tiles[6] = new Tile("barrier-sw", true);
    }

    private void setMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/res/map/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int row = 0;
            while (br.ready()) {
                String[] listOfStrings = br.readLine().split(" ");
                if (map == null) {
                    mapCols = listOfStrings.length;
                    mapRows = mapCols;
                    map = new int[mapRows][mapCols];
                } else {
                    for (int i=0; i<mapCols; i++) {
                        map[row][i] = Integer.valueOf(listOfStrings[i]);
                    }
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public boolean collidesWithTerrain(Player player, int distance) {
    //     return ()
    // }

    public void drawTiles(Graphics2D g2d) {
        for (int i=0; i<mapRows; i++) {
            for (int j=0; j<mapCols; j++) {
                //only render visible tiles to improve rendering efficiency
                if (gp.player.camera.shouldRenderTile(i, j)) {
                    g2d.drawImage(tiles[map[i][j]].image, gp.player.camera.calculateTileX(i), gp.player.camera.calculateTileY(j), gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }

}
