package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.lang.Math;

import game.Game;

public class TileController {
    
    Game game;

    //images
    static BufferedImage[] tileImages;

    //world dimensions
    public static int mapRows;
    public static int mapCols;
    public static int worldWidth;
    public static int worldHeight;

    //player spawn
    public static int playerSpawnX;
    public static int playerSpawnY;

    //map
    public static Tile[][] map;

    public TileController(Game game) {
        this.game = game;
    }

    //calculate manhattan distance between two tiles
    public static int getDistance(Tile one, Tile two) {
        return (Math.abs(one.col - two.col) + Math.abs(one.row - two.row));
    }

    //returns an array of the neighbors of a tile in the form of [n, e, s, w] --- NOTE: later on it will be [n, ne, e, se, s, sw, w, nw]
    public static Tile[] getTileNeighbors(Tile tile) {
        //Tile[] neighbors = { map[tile.col][tile.row - 1], map[tile.col + 1][tile.row - 1], map[tile.col + 1][tile.row], map[tile.col + 1][tile.row + 1], map[tile.col][tile.row + 1], map[tile.col - 1][tile.row + 1], map[tile.col - 1][tile.row], map[tile.col - 1][tile.row - 1] };
        Tile[] neighbors = { map[tile.col][tile.row - 1], map[tile.col + 1][tile.row], map[tile.col][tile.row + 1], map[tile.col - 1][tile.row] };
        return neighbors;
    }

    public void drawTiles(Graphics2D g2d) {
        for (int row=0; row < mapRows; row++) {
            for (int col=0; col < mapCols; col++) {
                //only render visible tiles to improve rendering efficiency
                if (game.camera.shouldRenderTile(col, row)) {
                    g2d.drawImage(tileImages[map[col][row].imageIndex], game.camera.calculateTileX(col), game.camera.calculateTileY(row), Game.tileSize, Game.tileSize, null);
                }
            }
        }
    }

    public static void setMapDimensions() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(TileController.class.getResourceAsStream("/res/map/map.txt")));
            String line = reader.readLine();
            mapCols = line.split(" ").length;
            int count = 0;
            while (line != null) {
                line = reader.readLine();
                count++;
            }
            reader.close();
            mapRows = count;
            worldWidth = mapCols * Game.tileSize;
            worldHeight = mapRows * Game.tileSize;
            playerSpawnX = (worldWidth / 2) - (Game.tileSize / 2);
            playerSpawnY = (worldHeight / 2) - (Game.tileSize / 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initializeMap() {
        map = new Tile[mapCols][mapRows];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(TileController.class.getResourceAsStream("/res/map/map.txt")));
            String[] splitLine;
            for (int row = 0; row < mapRows; row++) {
                splitLine = reader.readLine().split(" ");
                for (int col = 0; col < mapCols; col++) {
                    map[col][row] = new Tile(Integer.parseInt(splitLine[col]), col, row);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImages() {
        tileImages = new BufferedImage[2];
        try {
            tileImages[0] = ImageIO.read(TileController.class.getResourceAsStream("/res/tile/cobblestone.png"));
            tileImages[1] = ImageIO.read(TileController.class.getResourceAsStream("/res/tile/wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

}
