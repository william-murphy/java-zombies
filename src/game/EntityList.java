package game;

import java.awt.Graphics2D;
import java.util.LinkedList;

import entity.livingentity.*;
import entity.*;
import item.ItemStack;
import item.ammo.Ammo;
import item.weapon.Weapon;
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
    static final int spawnZombieRadius = 5;
    static final int spawnZombieDelay = 5 * Game.FPS;
    int lastSpawnZombieTick;

    public void initialize() {
        player.spawn(Player.playerSpawnX, Player.playerSpawnY);
        // temp
        new ItemStack(Weapon.tac40).spawnEntityItem(Player.playerSpawnX - 200, Player.playerSpawnY);
        new ItemStack(Ammo.handgunAmmo, 32).spawnEntityItem(Player.playerSpawnX + 100, Player.playerSpawnY);
        new ItemStack(Ammo.handgunAmmo, 33).spawnEntityItem(Player.playerSpawnX + 100, Player.playerSpawnY - 100);
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
    }

    public void decrementZombieCount() {
        numZombies--;
        if (numZombies <= 0 && zombiesLeftToSpawn <= 0) {
            newRound();
        }
    }

    private void attemptZombieSpawn() {
        if (zombiesLeftToSpawn > 0 && Game.getInstance().tick - lastSpawnZombieTick > spawnZombieDelay) {
            Tile playerTile = this.player.getTile();
            int spawnCol = playerTile.col + (Game.getInstance().random.nextInt(2 * spawnZombieRadius + 1) - spawnZombieRadius);
            int spawnRow = playerTile.row + (Game.getInstance().random.nextInt(2 * spawnZombieRadius + 1) - spawnZombieRadius);
            if (!Tile.map[spawnCol][spawnRow].collision) {
                lastSpawnZombieTick = Game.getInstance().tick;
                new Zombie().spawn(spawnCol * Game.tileSize + 16, spawnRow * Game.tileSize + 16);
                numZombies++;
                zombiesLeftToSpawn--;
            }
        }
    }

    private void updateWorld() {
        attemptZombieSpawn();
        // TODO - implement random ammo spawns
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
