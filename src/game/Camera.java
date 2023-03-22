package game;

import entity.Player;

public class Camera {
    private GamePanel game;
    private final int cameraBoundaryX;
    private final int cameraBoundaryY;
    public int x, y;
    public final int screenX, screenY;

    public Camera(GamePanel game) {
        this.game = game;
        this.x = game.entityController.player.x;
        this.y = game.entityController.player.y;
        this.screenX = (game.screenWidth / 2) - (game.tileSize / 2);
        this.screenY = (game.screenHeight / 2) - (game.tileSize / 2);
        this.cameraBoundaryX = (game.screenWidth / 2) + (game.tileSize / 2);
        this.cameraBoundaryY = (game.screenHeight / 2) + (game.tileSize / 2);
    }

    public void update(int worldX, int worldY) {
        if (worldX >= this.cameraBoundaryX && worldX <= game.worldWidth - this.cameraBoundaryX - game.tileSize) {
            this.x = worldX;
        }
        if (worldY >= this.cameraBoundaryY && worldY <= game.worldHeight - this.cameraBoundaryY - game.tileSize) {
            this.y = worldY;
        }
    }

    //given an x and y coordinate, check if that is in the bounds of the camera and thus should be rendered
    public boolean shouldRenderTile(int col, int row) {
        int mapCol = col * game.tileSize;
        int mapRow = row * game.tileSize;
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
        return (col * game.tileSize) - this.x + this.screenX;
    }

    public int calculateTileY(int row) {
        return (row * game.tileSize) - this.y + this.screenY;
    }

}
