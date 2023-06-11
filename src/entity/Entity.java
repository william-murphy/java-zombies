package entity;

import game.Game;
import common.*;
import game.CollisionChecker;
import tile.Tile;
import tile.TileController;

import java.awt.Graphics2D;

public class Entity implements Collidable {
    public int screenX, screenY;

    public int animationCounter = 0;
    public boolean animationStep = false;

    public int speed;
    public Direction direction;
    public boolean moving = false;
    public boolean onPath = false;

    public Hitbox hitbox;
    public boolean collision = true;

    public int maxHealth;
    public int health;

    public int getNorthBound() {
        return this.hitbox.y;
    }

    public int getSouthBound() {
        return this.hitbox.y + this.hitbox.height;
    }

    public int getEastBound() {
        return this.hitbox.x + this.hitbox.width;
    }

    public int getWestBound() {
        return this.hitbox.x;
    }

    public Tile getTile() {
        return TileController.map[this.hitbox.x / Game.tileSize][this.hitbox.y / Game.tileSize];
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
        if (moving && !CollisionChecker.checkTileCollision(this)) {
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

            animationCounter++;
            if (animationCounter > 15) {
                animationStep = !animationStep;
                animationCounter = 0;
            }

        }
    }

    @Override 
    public boolean getCollision() {
        return this.collision;
    }

    @Override
    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public void update() {
        //to be implemented in subclass
    }

    public void draw(Graphics2D g2d) {
        //to be implemented in subclass
    }

}