package tile;

import game.Game;
import common.*;
import entity.Entity;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.LinkedList;
import java.lang.Math;

public class Tile implements Collidable {
    
    static { 
        setMapDimensions();
        initializeMap();
        loadImages();
    }
    
    //images
    static BufferedImage[] tileImages;

    //world dimensions
    public static int mapRows;
    public static int mapCols;
    public static int worldWidth;
    public static int worldHeight;
    static int minSpawnableRow, minSpawnableCol, maxSpawnableRow, maxSpawnableCol;

    //map
    public static Tile[][] map;

    public final int imageIndex;
    public final int col, row;
    public LinkedList<Entity> entities = new LinkedList<>();
    public Hitbox hitbox;
    public boolean collision;

    public Tile(int imageIndex, int col, int row) {
        this.imageIndex = imageIndex;
        this.col = col;
        this.row = row;
        this.hitbox = new Hitbox(col * Game.tileSize, row * Game.tileSize, Game.tileSize, Game.tileSize);
        initializeCollision();
    }

    public String toString() {
        return String.format("%d, %d%s", this.col, this.row, this.collision ? ", C" : "");
    }

    public void draw(Graphics2D g2d) {
        int screenX = Game.getInstance().camera.calculateScreenX(col * Game.tileSize, 0);
        int screenY = Game.getInstance().camera.calculateScreenY(row * Game.tileSize, 0);
        g2d.drawImage(tileImages[imageIndex], screenX, screenY, Game.tileSize, Game.tileSize, null);
        //DEBUG
        if (Game.getInstance().debug) {
            g2d.setColor(Color.WHITE);
            g2d.drawString(toString(), screenX, screenY + Game.tileSize / 4);
        }
    }

    @Override
    public boolean hasCollision() {
        return this.collision;
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tile)) {
            return false;
        }
        Tile t = (Tile) o;
        return this.col == t.col && this.row == t.row;
    }

    private void initializeCollision() {
        switch (this.imageIndex) {
            case 0:
                this.collision = false;
                break;
            case 1:
                this.collision = true;
                break;
            default:
                this.collision = false;
        }
    }

    private static Tile getRandomSpawnableTile(int radius, Tile playerTile, int step) {
        if (step > 20) {
            return null;
        }
        int row = playerTile.col + (Game.getInstance().random.nextInt(2 * radius + 1) - radius);
        int col = playerTile.row + (Game.getInstance().random.nextInt(2 * radius + 1) - radius);
        if (!map[row][col].collision && row >= minSpawnableRow && row <= maxSpawnableRow && col >= minSpawnableCol && col <= maxSpawnableCol) {
            return map[row][col];
        } else {
            return getRandomSpawnableTile(radius, playerTile, step + 1);
        }
    }

    public static Tile getRandomSpawnableTile(int radius) {
        Tile playerTile = Game.getInstance().entityList.player.getTile();
        return getRandomSpawnableTile(radius, playerTile, 0);
    }

    // calculate manhattan distance between two tiles
    public static int getDistance(Tile one, Tile two) {
        return (Math.abs(one.col - two.col) + Math.abs(one.row - two.row));
    }

    public static Tile getTile(int x, int y) {
        Tile result;
        try {
            result = map[x / Game.tileSize][y / Game.tileSize];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return result;
    }

    public static void drawTiles(Graphics2D g2d) {
        for (int row=0; row < mapRows; row++) {
            for (int col=0; col < mapCols; col++) {
                //only render visible tiles to improve rendering efficiency
                if (Game.getInstance().camera.shouldRenderTile(map[col][row])) {
                    map[col][row].draw(g2d);
                }
            }
        }
    }

    private static void setMapDimensions() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Tile.class.getResourceAsStream("/res/map/map.txt")));
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
            minSpawnableCol = 4;
            minSpawnableRow = 4;
            maxSpawnableCol = mapCols - 4;
            maxSpawnableRow = mapRows - 4;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initializeMap() {
        map = new Tile[mapCols][mapRows];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Tile.class.getResourceAsStream("/res/map/map.txt")));
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

    private static void loadImages() {
        tileImages = new BufferedImage[2];
        try {
            tileImages[0] = ImageIO.read(Tile.class.getResourceAsStream("/res/tile/cobblestone.png"));
            tileImages[1] = ImageIO.read(Tile.class.getResourceAsStream("/res/tile/wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

}
