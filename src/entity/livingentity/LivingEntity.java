package entity.livingentity;

import entity.*;
import game.Game;

import java.lang.Math;

public abstract class LivingEntity extends MovableEntity {

    static final int damageDelay = Game.FPS; // 1 second

    int lastDamageTick = 0;
    public boolean dead;
    public int strength;
    public int maxHealth;
    public int health;

    public void receiveDamage(int amount) {
        if (Math.abs(Game.getInstance().tick - this.lastDamageTick) >= LivingEntity.damageDelay) {
            this.health -= amount;
            if (this.health <= 0) {
                this.dead = true;
                this.despawn();
            }
            this.lastDamageTick = Game.getInstance().tick;
        }
    }

}
