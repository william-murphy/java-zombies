package item;

import game.Game;
import common.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Weapon extends Item {

    //temp
    public static Weapon pn21 = new Weapon("pn21", 10, 25, 6, 11, 9);

    int range;
    int damage;
    int capacity;

    public Weapon(String name, int range, int damage, int capacity, int width, int height) {
        setImage(name);
        this.range = range;
        this.damage = damage;
        this.capacity = capacity;
        this.hitbox = new Hitbox(0, 0, width, height);
    }

    public void use() {
        System.out.println("holding trigger");
    }

    public void stopUse() {
        System.out.println("released trigger");
    }

    void setImage(String name) {
        try {
            image = ImageIO.read(Weapon.class.getResourceAsStream(String.format("/res/item/weapon/%s.png", name)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}