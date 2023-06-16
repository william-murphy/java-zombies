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

    // IDEA: For entity collision, what if entity list was priority queue by coordinate somehow, then when checking collision you only have to check the two closest in the x prio q and the y prio q
    // IDEA: make a folder in entity and tile called controller and create a class Controller.java in each with any other classes they may need in there

    public Tile getTile() {
        return TileController.map[(int)Math.round(this.hitbox.getCenterX() / Game.tileSize)][(int)Math.round(this.hitbox.getCenterY() / Game.tileSize)];
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
                    hitbox.translate(0, -CollisionChecker.getNextYDistance(this));
                    break;
                case SOUTH:
                    hitbox.translate(0, CollisionChecker.getNextYDistance(this));
                    break;
                case EAST:
                    hitbox.translate(CollisionChecker.getNextXDistance(this), 0);
                    break;
                case WEST:
                    hitbox.translate(-CollisionChecker.getNextXDistance(this), 0);
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

    public void update() {
        //to be implemented in subclass
    }

    public void draw(Graphics2D g2d) {
        //to be implemented in subclass
    }

}