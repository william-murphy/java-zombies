package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
    public int x, y;
    public int screenX, screenY;
    public int speed;

    public BufferedImage standingNorth, walkingNorth1, walkingNorth2, standingSouth, walkingSouth1, walkingSouth2, standingEast, walkingEast1, walkingEast2, standingWest, walkingWest1, walkingWest2;
    public String direction;
    public boolean moving;

    public int animationCounter = 0;
    public boolean animationStep = false;

    public Rectangle hitbox;
    public boolean collision = false;

}