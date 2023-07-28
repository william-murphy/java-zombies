package item.weapon;

import game.Game;
import entity.EntityItem;
import entity.creature.Creature;
import common.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tac40 extends Weapon {
    
    static BufferedImage facingNorth, facingSouth, facingEast, facingWest;

    static final int width = 16;
    static final int height = 16;

    static int range = 4;
    static int damage = 3;
    static int capacity = 10;

    private void pullTrigger() {
        if (!pullingTrigger) {
            pullingTrigger = true;
            // shoot gun somehow
        }
    }

    private void releaseTrigger() {
        pullingTrigger = false;
    }

    @Override
    public void use() {
        System.out.println("shooting");
        pullTrigger();
    }

    @Override
    public void stopUse() {
        System.out.println("stop shooting");
        releaseTrigger();
    }

    @Override
    public void spawnEntityItem(int x, int y) {
        new EntityItem(this).spawn(x, y);
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
        Hand hand = creature.getHand();
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
