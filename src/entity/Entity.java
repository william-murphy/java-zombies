package entity;

import common.*;
import tile.Tile;

import java.awt.Graphics2D;

public abstract class Entity implements Collidable {

    public Hitbox hitbox;
    public boolean collision = true;

    public Tile getTile() {
        return Tile.getTile((int)hitbox.getCenterX(), (int)hitbox.getCenterY());
    }

    @Override 
    public boolean hasCollision() {
        return this.collision;
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    public abstract void spawn(int x, int y);

    public abstract void despawn();

    public abstract void update();

    public abstract void draw(Graphics2D g2d);

}