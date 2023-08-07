package entity.livingentity;

import entity.*;

public abstract class LivingEntity extends MovableEntity {

    public boolean dead;
    public int strength;
    public int maxHealth;
    public int health;

    public abstract void receiveDamage(int amount);

}
