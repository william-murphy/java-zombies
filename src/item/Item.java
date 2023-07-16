package item;

import entity.EntityItem;
import entity.creature.Creature;
import item.weapon.Weapon;

import java.awt.Graphics2D;

public abstract class Item  {

    static int width;
    static int height;

    public abstract void use();

    public abstract void stopUse();

    public abstract EntityItem createEntityItem(int x, int y);

    public abstract void drawInInventory(Graphics2D g2d);

    public abstract void drawInHand(Graphics2D g2d, Creature creature);

    public static void loadImages() {
        Weapon.loadImages();
    }

}
