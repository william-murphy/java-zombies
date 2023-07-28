package item.ammo;

import entity.*;
import entity.creature.Creature;

import java.awt.Graphics2D;
// import javax.imageio.ImageIO;
// import java.io.IOException;
import java.awt.image.BufferedImage;

public class HandGunAmmo extends Ammo {
    
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public BufferedImage getDefaultImage() {
        return null;
    }

    @Override
    public void spawnEntityItem(int x, int y) {
        new EntityItem(this).spawn(x, y);
    }

    @Override
    public void drawInHand(Graphics2D g2d, Creature creature) {

    }

    @Override
    public void drawInInventory(Graphics2D g2d) {

    }

}
