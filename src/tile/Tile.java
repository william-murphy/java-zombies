package tile;

import java.awt.Rectangle;

public class Tile {
    
    public int imageIndex;
    public boolean collision;
    public Rectangle hitbox;

    public Tile(int imageIndex, int col, int row, int tileSize) {
        this.imageIndex = imageIndex;
        this.hitbox = new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize);
        initializeCollision();
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

    public boolean collides(Rectangle other) {
        return this.collision && this.hitbox.intersects(other);
    }

}
