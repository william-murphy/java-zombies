package entity;

import common.*;
import tile.Tile;

import java.awt.Graphics2D;

public abstract class Entity implements Collidable {

    public Hitbox hitbox;
    public boolean collision = true;

    public int animationCounter = 0;
    public boolean animationStep = false;

    public int speed;
    public Direction direction;
    public boolean moving = false;

    // IDEA: For entity collision, what if entity list was priority queue by coordinate somehow, then when checking collision you only have to check the two closest in the x prio q and the y prio q
    // IDEA: make a folder in entity and tile called controller and create a class Controller.java in each with any other classes they may need in there

    public Tile getTile() {
        return Tile.getTile((int)hitbox.getCenterX(), (int)hitbox.getCenterY());
    }

    public void move(Direction direction) {
        this.moving = true;
        this.direction = direction;
    }

    public void stopMove(Direction direction) {
        if (this.direction == direction) {
            this.moving = false;
        }
    }

    protected void updatePosition() {
        if (moving) {
            switch(direction) {
                case NORTH:
                    hitbox.translate(0, -speed);
                    if (collides(Tile.getTile(hitbox.x, hitbox.y))) {
                        hitbox.setLocation(hitbox.x, (int)Tile.getTile(hitbox.x, hitbox.y).getHitbox().getMaxY());
                    } else if (collides(Tile.getTile(hitbox.x + hitbox.width, hitbox.y))) {
                        hitbox.setLocation(hitbox.x, (int)Tile.getTile(hitbox.x + hitbox.width, hitbox.y).getHitbox().getMaxY());
                    }
                    break;
                case SOUTH:
                    hitbox.translate(0, speed);
                    if (collides(Tile.getTile(hitbox.x, hitbox.y + hitbox.height))) {
                        hitbox.setLocation(hitbox.x, Tile.getTile(hitbox.x, hitbox.y + hitbox.height).getHitbox().y - hitbox.height);
                    } else if (collides(Tile.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height))) {
                        hitbox.setLocation(hitbox.x, Tile.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height).getHitbox().y - hitbox.height);
                    }
                    break;
                case EAST:
                    hitbox.translate(speed, 0);
                    if (collides(Tile.getTile(hitbox.x + hitbox.width, hitbox.y))) {
                        hitbox.setLocation(Tile.getTile(hitbox.x + hitbox.width, hitbox.y).getHitbox().x - hitbox.width, hitbox.y);
                    } else if (collides(Tile.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height))) {
                        hitbox.setLocation(Tile.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height).getHitbox().x - hitbox.width, hitbox.y);
                    }
                    break;
                case WEST:
                    hitbox.translate(-speed, 0);
                    if (collides(Tile.getTile(hitbox.x, hitbox.y))) {
                        hitbox.setLocation((int)Tile.getTile(hitbox.x, hitbox.y).getHitbox().getMaxX(), hitbox.y);
                    } else if (collides(Tile.getTile(hitbox.x, hitbox.y + hitbox.height))) {
                        hitbox.setLocation((int)Tile.getTile(hitbox.x, hitbox.y + hitbox.height).getHitbox().getMaxX(), hitbox.y);
                    }
                    break;
            }

            animationCounter++;
            if (animationCounter > 15) {
                animationStep = !animationStep;
                animationCounter = 0;
            }

        }
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

    public abstract void update(); // false means delete

    public abstract void draw(Graphics2D g2d);

}