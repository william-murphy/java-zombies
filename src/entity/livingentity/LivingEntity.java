package entity.livingentity;

import entity.*;
import game.Game;

public abstract class LivingEntity extends MovableEntity {

    static final int healthRegenDelay = Game.FPS * 3;

    protected int lastHealthRegenTick;
    public boolean dead;
    public int strength;
    public int maxHealth;
    public int health;

    public abstract void receiveDamage(int amount);

    public void regenerateHealth() {
        if (this.health < this.maxHealth && Game.getInstance().tick - this.lastHealthRegenTick >= healthRegenDelay) {
            this.lastHealthRegenTick = Game.getInstance().tick;
            this.health++;
        }
    }

}
