package game;

import entity.Player;

public class Camera {
    public final int x, y;
    private Player player;
    private GamePanel gp;

    public Camera(Player player, GamePanel gp) {
        this.player = player;
        this.gp = gp;
        this.x = this.gp.spawnX;
        this.y = this.gp.spawnY;
    }

    //given an x and y coordinate, check if that is in the bounds of the camera and thus should be rendered
    public boolean shouldRender(int mapX, int mapY) {
        return(
            (mapX * gp.tileSize) >= player.x - this.x - gp.tileSize &&
            (mapX * gp.tileSize) <= player.x + this.x + gp.tileSize &&
            (mapY * gp.tileSize) >= player.y - this.y - gp.tileSize &&
            (mapY * gp.tileSize) <= player.y + this.y + gp.tileSize
        );
    }

    public int getX(int mapX) {
        return (mapX * gp.tileSize) - player.x + this.x;
    }

    public int getY(int mapY) {
        return (mapY * gp.tileSize) - player.y + this.y;
    }

}
