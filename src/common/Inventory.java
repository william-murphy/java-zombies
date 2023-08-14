package common;

import entity.livingentity.*;
import item.ItemStack;
import item.Item;

import java.awt.Graphics2D;
import java.awt.Color;

public class Inventory {

    static final int squareSize = 32;

    Player player;
    ItemStack[] items;
    int current = 0;
    int next = 0;

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

    public Item getCurrentItem() {
        if (isHoldingItem()) {
            return items[current].getItem();
        }
        return null;
    }

    public void transfer(ItemStack receiver, int amount) {
        int stackSize;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getItem().equals(receiver.getItem())) {
                if (items[i].getSize() < amount) {
                    stackSize = items[i].getSize();
                    items[i] = null;
                    receiver.add(stackSize);
                    amount -= stackSize;
                } else if (items[i].getSize() == amount) {
                    items[i] = null;
                    receiver.add(amount);
                    return;
                } else {
                    items[i].subtract(amount);
                    receiver.add(amount);
                    return;
                }
            } 
        }
    }

    public void add(ItemStack itemStack) {
        int nullIndex = -1;
        int remaining = itemStack.getSize();
        for (int i = 0; i < items.length; i++) {
            if (itemStack.getItem().maxStack > 1 && items[i] != null && items[i].getItem().equals(itemStack.getItem())) {
                // if the same item exists in the inventory add to it
                remaining = items[i].add(remaining);
                itemStack.subtract(itemStack.getSize() - remaining);
                if (remaining <= 0) return;
            } else if (nullIndex == -1 && items[i] == null) {
                // find a null index so if the above condition is never met we have a backup open slot to add itemStack to
                nullIndex = i;
            }
        }
        if (nullIndex != -1 && remaining > 0) {
            // if we've gotten this far without returning try to add itemStack at the null index if we found one
            items[nullIndex] = itemStack;
        }
    }

    public void remove() {
        if (isHoldingItem()) {
            ItemStack removed = items[current];
            items[current] = null;
            if (current < next) {
                next = current;
            }
            removed.spawnEntityItem(player.hitbox.x, player.hitbox.y);
        }
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

    public void draw(Graphics2D g2d, int x, int y) {
        for (int i = 0; i < items.length; i++) {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(x + (squareSize + 1) * i, y, squareSize, squareSize);
            if (i == current) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawRect(x + (squareSize + 1) * i, y, squareSize, squareSize);
            if (items[i] != null) {
                items[i].getItem().draw(g2d, x + (i * squareSize) + 4, y + 4);
                if (items[i].getItem().maxStack > 1) {
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(String.valueOf(items[i].getSize()), x + (i * squareSize) + 20, y + 28);
                }
            }
        }
    }

}
