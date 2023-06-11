package game;

import tile.TileController;
import tile.Tile;
import common.Drawable;

public class Camera {
    
    Game game;

    public int x, y;
    public int screenX = (Game.screenWidth / 2) - (Game.tileSize / 2);
    public int screenY = (Game.screenHeight / 2) - (Game.tileSize / 2);

    private static int cameraBoundaryX = (Game.screenWidth / 2) + (Game.tileSize / 2);
    private static int cameraBoundaryY = (Game.screenHeight / 2) + (Game.tileSize / 2);

    public Camera(Game game) {
        this.game = game;
        this.x = game.entityController.player.getWorldX();
        this.y = game.entityController.player.getWorldY();
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
    public boolean shouldRenderTile(Tile tile) {
        int worldX = tile.getWorldX();
        int worldY = tile.getWorldY();
        return(
            worldX >= this.x - cameraBoundaryX && 
            worldX <= this.x + cameraBoundaryX && 
            worldY >= this.y - cameraBoundaryY &&
            worldY <= this.y + cameraBoundaryY
        );
    }

    public int calculateScreenX(Drawable thing) {
        return (thing.getWorldX() - thing.getHorizontalOffset()) + this.screenX - this.x;
    }

    public int calculateScreenY(Drawable thing) {
        return (thing.getWorldY() - thing.getVerticalOffset()) + this.screenY - this.y;
    }

}
