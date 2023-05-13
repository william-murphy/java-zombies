package entity;

import game.Game;
import common.Direction;
import common.Collidable;
import game.CollisionChecker;
import tile.Tile;
import tile.TileController;

import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Entity implements Collidable {
    public int x, y;
    public int screenX, screenY;

    public int animationCounter = 0;
    public boolean animationStep = false;

    public int speed;
    public Direction direction;
    public boolean moving = false;
    public boolean onPath = false;

    public Rectangle hitbox;
    public boolean collision = false;

    public int maxHealth;
    public int health;

    public Tile getTile() {
        return TileController.map[this.hitbox.x / Game.tileSize][this.hitbox.y / Game.tileSize];
    }

    protected boolean canMove(Direction direction) {
        return true;
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
                    if (!CollisionChecker.checkTileCollision(this)) {
                        y -= speed;
                    }else {
                        hitbox.translate(0, speed);
                    }
                    break;
                case SOUTH:
                    hitbox.translate(0, speed);
                    if (!CollisionChecker.checkTileCollision(this)) {
                        y += speed;
                    }else {
                        hitbox.translate(0, -speed);
                    }
                    break;
                case EAST:
                    hitbox.translate(speed, 0);
                    if (!CollisionChecker.checkTileCollision(this)) {
                        x += speed;
                    }else {
                        hitbox.translate(-speed, 0);
                    }
                    break;
                case WEST:
                    hitbox.translate(-speed, 0);
                    if (!CollisionChecker.checkTileCollision(this)) {
                        x -= speed;
                    }else {
                        hitbox.translate(speed, 0);
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

    @Override
    public Rectangle getHitbox() {
        return this.hitbox;
    }

    @Override
    public boolean collides(Collidable other) {
        return this.collision && this.hitbox.intersects(other.getHitbox());
    }

    public void update() {
        //to be implemented in subclass
    }

    public void draw(Graphics2D g2d) {
        //to be implemented in subclass
    }

}