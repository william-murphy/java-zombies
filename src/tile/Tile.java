package tile;

import game.Game;

import java.awt.Rectangle;

public class Tile {
    
    public final int imageIndex;
    public final Rectangle hitbox;
    public final int col, row;
    public boolean collision;

    public Tile(int imageIndex, int col, int row) {
        this.imageIndex = imageIndex;
        this.col = col;
        this.row = row;
        this.hitbox = new Rectangle(col * Game.tileSize, row * Game.tileSize, Game.tileSize, Game.tileSize);
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

}
