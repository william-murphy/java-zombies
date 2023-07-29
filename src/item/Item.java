package item;

import entity.creature.Creature;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Item  {

    public int width, height;
    public int maxStack;
    protected BufferedImage facingNorth, facingSouth, facingEast, facingWest;

    public abstract void use();

    public abstract void stopUse();

    public abstract BufferedImage getDefaultImage();

    public abstract void drawInInventory(Graphics2D g2d);

    public abstract void drawInHand(Graphics2D g2d, Creature creature);

}
