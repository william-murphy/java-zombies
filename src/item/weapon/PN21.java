package item.weapon;

import game.Game;
import common.*;
import entity.EntityItem;
import entity.creature.Creature;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Point;
import javax.imageio.ImageIO;

public class PN21 extends Weapon {
    
    static BufferedImage facingWest, facingEast;

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
        return new EntityItem(facingWest, x, y, width, height);
    }

    @Override
    public void drawInInventory(Graphics2D g2d) {

    }

    @Override
    public void drawInHand(Graphics2D g2d, Creature creature) {
        Point hand = creature.getHand();
        g2d.drawImage(creature.direction == Direction.WEST ? facingWest : facingEast, Game.getInstance().camera.calculateScreenX(hand.x, 0), Game.getInstance().camera.calculateScreenY(hand.y, 0), width, height, null);
    }

    public static void loadImages() {
        try {
            facingWest = ImageIO.read(PN21.class.getResourceAsStream("/res/item/weapon/pn21/west.png"));
            facingEast = ImageIO.read(PN21.class.getResourceAsStream("/res/item/weapon/pn21/east.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
