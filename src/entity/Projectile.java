package entity;

import item.ammo.Ammo;
import common.*;
import game.Game;
import entity.livingentity.*;

import java.awt.Graphics2D;

public class Projectile extends MovableEntity {

    Ammo ammo;

    public Projectile(Ammo ammo, int initialSpeed, Direction direction) {
        this.hitbox = new Hitbox(0, 0, ammo.width, ammo.height);
        this.ammo = ammo;
        this.speed = initialSpeed;
        this.direction = direction;
    }
    
    private void checkZombieCollision() {
        if (this.tile.entities.size() > 1) {
            for (Entity entity : this.tile.entities) {
                if (entity.getClass().equals(Zombie.class) && this.collides(entity)) {
                    this.damage((LivingEntity)entity, (ammo.damage + this.speed) / 2);
                    this.despawn();
                }
            }
        }
    }

    @Override
    public void spawn(int x, int y) {
        hitbox.setLocation(x, y);
        tile = getTile();
        tile.entities.add(this);
        this.moving = true;
        Game.getInstance().entityList.add(this);
    }

    @Override
    public void despawn() {
        tile.entities.remove(this);
        Game.getInstance().entityList.remove(this);
    }

    @Override
    public void draw(Graphics2D g2d) {
        ammo.drawAsProjectile(g2d, this);
    }

    @Override
    public void update() {
        updatePosition();
        checkZombieCollision();
        if (checkCollision()) {
            despawn();
        }
        updateTile();
    }

}
