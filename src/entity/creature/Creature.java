package entity.creature;

import entity.Entity;
import item.Item;

import java.awt.Point;

public abstract class Creature extends Entity {

    public int maxHealth;
    public int health;

    int inventorySize;
    int curItem = 0;
    Item[] items;

    public abstract Point getHand();

    public boolean isHoldingItem() {
        return items != null && items[this.curItem] != null;
    }

}
