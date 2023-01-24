package game;

public class Camera {
    public int x, y;
    public final int screenX, screenY;
    private GamePanel gp;
    int cameraBoundaryX;
    int cameraBoundaryY;

    public Camera(GamePanel gp) {
        this.gp = gp;
        this.x = gp.playerSpawnX;
        this.y = gp.playerSpawnY;
        this.screenX = (gp.width / 2) - (gp.tileSize / 2);
        this.screenY = (gp.height / 2) - (gp.tileSize / 2);
        this.cameraBoundaryX = gp.width / 2 + gp.tileSize / 2;
        this.cameraBoundaryY = gp.height / 2 + gp.tileSize / 2;
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
    public boolean shouldRender(int col, int row) {
        int mapCol = col * gp.tileSize;
        int mapRow = row * gp.tileSize;
        // int halfScreenWidth = this.gp.width / 2 + gp.tileSize / 2;
        // int halfScreenHeight = this.gp.height / 2 + gp.tileSize / 2;
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
