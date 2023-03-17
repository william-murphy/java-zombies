package entity;

import game.KeyHandler.Direction;

import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Entity {
    public int x, y;
    public int screenX, screenY;

    public int animationCounter = 0;
    public boolean animationStep = false;

    public int speed;
    public Direction direction;
    public boolean moving = false;

    public Rectangle hitbox;
    public boolean collision = false;

    public int maxHealth;
    public int health;

    public void update() {
        //to be implemented in subclass
    }

    public void draw(Graphics2D g2d) {
        //to be implemented in subclass
    }

}