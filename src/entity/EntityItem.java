package entity;

import game.Game;
import common.*;
import item.*;

import java.awt.Graphics2D;

public class EntityItem extends Entity {
    
    final static int pickupDelay = Game.FPS * 2; // 2 seconds
    int spawnTick;
    ItemStack itemStack;

    public EntityItem(ItemStack itemStack) {
        this.hitbox = new Hitbox(0, 0, Item.width, Item.height);
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    @Override
    public void spawn(int x, int y) {
        hitbox.setLocation(x, y);
        tile = getTile();
        tile.entities.add(this);
        spawnTick = Game.getInstance().tick;
        Game.getInstance().entityList.add(this);
    }

    @Override
    public void despawn() {
        tile.entities.remove(this);
        Game.getInstance().entityList.remove(this);
    }

    @Override
    public void update() {
        if (tile.entities.size() > 1 && Game.getInstance().tick - spawnTick >= pickupDelay && Game.getInstance().entityList.player.hitbox.intersects(hitbox)) {
            Game.getInstance().entityList.player.pickupItem(this);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        itemStack.getItem().draw(g2d, Game.getInstance().camera.calculateScreenX(hitbox.x, 0), Game.getInstance().camera.calculateScreenY(hitbox.y, 0));
    }

}
