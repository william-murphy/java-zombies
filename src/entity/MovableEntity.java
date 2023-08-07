package entity;

import common.*;
import tile.Tile;

public abstract class MovableEntity extends Entity {

    public int speed;
    public Direction direction;
    public boolean moving = false;

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
                    break;
                case SOUTH:
                    hitbox.translate(0, speed);
                    break;
                case EAST:
                    hitbox.translate(speed, 0);
                    break;
                case WEST:
                    hitbox.translate(-speed, 0);
                    break;
            }

        }
    }

    protected boolean checkCollision() {
        if (moving) {
            switch(direction) {
                case NORTH:
                    if (collides(Tile.getTile(hitbox.x, hitbox.y))) {
                        hitbox.setLocation(hitbox.x, (int)Tile.getTile(hitbox.x, hitbox.y).getHitbox().getMaxY());
                    } else if (collides(Tile.getTile(hitbox.x + hitbox.width, hitbox.y))) {
                        hitbox.setLocation(hitbox.x, (int)Tile.getTile(hitbox.x + hitbox.width, hitbox.y).getHitbox().getMaxY());
                    } else {
                        return false;
                    }
                    break;
                case SOUTH:
                    if (collides(Tile.getTile(hitbox.x, hitbox.y + hitbox.height))) {
                        hitbox.setLocation(hitbox.x, Tile.getTile(hitbox.x, hitbox.y + hitbox.height).getHitbox().y - hitbox.height);
                    } else if (collides(Tile.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height))) {
                        hitbox.setLocation(hitbox.x, Tile.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height).getHitbox().y - hitbox.height);
                    } else {
                        return false;
                    }
                    break;
                case EAST:
                    if (collides(Tile.getTile(hitbox.x + hitbox.width, hitbox.y))) {
                        hitbox.setLocation(Tile.getTile(hitbox.x + hitbox.width, hitbox.y).getHitbox().x - hitbox.width, hitbox.y);
                    } else if (collides(Tile.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height))) {
                        hitbox.setLocation(Tile.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height).getHitbox().x - hitbox.width, hitbox.y);
                    } else {
                        return false;
                    }
                    break;
                case WEST:
                    if (collides(Tile.getTile(hitbox.x, hitbox.y))) {
                        hitbox.setLocation((int)Tile.getTile(hitbox.x, hitbox.y).getHitbox().getMaxX(), hitbox.y);
                    } else if (collides(Tile.getTile(hitbox.x, hitbox.y + hitbox.height))) {
                        hitbox.setLocation((int)Tile.getTile(hitbox.x, hitbox.y + hitbox.height).getHitbox().getMaxX(), hitbox.y);
                    } else {
                        return false;
                    }
                    break;
            }
            return true;
        }
        return false;
    }

    protected void updateTile() {
        Tile newTile = this.getTile();
        if (!newTile.equals(this.tile)) {
            newTile.entities.add(this);
            this.tile.entities.remove(this);
            this.tile = newTile;
        }
    }

}
