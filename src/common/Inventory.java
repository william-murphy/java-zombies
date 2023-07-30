package common;

import entity.livingentity.*;
import item.ItemStack;

public class Inventory {

    Player player;
    ItemStack[] items;
    int current = 0;
    int next = 0;
    boolean isFull = false;

    public Inventory(Player player, int size) {
        this.player = player;
        items = new ItemStack[size];
    }

    public boolean isHoldingItem() {
        return items[current] != null;
    }

    public ItemStack getCurrentItemStack() {
        return items[current];
    }

    public void add(ItemStack toAdd) {
        if (!isFull) {
            items[next] = toAdd;
            int i = 0;
            while (i < items.length) {
                if (items[i] == null) {
                    next = i;
                    break;
                }
                i++;
            }
            if (i >= items.length) {
                isFull = true;
                next = i;
            }
        }
    }

    public void remove() {
        if (isHoldingItem()) {
            ItemStack removed = items[current];
            items[current] = null;
            isFull = false;
            if (current < next) {
                next = current;
            }
            removed.spawnEntityItem(player.hitbox.x, player.hitbox.y);
        }
    }

    public ItemStack getCurrent() {
        return items[current];
    }

    public void scrollRight() {
        if (current < items.length - 1) {
            current++;
        } else {
            current = 0;
        }
    }

    public void scrollLeft() {
        if (current > 0) {
            current--;
        } else {
            current = items.length - 1;
        }
    }

}
