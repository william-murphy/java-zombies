package entity.projectile;

import item.ammo.Ammo;
import entity.*;

import java.awt.Graphics2D;

public class Projectile extends MovableEntity {

    Ammo ammo;

    public Projectile(Ammo ammo, int initialSpeed) {
        this.ammo = ammo;
        this.speed = initialSpeed;
    }

    @Override
    public void spawn(int x, int y) {

    }

    @Override
    public void despawn() {
        
    }

    @Override
    public void draw(Graphics2D g2d) {
        
    }

    @Override
    public void update() {

    }

}
