package game;

import tile.Tile;

public class Camera {

    public int x, y;
    public int screenX = (Game.screenWidth / 2) - (Game.tileSize / 2);
    public int screenY = (Game.screenHeight / 2) - (Game.tileSize / 2);

    private static int cameraBoundaryX = (Game.screenWidth / 2) + (Game.tileSize / 2);
    private static int cameraBoundaryY = (Game.screenHeight / 2) + (Game.tileSize / 2);

    public Camera() {
        this.x = Game.getInstance().entityList.player.getHitbox().x;
        this.y = Game.getInstance().entityList.player.getHitbox().y;
    }

    public void update(int worldX, int worldY) {
        if (worldX >= cameraBoundaryX && worldX <= Tile.worldWidth - cameraBoundaryX - Game.tileSize) {
            this.x = worldX;
        }
        if (worldY >= cameraBoundaryY && worldY <= Tile.worldHeight - cameraBoundaryY - Game.tileSize) {
            this.y = worldY;
        }
    }

    //given an x and y coordinate, check if that is in the bounds of the camera and thus should be rendered
    public boolean shouldRenderTile(Tile tile) {
        int worldX = tile.getHitbox().x;
        int worldY = tile.getHitbox().y;
        return(
            worldX >= this.x - cameraBoundaryX && 
            worldX <= this.x + cameraBoundaryX && 
            worldY >= this.y - cameraBoundaryY &&
            worldY <= this.y + cameraBoundaryY
        );
    }

    public int calculateScreenX(int worldX, int offset) {
        return (worldX - offset) + this.screenX - this.x;
    }

    public int calculateScreenY(int worldY, int offset) {
        return (worldY - offset) + this.screenY - this.y;
    }

}
