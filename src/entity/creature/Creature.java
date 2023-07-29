package entity.creature;

import item.ItemStack;
import entity.*;
import common.*;

public abstract class Creature extends MovableEntity {

    public int maxHealth;
    public int health;

    int inventorySize;
    int curItem = 0;
    ItemStack[] inventory;

    public boolean isHoldingItem() {
        return inventory != null && inventory[this.curItem] != null;
    }

    public void pickupItem(EntityItem item) {
        item.despawn();
        inventory[curItem] = item.getItemStack();
    }

    public void dropItem() {
        if (isHoldingItem()) {
            inventory[curItem].spawnEntityItem(hitbox.x, hitbox.y);
            inventory[curItem] = null;
        }
    }

    public void useItem() {
        if (isHoldingItem()) {
            inventory[curItem].getItem().use();
        }
    }

    public void stopUseItem() {
        if (isHoldingItem()) {
            inventory[curItem].getItem().stopUse();
        }
    }

    public abstract Hand getHand();

}
