package tile;

import game.CollisionChecker;
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
    public boolean hasCollision() {
        return this.collision;
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public Direction getDirection(Collidable other) {
        double angle = CollisionChecker.angleBetweenPoints(this.getHitbox().getCenterX(), this.getHitbox().getCenterY(), other.getHitbox().getCenterX(), other.getHitbox().getCenterY());
        return CollisionChecker.dirFromAngle(angle);
    }
    
    @Override
    public int getMaxX(int padding) {
        return this.hitbox.x + this.hitbox.width + padding;
    }

    @Override
    public int getMinX(int padding) {
        return this.hitbox.x - padding;
    }

    @Override
    public int getMaxY(int padding) {
        return this.hitbox.y + this.hitbox.height + padding;
    }

    @Override
    public int getMinY(int padding) {
        return this.hitbox.y - padding;
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
