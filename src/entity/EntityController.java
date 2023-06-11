package entity;

import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;

import game.Game;
import tile.TileController;

public class EntityController {
    
    Game game;

    //player spawn
    public static final int playerSpawnX = (TileController.worldWidth / 2) - (Game.tileSize / 2);;
    public static final int playerSpawnY = (TileController.worldHeight / 2) - (Game.tileSize / 2);
    
    public Player player;
    private int total = 1;
    private int max = 1; //NOTE: change back to 10
    private int spawnDelay = 5 * Game.FPS; //spawn zombie every 5 seconds
    public Set<Entity> entities = new HashSet<Entity>(max);

    public EntityController(Game game) {
        this.game = game;
        this.player = new Player(game, playerSpawnX, playerSpawnY);
        entities.add(player);
    }

    public void attemptZombieSpawn() {
        //attempt zombie spawn
        if (total < max && game.tick % spawnDelay == 0) {
            final int x = game.random.nextInt((player.getWorldX() + player.spawnZombieRadius) - (player.getWorldX() - player.spawnZombieRadius)) + (player.getWorldX() - player.spawnZombieRadius);
            final int y = game.random.nextInt((player.getWorldY() + player.spawnZombieRadius) - (player.getWorldY() - player.spawnZombieRadius)) + (player.getWorldY() - player.spawnZombieRadius);
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
