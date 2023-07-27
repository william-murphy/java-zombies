package entity;

import java.awt.Graphics2D;
import java.util.LinkedList;

import entity.creature.*;
import item.weapon.Tac40;

public class EntityList {
    
    public Player player = new Player(Player.playerSpawnX, Player.playerSpawnY);

    LinkedList<Entity> entities = new LinkedList<>();
    LinkedList<Entity> toRemove = new LinkedList<>();
    LinkedList<Entity> toAdd = new LinkedList<>();

    public EntityList() {
        entities.add(player);
        // temp
        entities.add(new EntityItem(new Tac40(), Player.playerSpawnX - 200, Player.playerSpawnY));
    }

    public void add(Entity entity) {
        toAdd.add(entity);
    }

    public void remove(Entity entity) {
        toRemove.add(entity);
    }

    public void update() {
        entities.addAll(toAdd);
        toAdd.clear();
        for (Entity entity : entities) {
            entity.update();
        }
        entities.removeAll(toRemove);
        toRemove.clear();
    }

    public void drawAll(Graphics2D g2d) {
        for (Entity entity : entities) {
            entity.draw(g2d);
        }
    }

    public static void loadImages() {
        Player.loadImages();
        Zombie.loadImages();
    }

}
