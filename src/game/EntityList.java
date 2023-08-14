package game;

import java.awt.Graphics2D;
import java.util.LinkedList;

import entity.livingentity.*;
import entity.*;
import item.ItemStack;
import item.ammo.Ammo;
import tile.Tile;

public class EntityList {
    
    public Player player = new Player();

    LinkedList<Entity> entities = new LinkedList<>();
    LinkedList<Entity> toRemove = new LinkedList<>();
    LinkedList<Entity> toAdd = new LinkedList<>();

    public int round = 0;
    int totalZombies;
    int numZombies;
    int zombiesLeftToSpawn;
    static final int spawnRadius = 5;
    static final int spawnZombieDelay = 5 * Game.FPS;
    int lastSpawnZombieTick;

    public void initialize() {
        Tile playerSpawn = Tile.getRandomSpawnableTile();
        player.spawn(playerSpawn.row * Game.tileSize, playerSpawn.col * Game.tileSize);
        newRound();
    }

    public void add(Entity entity) {
        toAdd.add(entity);
    }

    public void remove(Entity entity) {
        toRemove.add(entity);
    }

    public void newRound() {
        round++;
        totalZombies = round * 2;
        numZombies = 0;
        zombiesLeftToSpawn = totalZombies;
        attemptAmmoSpawn();
    }

    public void decrementZombieCount() {
        numZombies--;
        if (numZombies <= 0 && zombiesLeftToSpawn <= 0) {
            newRound();
        }
    }

    private void attemptAmmoSpawn() {
        Tile spawnTile = Tile.getRandomSpawnableTile(player.getTile(), spawnRadius);
        if (spawnTile != null) {
            new EntityItem(new ItemStack(Ammo.handgunAmmo, 16 + Game.getInstance().random.nextInt(48))).spawn(spawnTile.col * Game.tileSize + 16, spawnTile.row * Game.tileSize + 16);
        }
    }

    private void attemptZombieSpawn() {
        if (zombiesLeftToSpawn > 0 && Game.getInstance().tick - lastSpawnZombieTick > spawnZombieDelay) {
            Tile spawnTile = Tile.getRandomSpawnableTile(player.getTile(), spawnRadius);
            if (spawnTile != null) {
                lastSpawnZombieTick = Game.getInstance().tick;
                new Zombie().spawn(spawnTile.col * Game.tileSize + 16, spawnTile.row * Game.tileSize + 16);
                numZombies++;
                zombiesLeftToSpawn--;
            }
        }
    }

    private void updateWorld() {
        attemptZombieSpawn();
    }

    public void update() {
        updateWorld();
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

}
