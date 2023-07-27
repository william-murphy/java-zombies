package item;

import entity.creature.Creature;
import item.weapon.Weapon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Item  {

    public abstract void use();

    public abstract void stopUse();

    public abstract void spawnEntityItem(int x, int y);

    public abstract BufferedImage getDefaultImage();

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract void drawInInventory(Graphics2D g2d);

    public abstract void drawInHand(Graphics2D g2d, Creature creature);

    public static void loadImages() {
        Weapon.loadImages();
    }

}
