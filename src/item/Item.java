package item;

import entity.livingentity.Player;

import java.awt.Graphics2D;

public abstract class Item  {

    public final static int width = 16;
    public final static int height = 16;
    public int maxStack;

    public abstract void use(Player player);

    public abstract void stopUse();

    public abstract void secondaryUse(Player player);

    public abstract void stopSecondaryUse();

    public abstract void draw(Graphics2D g2d, int x, int y);

    public abstract void drawInHand(Graphics2D g2d, Player player);

    public abstract void drawHud(Graphics2D g2d, int x, int y);

}
