package entity;

import game.Game;
import common.*;
import item.Item;

import java.awt.Graphics2D;

public class EntityItem extends Entity {
    
    Item item;

    public EntityItem(Item item) {
        this.hitbox = new Hitbox(0, 0, item.getWidth(), item.getHeight());
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    @Override
    public void spawn(int x, int y) {
        hitbox.setLocation(x, y);
        Game.getInstance().entityList.add(this);
    }

    @Override
    public void despawn() {
        Game.getInstance().entityList.remove(this);
    }

    @Override
    public void update() {
        if (Game.getInstance().entityList.player.hitbox.intersects(hitbox)) {
            Game.getInstance().entityList.player.pickupItem(this);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(item.getDefaultImage(), Game.getInstance().camera.calculateScreenX(hitbox.x, 0), Game.getInstance().camera.calculateScreenY(hitbox.y, 0), hitbox.width, hitbox.height, null);
    }

}
