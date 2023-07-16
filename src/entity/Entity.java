package entity;

import common.*;
import tile.Tile;
import tile.TileController;

import java.awt.Graphics2D;
import java.awt.Point;

// TODO - make a new abstract class called Mob so that Item and Mob are separate

public abstract class Entity implements Collidable {

    public int animationCounter = 0;
    public boolean animationStep = false;

    public int speed;
    public Direction direction;
    public boolean moving = false;

    public Hitbox hitbox;
    public boolean collision = true;

    public int maxHealth;
    public int health;

    // IDEA: For entity collision, what if entity list was priority queue by coordinate somehow, then when checking collision you only have to check the two closest in the x prio q and the y prio q
    // IDEA: make a folder in entity and tile called controller and create a class Controller.java in each with any other classes they may need in there

    public Tile getTile() {
        return TileController.getTile((int)hitbox.getCenterX(), (int)hitbox.getCenterY());
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
                    if (collides(TileController.getTile(hitbox.x, hitbox.y))) {
                        hitbox.setLocation(hitbox.x, (int)TileController.getTile(hitbox.x, hitbox.y).getHitbox().getMaxY());
                    } else if (collides(TileController.getTile(hitbox.x + hitbox.width, hitbox.y))) {
                        hitbox.setLocation(hitbox.x, (int)TileController.getTile(hitbox.x + hitbox.width, hitbox.y).getHitbox().getMaxY());
                    }
                    break;
                case SOUTH:
                    hitbox.translate(0, speed);
                    if (collides(TileController.getTile(hitbox.x, hitbox.y + hitbox.height))) {
                        hitbox.setLocation(hitbox.x, TileController.getTile(hitbox.x, hitbox.y + hitbox.height).getHitbox().y - hitbox.height);
                    } else if (collides(TileController.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height))) {
                        hitbox.setLocation(hitbox.x, TileController.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height).getHitbox().y - hitbox.height);
                    }
                    break;
                case EAST:
                    hitbox.translate(speed, 0);
                    if (collides(TileController.getTile(hitbox.x + hitbox.width, hitbox.y))) {
                        hitbox.setLocation(TileController.getTile(hitbox.x + hitbox.width, hitbox.y).getHitbox().x - hitbox.width, hitbox.y);
                    } else if (collides(TileController.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height))) {
                        hitbox.setLocation(TileController.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height).getHitbox().x - hitbox.width, hitbox.y);
                    }
                    break;
                case WEST:
                    hitbox.translate(-speed, 0);
                    if (collides(TileController.getTile(hitbox.x, hitbox.y))) {
                        hitbox.setLocation((int)TileController.getTile(hitbox.x, hitbox.y).getHitbox().getMaxX(), hitbox.y);
                    } else if (collides(TileController.getTile(hitbox.x, hitbox.y + hitbox.height))) {
                        hitbox.setLocation((int)TileController.getTile(hitbox.x, hitbox.y + hitbox.height).getHitbox().getMaxX(), hitbox.y);
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

    public abstract Point getHand();

    public abstract void update();

    public abstract void draw(Graphics2D g2d);

}