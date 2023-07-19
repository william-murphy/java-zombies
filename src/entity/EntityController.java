package entity;

import java.awt.Graphics2D;
import java.util.LinkedList;

import entity.creature.*;
import item.weapon.PN21;

public class EntityController {

    public Player player = new Player(Player.playerSpawnX, Player.playerSpawnY);

    // initialization
    LinkedList<Entity> entities = new LinkedList<>();

    public EntityController() {
        // temp
        this.add(player);
        this.add(new EntityItem(new PN21(), Player.playerSpawnX - 200, Player.playerSpawnY));
    }

    public void add(Entity entity) {
        entities.add(entity);
    }

    public void remove(Entity entity) {
        entities.remove(entity);
    }

    public void updateEntities() {
        player.attemptZombieSpawn();
        for (Entity entity : entities) {
            entity.update();
        }
    }

    public void drawEntities(Graphics2D g2d) {
        for (Entity entity : entities) {
            entity.draw(g2d);
        }
    }

    public static void loadImages() {
        Player.loadImages();
        Zombie.loadImages();
    }

}
