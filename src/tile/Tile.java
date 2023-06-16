package tile;

import game.Game;
import common.*;
import entity.Entity;

public class Tile implements Collidable, Drawable {
    
    public final int imageIndex;
    public final Hitbox hitbox;
    public final int col, row;
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
    public Hitbox getHitbox() {
        return this.hitbox;
    }

    @Override
    public int getNorthBound(int padding) {
        return (int)((this.hitbox.getMinY() - padding) / Game.tileSize);
    }

    @Override
    public int getSouthBound(int padding) {
        return (int)((this.hitbox.getMaxY() + padding) / Game.tileSize);
    }

    @Override
    public int getEastBound(int padding) {
        return (int)((this.hitbox.getMaxX() + padding) / Game.tileSize);
    }

    @Override
    public int getWestBound(int padding) {
        return (int)((this.hitbox.getMinX() - padding) / Game.tileSize);
    }

    @Override
    public int getWorldX() {
        return col * Game.tileSize;
    }

    public int getWorldY() {
        return row * Game.tileSize;
    }

    public int getHorizontalOffset() {
        return 0;
    }

    public int getVerticalOffset() {
        return 0;
    }

    public Direction getDirection(Tile other) {
        if (other.row == this.row - 1) {
            return Direction.NORTH;
        } else if (other.row == this.row + 1) {
            return Direction.SOUTH;
        } else if (other.col == this.col + 1) {
            return Direction.EAST;
        } else if (other.col == this.col - 1) {
            return Direction.WEST;
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
