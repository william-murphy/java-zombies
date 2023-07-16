package entity.creature;

import entity.Entity;
import tile.TileController;
import common.Direction;

import java.awt.Point;

public abstract class Creature extends Entity {
    
    public int animationCounter = 0;
    public boolean animationStep = false;

    public int speed;
    public Direction direction;
    public boolean moving = false;

    public int maxHealth;
    public int health;

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

    public abstract Point getHand();

}
