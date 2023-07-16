package entity;

import common.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Point;

public class EntityItem extends Entity {
    
    BufferedImage image;

    public EntityItem(BufferedImage image, int spawnX, int spawnY, int width, int height) {
        this.image = image;
        this.hitbox = new Hitbox(spawnX, spawnY, width, height);
    }

    // TODO - make Mob abstract class so that this class doesnt have to implement getHand
    @Override
    public Point getHand() {
        return new Point(0, 0); // temp
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
    }

}
