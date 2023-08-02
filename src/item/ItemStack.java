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

    public void add(int amount) {
        this.size += amount;
    }

    public void subtract(int amount) {
        if (amount > size) {
            this.size = 0;
        } else {
            this.size -= amount;
        }
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public void spawnEntityItem(int x, int y) {
        new EntityItem(this).spawn(x, y);
    }

}
