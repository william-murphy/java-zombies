package entity;

import game.Game;
import common.*;
import item.Item;

import java.awt.Graphics2D;

public class EntityItem extends Entity {
    
    Item item;

    public EntityItem(Item item, int spawnX, int spawnY) {
        this.hitbox = new Hitbox(spawnX, spawnY, item.getWidth(), item.getHeight());
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    @Override
    public void spawn() {
        Game.getInstance().entityList.add(this);
    }

    @Override
    public void despawn() {
        Game.getInstance().entityList.remove(this);
    }

    @Override
    public void update() {
        // TODO - scan for player rather than entityList being omniscient about player's whereabouts
        if (Game.getInstance().entityList.player.hitbox.intersects(hitbox)) {
            Game.getInstance().entityList.player.pickupItem(this);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(item.getDefaultImage(), Game.getInstance().camera.calculateScreenX(hitbox.x, 0), Game.getInstance().camera.calculateScreenY(hitbox.y, 0), hitbox.width, hitbox.height, null);
    }

}
