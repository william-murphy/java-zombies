package item.ammo;

import item.*;
import entity.Projectile;

import java.awt.Graphics2D;

public abstract class Ammo extends Item {
    
    static int damage;

    @Override
    public void use() {}

    @Override
    public void stopUse() {}

    public abstract void drawAsProjectile(Graphics2D g2d, Projectile projectile);

    public static void loadImages() {
        HandGunAmmo.loadImages();
    }

}
