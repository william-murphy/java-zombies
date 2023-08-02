package common;

import entity.livingentity.*;
import item.ItemStack;

import java.awt.Graphics2D;
import java.awt.Color;

public class Inventory {

    static final int squareSize = 32;

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
                g2d.drawImage(items[i].getItem().getDefaultImage(), x + (i * squareSize) + 4, y + 4, items[i].getItem().width, items[i].getItem().height, null);
                if (items[i].getItem().maxStack > 1) {
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(String.valueOf(items[i].getSize()), x + (i * squareSize) + 20, y + 28);
                }
            }
        }
    }

}
