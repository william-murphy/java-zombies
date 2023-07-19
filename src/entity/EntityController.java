package entity;

import java.awt.Graphics2D;
import java.util.LinkedList;

import entity.creature.*;
import game.Game;
import item.weapon.PN21;
import tile.Tile;

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

    public void attemptZombieSpawn() {
        if (Zombie.numZombies < Zombie.getMaxZombies() && Game.getInstance().tick % Player.spawnDelay == 0) {
            Tile playerTile = player.getTile();
            int spawnCol = playerTile.col + (Game.getInstance().random.nextInt(2 * Player.spawnZombieRadius + 1) - Player.spawnZombieRadius);
            int spawnRow = playerTile.row + (Game.getInstance().random.nextInt(2 * Player.spawnZombieRadius + 1) - Player.spawnZombieRadius);
            if (!Tile.map[spawnCol][spawnRow].collision) {
                Game.getInstance().entityController.add(new Zombie(spawnCol * Game.tileSize + 16, spawnRow * Game.tileSize + 16));
                Zombie.numZombies++;
            }
        }
    }

    public static void loadImages() {
        Player.loadImages();
        Zombie.loadImages();
    }

}
