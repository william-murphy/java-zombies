package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.Game;

public class EntityController {
    
    Game game;
    
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public Player player;

    int zombieCount = 0;
    int maxConcurrentZombies = 1; //NOTE: change back to 10
    int spawnZombieDelay = 5 * Game.FPS; //spawn zombie every 5 seconds

    public EntityController(Game game) {
        this.game = game;
        this.player = new Player(game);
        entities.add(player);
    }

    public void attemptZombieSpawn() {
        if (zombieCount < maxConcurrentZombies && game.tick % spawnZombieDelay == 0) {
            final int x = game.random.nextInt((player.x + player.spawnZombieRadius) - (player.x - player.spawnZombieRadius)) + (player.x - player.spawnZombieRadius);
            final int y = game.random.nextInt((player.y + player.spawnZombieRadius) - (player.y - player.spawnZombieRadius)) + (player.y - player.spawnZombieRadius);
            entities.add(new Zombie(game, x, y));
            zombieCount++;
        }
    }

    public void updateEntities() {
        attemptZombieSpawn();
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    public void drawEntities(Graphics2D g2d) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).draw(g2d);
        }
    }

    public static void loadImages() {
        Player.loadImages();
        Zombie.loadImages();
    }

}
