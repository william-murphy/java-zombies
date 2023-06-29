package tile;

import game.Game;
import common.*;

public class Tile implements Collidable, Drawable {
    
    public final int imageIndex;
    public final int col, row;
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

    @Override
    public boolean hasCollision() {
        return this.collision;
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public int getWorldX() {
        return col * Game.tileSize;
    }

    @Override
    public int getWorldY() {
        return row * Game.tileSize;
    }

    @Override
    public int getHorizontalOffset() {
        return 0;
    }

    @Override
    public int getVerticalOffset() {
        return 0;
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

}
