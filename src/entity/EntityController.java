package entity;

import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;

import game.Game;

public class EntityController {
    
    Game game;
    
    public Player player;
    private int total = 1;
    private int max = 3; //NOTE: change back to 10
    private int spawnDelay = 5 * Game.FPS; //spawn zombie every 5 seconds
    public Set<Entity> entities = new HashSet<Entity>(max);

    public EntityController(Game game) {
        this.game = game;
        this.player = new Player(game);
        entities.add(player);
    }

    public void attemptZombieSpawn() {
        //attempt zombie spawn
        if (total < max && game.tick % spawnDelay == 0) {
            final int x = game.random.nextInt((player.x + player.spawnZombieRadius) - (player.x - player.spawnZombieRadius)) + (player.x - player.spawnZombieRadius);
            final int y = game.random.nextInt((player.y + player.spawnZombieRadius) - (player.y - player.spawnZombieRadius)) + (player.y - player.spawnZombieRadius);
            entities.add(new Zombie(game, x, y));
            total++;
        }
    }

    public void updateEntities() {
        attemptZombieSpawn();
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
