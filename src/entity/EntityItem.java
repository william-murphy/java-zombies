package entity;

import game.Game;
import common.*;
import item.ItemStack;

import java.awt.Graphics2D;

public class EntityItem extends Entity {
    
    final static int pickupDelay = Game.FPS * 2; // 2 seconds
    ItemStack itemStack;
    boolean ready;

    public EntityItem(ItemStack itemStack) {
        this.hitbox = new Hitbox(0, 0, itemStack.getItem().width, itemStack.getItem().height);
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
        ready = false;
        Game.getInstance().entityList.add(this);
    }

    @Override
    public void despawn() {
        tile.entities.remove(this);
        Game.getInstance().entityList.remove(this);
    }

    @Override
    public void update() {
        if (!ready && Game.getInstance().tick % pickupDelay == 0) {
            ready = true;
        }
        if (ready && Game.getInstance().entityList.player.hitbox.intersects(hitbox)) {
            Game.getInstance().entityList.player.pickupItem(this);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(itemStack.getItem().getDefaultImage(), Game.getInstance().camera.calculateScreenX(hitbox.x, 0), Game.getInstance().camera.calculateScreenY(hitbox.y, 0), hitbox.width, hitbox.height, null);
    }

}
