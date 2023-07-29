package item;

import entity.EntityItem;

public class ItemStack {
    
    Item item;
    int amount;

    public ItemStack(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public ItemStack(Item item) {
        this.item = item;
        this.amount = 1;
    }

    public Item getItem() {
        return this.item;
    }

    public void spawnEntityItem(int x, int y) {
        new EntityItem(this).spawn(x, y);
    }

}
