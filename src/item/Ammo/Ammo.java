package item.ammo;

import item.*;
import entity.Projectile;
import entity.livingentity.*;
import game.Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ammo extends Item {
    
    public static Ammo handgunAmmo = new Ammo("handgun", 8, 8, 2);

    public int damage;

    private Ammo(String name, int width, int height, int damage) {
        loadImages(name);
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.maxStack = 64;
    }

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

    public void loadImages(String name) {
        try {
            facingNorth = ImageIO.read(Ammo.class.getResourceAsStream(String.format("/res/item/ammo/%s/facingNorth.png", name)));
            facingSouth = ImageIO.read(Ammo.class.getResourceAsStream(String.format("/res/item/ammo/%s/facingSouth.png", name)));
            facingEast = ImageIO.read(Ammo.class.getResourceAsStream(String.format("/res/item/ammo/%s/facingEast.png", name)));
            facingWest = ImageIO.read(Ammo.class.getResourceAsStream(String.format("/res/item/ammo/%s/facingWest.png", name)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void use(Player player) {}

    @Override
    public void stopUse() {}

    @Override
    public void secondaryUse(Player player) {}

    @Override
    public void stopSecondaryUse() {}

    @Override
    public BufferedImage getDefaultImage() {
        return facingWest;
    }

    @Override
    public void drawInHand(Graphics2D g2d, Player player) {
        switch(player.direction) {
            case NORTH:
                g2d.drawImage(facingNorth, Game.getInstance().camera.calculateScreenX(player.hand.x - width / 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - height + 2, 0), width, height, null);
                break;
            case SOUTH:
                g2d.drawImage(facingSouth, Game.getInstance().camera.calculateScreenX(player.hand.x - width / 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - 2, 0), width, height, null);
                break;
            case EAST:
                g2d.drawImage(facingEast, Game.getInstance().camera.calculateScreenX(player.hand.x - 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - 2, 0), width, height, null);
                break;
            case WEST:
                g2d.drawImage(facingWest, Game.getInstance().camera.calculateScreenX(player.hand.x - width + 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - 2, 0), width, height, null);
                break;
        }
    }

    @Override
    public void drawHud(Graphics2D g2d, int x, int y) {}

}
