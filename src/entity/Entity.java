package entity;

import common.*;
import tile.Tile;

import java.awt.Graphics2D;

public abstract class Entity implements Collidable {

    public Hitbox hitbox;
    public boolean collision = true;

    // IDEA: For entity collision, what if entity list was priority queue by coordinate somehow, then when checking collision you only have to check the two closest in the x prio q and the y prio q
    // IDEA: make a folder in entity and tile called controller and create a class Controller.java in each with any other classes they may need in there

    public Tile getTile() {
        return Tile.getTile((int)hitbox.getCenterX(), (int)hitbox.getCenterY());
    }

    public boolean fitsHorizontally(Tile tile) {
        return (hitbox.x >= tile.hitbox.x && (hitbox.x + hitbox.width) <= (tile.hitbox.x + tile.hitbox.width));
    }

    public boolean fitsVertically(Tile tile) {
        return (hitbox.y >= tile.hitbox.y && (hitbox.y + hitbox.height) <= (tile.hitbox.y + tile.hitbox.height));
    }

    @Override 
    public boolean hasCollision() {
        return this.collision;
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    public abstract void update();

    public abstract void draw(Graphics2D g2d);

}