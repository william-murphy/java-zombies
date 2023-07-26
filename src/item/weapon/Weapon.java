package item.weapon;

import item.Item;

public abstract class Weapon extends Item {

    static int range;
    static int damage;
    static int capacity;

    public static void loadImages() {
        Tac40.loadImages();
    }

}