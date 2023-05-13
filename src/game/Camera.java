package game;

import tile.TileController;

public class Camera {
    
    Game game;

    public int x, y;
    public int screenX = (Game.screenWidth / 2) - (Game.tileSize / 2);
    public int screenY = (Game.screenHeight / 2) - (Game.tileSize / 2);

    private static int cameraBoundaryX = (Game.screenWidth / 2) + (Game.tileSize / 2);
    private static int cameraBoundaryY = (Game.screenHeight / 2) + (Game.tileSize / 2);

    public Camera(Game game) {
        this.game = game;
        this.x = game.entityController.player.x;
        this.y = game.entityController.player.y;
    }

    public void update(int worldX, int worldY) {
        if (worldX >= cameraBoundaryX && worldX <= TileController.worldWidth - cameraBoundaryX - Game.tileSize) {
            this.x = worldX;
        }
        if (worldY >= cameraBoundaryY && worldY <= TileController.worldHeight - cameraBoundaryY - Game.tileSize) {
            this.y = worldY;
        }
    }

    //given an x and y coordinate, check if that is in the bounds of the camera and thus should be rendered
    public boolean shouldRenderTile(int col, int row) {
        int mapCol = col * Game.tileSize;
        int mapRow = row * Game.tileSize;
        return(
            mapCol >= this.x - cameraBoundaryX && 
            mapCol <= this.x + cameraBoundaryX && 
            mapRow >= this.y - cameraBoundaryY &&
            mapRow <= this.y + cameraBoundaryY
        );
    }

    public int calculateScreenX(int worldX) {
        return -(this.x - worldX - this.screenX);
    }

    public int calculateScreenY(int worldY) {
        return -(this.y - worldY - this.screenY);
    }

    public int calculateTileX(int col) {
        return (col * Game.tileSize) - this.x + this.screenX;
    }

    public int calculateTileY(int row) {
        return (row * Game.tileSize) - this.y + this.screenY;
    }

}
