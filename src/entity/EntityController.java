package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.GamePanel;

public class EntityController {
    
    private GamePanel gp;
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public final Player player;

    int zombieCount = 0;
    int maxConcurrentZombies = 10;

    final int spawnDelay = 5 * 60; //every 5 seconds (theoretically)
    int counter = 0;

    public EntityController(GamePanel gp) {
        this.gp = gp;
        this.player = new Player(gp);
        entities.add(player);
    }

    public static void loadImages() {
        Player.loadImages();
        Zombie.loadImages();
    }

    public void spawnZombie() {
        final int x = gp.random.nextInt((player.x + player.spawnZombieRadius) - (player.x - player.spawnZombieRadius)) + (player.x - player.spawnZombieRadius);
        final int y = gp.random.nextInt((player.y + player.spawnZombieRadius) - (player.y - player.spawnZombieRadius)) + (player.y - player.spawnZombieRadius);
        entities.add(new Zombie(gp, x, y));
    }

    public void updateEntities() {
        if (counter >= spawnDelay && zombieCount < maxConcurrentZombies) {
            spawnZombie();
            zombieCount++;
            counter = 0;
        }
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        counter++;
    }

    public void drawEntities(Graphics2D g2d) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).draw(g2d);
        }
    }

}
