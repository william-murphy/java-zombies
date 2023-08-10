package entity;

import common.*;
import tile.Tile;
import entity.livingentity.*;
import game.Game;

import java.awt.Graphics2D;

public abstract class Entity implements Collidable {

    public static final int attackDelay = Game.FPS;

    public Hitbox hitbox;
    public boolean collision = true;

    public Tile tile;

    protected int animationCounter = 0;
    protected boolean animationStep = false;

    protected int lastAttackTick;

    public Tile getTile() {
        return Tile.getTile((int)hitbox.getCenterX(), (int)hitbox.getCenterY());
    }

    protected void updateAnimation() {
        animationCounter++;
        if (animationCounter > 15) {
            animationStep = !animationStep;
            animationCounter = 0;
        }
    }

    protected void damage(LivingEntity entity, int amount) {
        if (Game.getInstance().tick - this.lastAttackTick >= attackDelay) {
            this.lastAttackTick = Game.getInstance().tick;
            entity.receiveDamage(amount);
        }
    }

    @Override 
    public boolean hasCollision() {
        return this.collision;
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    public abstract void spawn(int x, int y);

    public abstract void despawn();

    public abstract void update();

    public abstract void draw(Graphics2D g2d);

}