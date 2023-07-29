package item.weapon;

import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import common.Hand;
import entity.creature.Creature;
import game.Game;
import item.*;
import item.ammo.*;

public class Weapon extends Item {

    public static Weapon tac40 = new Weapon("tac40", 16, 16, Ammo.handgunAmmo, 3, 10);

    boolean pullingTrigger = false;
    ItemStack magazine;
    int damage;
    int capacity;

    private Weapon(String name, int width, int height, Ammo ammo, int damage, int capacity) {
        loadImages(name);
        this.width = width;
        this.height = height;
        this.magazine = new ItemStack(ammo);
        this.damage= damage;
        this.capacity = capacity;
    }

    private void loadImages(String name) {
        try {
            facingNorth = ImageIO.read(Weapon.class.getResourceAsStream(String.format("/res/item/weapon/%s/facingNorth.png", name)));
            facingSouth = ImageIO.read(Weapon.class.getResourceAsStream(String.format("/res/item/weapon/%s/facingSouth.png", name)));
            facingEast = ImageIO.read(Weapon.class.getResourceAsStream(String.format("/res/item/weapon/%s/facingEast.png", name)));
            facingWest = ImageIO.read(Weapon.class.getResourceAsStream(String.format("/res/item/weapon/%s/facingWest.png", name)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void use() {
        if (!pullingTrigger) {
            pullingTrigger = true;
            
        }
    }

    @Override
    public void stopUse() {
        pullingTrigger = false;
    }

    @Override
    public BufferedImage getDefaultImage() {
        return facingWest;
    }

    @Override
    public void drawInInventory(Graphics2D g2d) {}

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

}