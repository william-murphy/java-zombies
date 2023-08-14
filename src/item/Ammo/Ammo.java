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

    BufferedImage item, projectileNorth, projectileSouth, projectileEast, projectileWest;
    
    public int projectileWidth, projectileHeight;
    public int damage;

    private Ammo(String name, int width, int height, int damage) {
        loadImages(name);
        this.projectileWidth = width;
        this.projectileHeight = height;
        this.damage = damage;
        this.maxStack = 64;
    }

    private void loadImages(String name) {
        try {
            item = ImageIO.read(Ammo.class.getResourceAsStream(String.format("/res/item/ammo/%s/item.png", name)));
            projectileNorth = ImageIO.read(Ammo.class.getResourceAsStream(String.format("/res/item/ammo/%s/projectileNorth.png", name)));
            projectileSouth = ImageIO.read(Ammo.class.getResourceAsStream(String.format("/res/item/ammo/%s/projectileSouth.png", name)));
            projectileEast = ImageIO.read(Ammo.class.getResourceAsStream(String.format("/res/item/ammo/%s/projectileEast.png", name)));
            projectileWest = ImageIO.read(Ammo.class.getResourceAsStream(String.format("/res/item/ammo/%s/projectileWest.png", name)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawAsProjectile(Graphics2D g2d, Projectile projectile) {
        switch(projectile.direction) {
            case NORTH:
                g2d.drawImage(projectileNorth, Game.getInstance().camera.calculateScreenX(projectile.hitbox.x, 0), Game.getInstance().camera.calculateScreenY(projectile.hitbox.y, 0), projectileWidth, projectileHeight, null);
                break;
            case SOUTH:
                g2d.drawImage(projectileSouth, Game.getInstance().camera.calculateScreenX(projectile.hitbox.x, 0), Game.getInstance().camera.calculateScreenY(projectile.hitbox.y, 0), projectileWidth, projectileHeight, null);
                break;
            case EAST:
                g2d.drawImage(projectileEast, Game.getInstance().camera.calculateScreenX(projectile.hitbox.x, 0), Game.getInstance().camera.calculateScreenY(projectile.hitbox.y, 0), projectileWidth, projectileHeight, null);
                break;
            case WEST:
                g2d.drawImage(projectileWest, Game.getInstance().camera.calculateScreenX(projectile.hitbox.x, 0), Game.getInstance().camera.calculateScreenY(projectile.hitbox.y, 0), projectileWidth, projectileHeight, null);
                break;
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
    public void draw(Graphics2D g2d, int x, int y) {
        g2d.drawImage(item, x, y, width, height, null);
    }

    @Override
    public void drawInHand(Graphics2D g2d, Player player) {
        switch(player.direction) {
            case NORTH:
                g2d.drawImage(projectileNorth, Game.getInstance().camera.calculateScreenX(player.hand.x - width / 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - height + 2, 0), width, height, null);
                break;
            case SOUTH:
                g2d.drawImage(projectileSouth, Game.getInstance().camera.calculateScreenX(player.hand.x - width / 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - 2, 0), width, height, null);
                break;
            case EAST:
                g2d.drawImage(projectileEast, Game.getInstance().camera.calculateScreenX(player.hand.x - 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - 2, 0), width, height, null);
                break;
            case WEST:
                g2d.drawImage(projectileWest, Game.getInstance().camera.calculateScreenX(player.hand.x - width + 2, 0), Game.getInstance().camera.calculateScreenY(player.hand.y - 2, 0), width, height, null);
                break;
        }
    }

    @Override
    public void drawHud(Graphics2D g2d, int x, int y) {}

}
