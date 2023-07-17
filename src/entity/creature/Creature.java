package entity.creature;

import entity.Entity;

import java.awt.Point;

public abstract class Creature extends Entity {

    public int maxHealth;
    public int health;

    public abstract Point getHand();

}
