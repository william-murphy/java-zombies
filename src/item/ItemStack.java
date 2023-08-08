package item;

import entity.EntityItem;

public class ItemStack {

    Item item;
    int size;

    public ItemStack(Item item, int size) {
        this.item = item;
        this.size = size;
    }

    public ItemStack(Item item) {
        this.item = item;
        this.size = 1;
    }

    public Item getItem() {
        return this.item;
    }

    public int getSize() {
        return this.size;
    }

    // Add given amount to itemStack and return any leftover
    public int add(int amount) {
        if (amount > item.maxStack - size) {
            int leftover = amount - (item.maxStack - size);
            size = item.maxStack;
            return leftover;
        } else {
            size += amount;
            return 0;
        }
    }

    // Subtract given amount from itemStack and return any leftover
    public int subtract(int amount) {
        if (amount > size) {
            int leftover = amount - size;
            size = 0;
            return leftover;
        } else {
            size -= amount;
            return 0;
        }
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public void spawnEntityItem(int x, int y) {
        new EntityItem(this).spawn(x, y);
    }

}
