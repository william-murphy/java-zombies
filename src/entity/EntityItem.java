package entity;

import common.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class EntityItem extends Entity {
    
    BufferedImage image;

    public EntityItem(BufferedImage image, int spawnX, int spawnY, int width, int height) {
        this.image = image;
        this.hitbox = new Hitbox(spawnX, spawnY, width, height);
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
    }

}
