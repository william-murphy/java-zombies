package entity;

import java.awt.Graphics2D;
import java.util.LinkedList;

import entity.creature.*;
import game.Game;
import item.weapon.Tac40;
import tile.Tile;

public class EntityList {
    
    public Player player = new Player(Player.playerSpawnX, Player.playerSpawnY);

    LinkedList<Entity> entities = new LinkedList<>();
    LinkedList<Entity> toRemove = new LinkedList<>();

    public EntityList() {
        entities.add(player);
        // temp
        entities.add(new EntityItem(new Tac40(), Player.playerSpawnX - 200, Player.playerSpawnY));
    }

    public void add(Entity entity) {
        entities.add(entity);
    }

    public void remove(Entity entity) {
        toRemove.add(entity);
    }

    public void update() {
        // attemptZombieSpawn()
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

    public void attemptZombieSpawn() {
        if (Zombie.numZombies < Zombie.getMaxZombies() && Game.getInstance().tick % Player.spawnDelay == 0) {
            Tile playerTile = player.getTile();
            int spawnCol = playerTile.col + (Game.getInstance().random.nextInt(2 * Player.spawnZombieRadius + 1) - Player.spawnZombieRadius);
            int spawnRow = playerTile.row + (Game.getInstance().random.nextInt(2 * Player.spawnZombieRadius + 1) - Player.spawnZombieRadius);
            if (!Tile.map[spawnCol][spawnRow].collision) {
                entities.add(new Zombie(spawnCol * Game.tileSize + 16, spawnRow * Game.tileSize + 16));
                Zombie.numZombies++;
            }
        }
    }

    public static void loadImages() {
        Player.loadImages();
        Zombie.loadImages();
    }

}
