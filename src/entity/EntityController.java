package entity;

import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;

import game.Game;
import entity.creature.*;
import tile.TileController;
import tile.Tile;

public class EntityController {

    //player spawn
    public static final int playerSpawnX = (TileController.worldWidth / 2) - (Game.tileSize / 2);;
    public static final int playerSpawnY = (TileController.worldHeight / 2) - (Game.tileSize / 2);
    
    public Player player;
    private int total = 1;
    private int max = 3; //NOTE: change back to 10
    private int spawnDelay = 5 * Game.FPS; //spawn zombie every 5 seconds
    public Set<Entity> entities = new HashSet<Entity>(max);

    public EntityController() {
        this.player = new Player(playerSpawnX, playerSpawnY);
        entities.add(player);
    }

    public void attemptZombieSpawn() {
        //attempt zombie spawn
        if (total < max && Game.getInstance().tick % spawnDelay == 0) {
            Tile playerTile = player.getTile();
            int spawnCol = playerTile.col + (Game.getInstance().random.nextInt(2 * player.spawnZombieRadius + 1) - player.spawnZombieRadius);
            int spawnRow = playerTile.row + (Game.getInstance().random.nextInt(2 * player.spawnZombieRadius + 1) - player.spawnZombieRadius);
            if (!TileController.map[spawnCol][spawnRow].collision) {
                entities.add(new Zombie(spawnCol * Game.tileSize + 16, spawnRow * Game.tileSize + 16));
                total++;
            }
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
