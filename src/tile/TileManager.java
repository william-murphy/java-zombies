package tile;

import game.GamePanel;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int[][] map;
    int dimension;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        setTiles();
        setMap();
    }

    private void setMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/res/map/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int row = 0;
            while (br.ready()) {
                String[] listOfStrings = br.readLine().split(" ");
                if (map == null) {
                    dimension = listOfStrings.length;
                    map = new int[dimension][dimension];
                } else {
                    for (int i=0; i<dimension; i++) {
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

    public void drawTiles(Graphics2D g2d) {
        int renderCount = 0;
        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                //only render visible tiles to improve rendering efficiency
                if (gp.player.camera.shouldRender(i, j)) {
                    g2d.drawImage(tiles[map[i][j]].image, gp.player.camera.getX(i), gp.player.camera.getY(j), gp.tileSize, gp.tileSize, null);
                    renderCount++;
                }
            }
        }
        System.out.println(renderCount);
    }

}
