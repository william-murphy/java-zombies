package item;

import common.*;

import java.awt.image.BufferedImage;

public class Item {
    
    public Hitbox hitbox;
    public BufferedImage image;

    public void use() {
        // to be implemented in subclasses
    }

    public void stopUse() {
        // to be implemented in subclasses
    }

}
