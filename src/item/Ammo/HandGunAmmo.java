package item.ammo;

import entity.*;
import entity.creature.Creature;
import entity.creature.Player;
import game.Game;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class HandGunAmmo extends Ammo {
    
    static final int width = 8;
    static final int height = 8;

    static BufferedImage facingNorth, facingSouth, facingEast, facingWest;
    static int damage = 5;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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
    public void drawInHand(Graphics2D g2d, Creature creature) {}

    @Override
    public void drawAsProjectile(Graphics2D g2d, Projectile projectile) {
        switch(projectile.direction) {
            case NORTH:
                g2d.drawImage(facingNorth, Game.getInstance().camera.calculateScreenX(projectile.hitbox.x, 0), Game.getInstance().camera.calculateScreenY(projectile.hitbox.y, 0), width, height, null);
                break;
            case SOUTH:
                g2d.drawImage(facingSouth, Game.getInstance().camera.calculateScreenX(projectile.hitbox.x, 0), Game.getInstance().camera.calculateScreenY(projectile.hitbox.y, 0), width, height, null);
                break;
            case EAST:
                g2d.drawImage(facingEast, Game.getInstance().camera.calculateScreenX(projectile.hitbox.x, 0), Game.getInstance().camera.calculateScreenY(projectile.hitbox.y, 0), width, height, null);
                break;
            case WEST:
                g2d.drawImage(facingWest, Game.getInstance().camera.calculateScreenX(projectile.hitbox.x, 0), Game.getInstance().camera.calculateScreenY(projectile.hitbox.y, 0), width, height, null);
                break;
        }
    }

    @Override
    public void drawInInventory(Graphics2D g2d) {

    }

    public static void loadImages() {
        try {
            facingNorth = ImageIO.read(Player.class.getResourceAsStream("/res/item/ammo/handgun/facingNorth.png"));
            facingSouth = ImageIO.read(Player.class.getResourceAsStream("/res/item/ammo/handgun/facingSouth.png"));
            facingEast = ImageIO.read(Player.class.getResourceAsStream("/res/item/ammo/handgun/facingEast.png"));
            facingWest = ImageIO.read(Player.class.getResourceAsStream("/res/item/ammo/handgun/facingWest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
