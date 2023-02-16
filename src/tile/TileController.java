package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import game.GamePanel;
import entity.Entity;

public class TileController {
    private GamePanel gp;
    private Tile[] tiles;
    public int[][] map;

    public TileController(GamePanel gp) {
        this.gp = gp;
        map = new int[gp.mapRows][gp.mapCols];
        initializeTiles();
        initializeMap();
    }

    private void initializeTiles() {
        tiles = new Tile[2];
        tiles[0] = new Tile("cobblestone", false);
        tiles[1] = new Tile("wall", true);
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
                    map[col][row] = Integer.parseInt(splitLine[col]);
                }
                line = reader.readLine();
                row++;
            }
            reader.close();
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
                return (tiles[map[hitboxLeft/gp.tileSize][hitboxTop/gp.tileSize]].collision || tiles[map[hitboxRight/gp.tileSize][hitboxTop/gp.tileSize]].collision);
            case "south": 
                hitboxBottom += entity.speed;
                return (tiles[map[hitboxLeft/gp.tileSize][hitboxBottom/gp.tileSize]].collision || tiles[map[hitboxRight/gp.tileSize][hitboxBottom/gp.tileSize]].collision);
            case "west":
                hitboxLeft -= entity.speed;
                return (tiles[map[hitboxLeft/gp.tileSize][hitboxTop/gp.tileSize]].collision || tiles[map[hitboxLeft/gp.tileSize][hitboxBottom/gp.tileSize]].collision);
            case "east":
                hitboxRight += entity.speed;
                return (tiles[map[hitboxRight/gp.tileSize][hitboxTop/gp.tileSize]].collision || tiles[map[hitboxRight/gp.tileSize][hitboxBottom/gp.tileSize]].collision);
            default:
                return false;
        }
    }

    public void drawTiles(Graphics2D g2d) {
        for (int row=0; row<gp.mapRows; row++) {
            for (int col=0; col<gp.mapCols; col++) {
                //only render visible tiles to improve rendering efficiency
                if (gp.player.camera.shouldRenderTile(col, row)) {
                    g2d.drawImage(tiles[map[col][row]].image, gp.player.camera.calculateTileX(col), gp.player.camera.calculateTileY(row), gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }

}
