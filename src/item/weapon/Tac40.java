package item.weapon;

import game.Game;
import entity.EntityItem;
import entity.creature.Creature;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Point;
import javax.imageio.ImageIO;

public class Tac40 extends Weapon {
    
    static BufferedImage facingNorth, facingSouth, facingEast, facingWest;

    static final int width = 11;
    static final int height = 9;

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
        return new EntityItem(this, x, y);
    }

    @Override
    public BufferedImage getDefaultImage() {
        return facingWest;
    }

    @Override
    public final int getWidth() {
        return width;
    }

    @Override
    public final int getHeight() {
        return height;
    }

    @Override
    public void drawInInventory(Graphics2D g2d) {

    }

    @Override
    public void drawInHand(Graphics2D g2d, Creature creature) {
        Point hand = creature.getHand();
        switch(creature.direction) {
            case NORTH:
                g2d.drawImage(facingNorth, Game.getInstance().camera.calculateScreenX(hand.x, 0), Game.getInstance().camera.calculateScreenY(hand.y, 0), width, height, null);
                break;
            case SOUTH:
                g2d.drawImage(facingSouth, Game.getInstance().camera.calculateScreenX(hand.x, 0), Game.getInstance().camera.calculateScreenY(hand.y, 0), width, height, null);
                break;
            case EAST:
                g2d.drawImage(facingEast, Game.getInstance().camera.calculateScreenX(hand.x, 0), Game.getInstance().camera.calculateScreenY(hand.y, 0), width, height, null);
                break;
            case WEST:
                g2d.drawImage(facingWest, Game.getInstance().camera.calculateScreenX(hand.x, 0), Game.getInstance().camera.calculateScreenY(hand.y, 0), width, height, null);
                break;
        }

    }

    public static void loadImages() {
        try {
            facingNorth = ImageIO.read(Tac40.class.getResourceAsStream("/res/item/weapon/tac40/facingNorth.png"));
            facingSouth = ImageIO.read(Tac40.class.getResourceAsStream("/res/item/weapon/tac40/facingSouth.png"));
            facingEast = ImageIO.read(Tac40.class.getResourceAsStream("/res/item/weapon/tac40/facingEast.png"));
            facingWest = ImageIO.read(Tac40.class.getResourceAsStream("/res/item/weapon/tac40/facingWest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
