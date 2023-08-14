package item;

import entity.livingentity.Player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Item  {

    public int width, height;
    public int maxStack;
    protected BufferedImage facingNorth, facingSouth, facingEast, facingWest;

    public abstract void use(Player player);

    public abstract void stopUse();

    public abstract void secondaryUse(Player player);

    public abstract void stopSecondaryUse();

    public abstract BufferedImage getDefaultImage();

    public abstract void drawHud(Graphics2D g2d, int x, int y);

    public abstract void drawInHand(Graphics2D g2d, Player player);

}
