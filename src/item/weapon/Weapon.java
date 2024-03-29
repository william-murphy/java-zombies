package item.weapon;

import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import entity.livingentity.Player;
import entity.Projectile;
import game.Game;
import item.*;
import item.ammo.*;

public class Weapon extends Item {

    public static Weapon tac40 = new Weapon("tac40", Ammo.handgunAmmo, 8, 10);

    BufferedImage itemNorth, itemSouth, itemEast, itemWest;
    boolean pullingTrigger = false;
    ItemStack magazine;
    int damage;
    int capacity;

    private Weapon(String name, Ammo ammo, int damage, int capacity) {
        loadImages(name);
        this.magazine = new ItemStack(ammo, 0);
        this.damage= damage;
        this.capacity = capacity;
        this.maxStack = 1;
    }

    private void loadImages(String name) {
        try {
            itemNorth = ImageIO.read(Weapon.class.getResourceAsStream(String.format("/res/item/weapon/%s/itemNorth.png", name)));
            itemSouth = ImageIO.read(Weapon.class.getResourceAsStream(String.format("/res/item/weapon/%s/itemSouth.png", name)));
            itemEast = ImageIO.read(Weapon.class.getResourceAsStream(String.format("/res/item/weapon/%s/itemEast.png", name)));
            itemWest = ImageIO.read(Weapon.class.getResourceAsStream(String.format("/res/item/weapon/%s/itemWest.png", name)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void use(Player player) {
        if (!pullingTrigger) {
            pullingTrigger = true;
            if (!magazine.isEmpty()) {
                magazine.subtract(1);
                new Projectile((Ammo)this.magazine.getItem(), this.damage, player.direction).spawn(player.hand.x, player.hand.y);
            }
        }
    }

    @Override
    public void stopUse() {
        pullingTrigger = false;
    }

    @Override
    public void secondaryUse(Player player) {
        if (magazine.isEmpty()) {
            player.inventory.transfer(magazine, capacity);
        }
    }

    @Override
    public void stopSecondaryUse() {}

    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        g2d.drawImage(itemWest, x, y, width, height, null);
    }

    @Override
    public void drawInHand(Graphics2D g2d, Player player) {
        switch(player.direction) {
            case NORTH:
                g2d.drawImage(itemNorth, Game.getInstance().camera.calculateScreenX(player.hand.x - width / 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - height + 2, 0), width, height, null);
                break;
            case SOUTH:
                g2d.drawImage(itemSouth, Game.getInstance().camera.calculateScreenX(player.hand.x - width / 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - 2, 0), width, height, null);
                break;
            case EAST:
                g2d.drawImage(itemEast, Game.getInstance().camera.calculateScreenX(player.hand.x - 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - height / 2, 0), width, height, null);
                break;
            case WEST:
                g2d.drawImage(itemWest, Game.getInstance().camera.calculateScreenX(player.hand.x - width + 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - height / 2, 0), width, height, null);
                break;
        }
    }

    @Override
    public void drawHud(Graphics2D g2d, int x, int y) {
        g2d.drawString(String.format("%d / %d", this.magazine.getSize(), this.capacity), x, y);
    }

}