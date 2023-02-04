package tile;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;

import game.GamePanel;
import entity.Entity;

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
        tiles[1] = new Tile("wall", true);
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

    public boolean collides(Entity entity) {
        //find where entity's hitbox is in the world
        int hitboxLeft = entity.x + entity.hitbox.x;
        int hitboxRight = entity.x + entity.hitbox.x + entity.hitbox.width;
        int hitboxTop = entity.y + entity.hitbox.y;
        int hitboxBottom = entity.y + entity.hitbox.y + entity.hitbox.height;

        switch (entity.direction) {
            case "north":
                hitboxTop -= entity.speed;
                hitboxBottom -= entity.speed;
                return (tiles[map[hitboxLeft/gp.tileSize][hitboxTop/gp.tileSize]].collision || tiles[map[hitboxRight/gp.tileSize][hitboxTop/gp.tileSize]].collision);
            case "south": 
                hitboxBottom += entity.speed;
                hitboxTop += entity.speed;
                return (tiles[map[hitboxLeft/gp.tileSize][hitboxBottom/gp.tileSize]].collision || tiles[map[hitboxRight/gp.tileSize][hitboxBottom/gp.tileSize]].collision);
            case "west":
                hitboxLeft -= entity.speed;
                hitboxRight -= entity.speed;
                return (tiles[map[hitboxLeft/gp.tileSize][hitboxTop/gp.tileSize]].collision || tiles[map[hitboxLeft/gp.tileSize][hitboxBottom/gp.tileSize]].collision);
            case "east":
                hitboxRight += entity.speed;
                hitboxLeft += entity.speed;
                return (tiles[map[hitboxRight/gp.tileSize][hitboxTop/gp.tileSize]].collision || tiles[map[hitboxRight/gp.tileSize][hitboxBottom/gp.tileSize]].collision);
            default:
                return false;
        }
    }

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
