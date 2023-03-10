package game;

import entity.Player;

public class Camera {
    private GamePanel gp;
    private final int cameraBoundaryX;
    private final int cameraBoundaryY;
    public int x, y;
    public final int screenX, screenY;

    public Camera(GamePanel gp, Player player) {
        this.gp = gp;
        this.x = player.x;
        this.y = player.y;
        this.screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);
        this.screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);
        this.cameraBoundaryX = (gp.screenWidth / 2) + (gp.tileSize / 2);
        this.cameraBoundaryY = (gp.screenHeight / 2) + (gp.tileSize / 2);
    }

    public void update(int worldX, int worldY) {
        if (worldX >= this.cameraBoundaryX && worldX <= gp.worldWidth - this.cameraBoundaryX - gp.tileSize) {
            this.x = worldX;
        }
        if (worldY >= this.cameraBoundaryY && worldY <= gp.worldHeight - this.cameraBoundaryY - gp.tileSize) {
            this.y = worldY;
        }
    }

    //given an x and y coordinate, check if that is in the bounds of the camera and thus should be rendered
    public boolean shouldRenderTile(int col, int row) {
        int mapCol = col * gp.tileSize;
        int mapRow = row * gp.tileSize;
        return(
            mapCol >= this.x - cameraBoundaryX && 
            mapCol <= this.x + cameraBoundaryX && 
            mapRow >= this.y - cameraBoundaryY &&
            mapRow <= this.y + cameraBoundaryY
        );
    }

    public int calculateScreenX(int worldX) {
        return -1 * (this.x - worldX - this.screenX);
    }

    public int calculateScreenY(int worldY) {
        return -1 * (this.y - worldY - this.screenY);
    }

    public int calculateTileX(int col) {
        return (col * gp.tileSize) - this.x + this.screenX;
    }

    public int calculateTileY(int row) {
        return (row * gp.tileSize) - this.y + this.screenY;
    }

}
