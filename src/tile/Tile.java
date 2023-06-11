package tile;

import game.Game;
import common.Direction;
import common.Collidable;
import entity.Entity;

import java.awt.Rectangle;

public class Tile implements Collidable {
    
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

    public String toString() {
        return String.format("Image: %d, Column: %d, Row: %d", this.imageIndex, this.col, this.row);
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

    public boolean contains(Entity entity) {
        return this.hitbox.contains(entity.hitbox);
    }

    public boolean fitsHorizontally(Entity entity) {
        return (this.hitbox.x < entity.hitbox.x && entity.hitbox.x + entity.hitbox.width < this.hitbox.x + this.hitbox.width);
    }

    public boolean fitsVertically(Entity entity) {
        return (this.hitbox.y < entity.hitbox.y && entity.hitbox.y + entity.hitbox.height < this.hitbox.y + this.hitbox.height);
    }

    @Override
    public boolean getCollision() {
        return this.collision;
    }

    @Override
    public Rectangle getHitbox() {
        return this.hitbox;
    }

    public Direction getDirection(Tile other) {
        if (other.row == this.row - 1) {
            return Direction.NORTH;
        } else if (other.row == this.row + 1) {
            return Direction.SOUTH;
        } else if (other.col == this.col - 1) {
            return Direction.WEST;
        } else if (other.col == this.col + 1) {
            return Direction.EAST;
        }
        return null;
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
