package entity.creature;

import item.Item;
import entity.*;

import java.awt.Point;

public abstract class Creature extends MovableEntity {

    public int maxHealth;
    public int health;

    int inventorySize;
    int curItem = 0;
    Item[] items;

    public boolean isHoldingItem() {
        return items != null && items[this.curItem] != null;
    }

    public void pickupItem(EntityItem item) {
        item.despawn();
        items[curItem] = item.getItem();
    }

    public void dropItem() {
        if (isHoldingItem()) {
            items[curItem].spawnEntityItem(hitbox.x, hitbox.y);
            items[curItem] = null;
        }
    }

    public void useItem() {
        if (isHoldingItem()) {
            items[curItem].use();
        }
    }

    public void stopUseItem() {
        if (isHoldingItem()) {
            items[curItem].stopUse();
        }
    }

    public abstract Point getHand();

}
