package entity;

import item.ammo.Ammo;
import common.*;
import game.Game;

import java.awt.Graphics2D;

public class Projectile extends MovableEntity {

    Ammo ammo;

    public Projectile(Ammo ammo, int initialSpeed, Direction direction) {
        this.hitbox = new Hitbox(0, 0, ammo.width, ammo.height);
        this.ammo = ammo;
        this.speed = initialSpeed;
        this.direction = direction;
    }

    @Override
    public void spawn(int x, int y) {
        hitbox.setLocation(x, y);
        this.moving = true;
        Game.getInstance().entityList.add(this);
    }

    @Override
    public void despawn() {
        Game.getInstance().entityList.remove(this);
    }

    @Override
    public void draw(Graphics2D g2d) {
        ammo.drawAsProjectile(g2d, this);
    }

    @Override
    public void update() {
        updatePosition();
    }

}
