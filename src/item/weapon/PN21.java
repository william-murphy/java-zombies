package item.weapon;

import entity.EntityItem;
import game.Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Point;
import javax.imageio.ImageIO;

public class PN21 extends Weapon {
    
    static BufferedImage image;

    final static int width = 11;
    final static int height = 9;

    static int range = 4;
    static int damage = 3;
    static int capacity = 10;

    @Override
    public void use() {
        System.out.println("shooting");
    }

    @Override
    public void stopUse() {
        System.out.println("stop shooting");
    }

    @Override
    public EntityItem createEntityItem(int x, int y) {
        return new EntityItem(image, x, y, width, height);
    }

    @Override
    public void drawInInventory(Graphics2D g2d) {

    }

    @Override
    public void drawInHand(Graphics2D g2d, Point hand) {
        g2d.drawImage(image, Game.getInstance().camera.calculateScreenX(hand.x, 0), Game.getInstance().camera.calculateScreenY(hand.y, 0), width, height, null);
    }

    public static void loadImages() {
        try {
            image = ImageIO.read(PN21.class.getResourceAsStream("/res/item/weapon/pn21.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
