package entity;

import java.awt.Graphics2D;

import entity.creature.*;
import item.weapon.PN21;

public class EntityController {
    
    public int size = 0;
    public int max = 4;

    public Player player = new Player(Player.playerSpawnX, Player.playerSpawnY);

    // initialization
    Entity[] entities = new Entity[max];

    public EntityController() {
        // temp
        this.add(player);
        this.add(new EntityItem(new PN21(), Player.playerSpawnX - 200, Player.playerSpawnY));
    }

    public void add(Entity entity) {
        if (size < max) {
            entities[size] = entity;
            size++;
        }
    }

    public void updateEntities() {
        for (Entity entity : entities) {
            if (entity != null) {
                entity.update();
            }
        }
    }

    public void drawEntities(Graphics2D g2d) {
        for (Entity entity : entities) {
            if (entity != null) {
                entity.draw(g2d);
            }
        }
    }

    public static void loadImages() {
        Player.loadImages();
        Zombie.loadImages();
    }

}
