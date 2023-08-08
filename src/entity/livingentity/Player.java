package entity.livingentity;

import game.Game;
import common.*;
import entity.EntityItem;
import tile.Tile;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Player extends LivingEntity {
    
    public static final int playerSpawnX = (Tile.worldWidth / 2) - (Game.tileSize / 2);
    public static final int playerSpawnY = (Tile.worldHeight / 2) - (Game.tileSize / 2);
    public static final int spawnZombieRadius = 5;
    public static final int spawnDelay = 5 * Game.FPS;

    // player specific fields
    static BufferedImage standingNorth, walkingNorth1, walkingNorth2, standingSouth, walkingSouth1, walkingSouth2, standingEast, walkingEast1, walkingEast2, standingWest, walkingWest1, walkingWest2;
    static BufferedImage standingNorthEquipped, walkingNorthEquipped1, walkingNorthEquipped2, standingSouthEquipped, walkingSouthEquipped1, walkingSouthEquipped2, standingEastEquipped, walkingEastEquipped1, walkingEastEquipped2, standingWestEquipped, walkingWestEquipped1, walkingWestEquipped2;

    public Inventory inventory = new Inventory(this, 8);
    public Hand hand = new Hand(this);

    public Player() {
        this.hitbox = new Hitbox(0, 0, Game.tileSize / 2, Game.tileSize / 2);
        setDefaultValues();
    }

    private void setDefaultValues() {
        speed = 4;
        direction = Direction.SOUTH;
        moving = false;
        maxHealth = 20;
        health = maxHealth;
        strength = 5;
    }

    public void attemptZombieSpawn() {
        if (Zombie.numZombies < Zombie.getMaxZombies() && Game.getInstance().tick % Player.spawnDelay == 0) {
            Tile playerTile = this.getTile();
            int spawnCol = playerTile.col + (Game.getInstance().random.nextInt(2 * Player.spawnZombieRadius + 1) - Player.spawnZombieRadius);
            int spawnRow = playerTile.row + (Game.getInstance().random.nextInt(2 * Player.spawnZombieRadius + 1) - Player.spawnZombieRadius);
            if (!Tile.map[spawnCol][spawnRow].collision) {
                new Zombie().spawn(spawnCol * Game.tileSize + 16, spawnRow * Game.tileSize + 16);
                Zombie.numZombies++;
            }
        }
    }

    public void pickupItem(EntityItem item) {
        item.despawn();
        inventory.add(item.getItemStack());
    }

    public void dropItem() {
        inventory.remove();
    }

    public void primaryAction() {
        if (inventory.isHoldingItem()) {
            inventory.getCurrentItemStack().getItem().use(this);
        }
    }

    public void stopPrimaryAction() {
        if (inventory.isHoldingItem()) {
            inventory.getCurrentItemStack().getItem().stopUse();
        }
    }

    public void secondaryAction() {
        if (inventory.isHoldingItem()) {
            inventory.getCurrentItemStack().getItem().secondaryUse(this);
        }
    }

    public void stopSecondaryAction() {
        if (inventory.isHoldingItem()) {
            inventory.getCurrentItemStack().getItem().stopSecondaryUse();
        }
    }

    @Override
    public void damage(LivingEntity entity) {
        entity.receiveDamage(this.strength);
    }

    @Override
    public void spawn(int x, int y) {
        hitbox.setLocation(x, y);
        tile = getTile();
        tile.entities.add(this);
        Game.getInstance().entityList.add(this);
    }

    @Override
    public void despawn() {
        tile.entities.remove(this);
        Game.getInstance().entityList.remove(this);
        Game.getInstance().gameOver();
    }

    @Override
    public void update() {
        updatePosition();
        updateAnimation();
        checkCollision();
        updateTile();
        if (inventory.isHoldingItem()) {
            hand.update();
        }
        attemptZombieSpawn();
        Game.getInstance().camera.update(hitbox.x, hitbox.y);
    }

    @Override
    public void draw(Graphics2D g2d) {

        BufferedImage image = null;

        switch (direction) {
            
            case NORTH:
                if (moving) {
                    if (inventory.isHoldingItem()) {
                        image = animationStep ? walkingNorthEquipped1 : walkingNorthEquipped2;
                    } else {
                        image = animationStep ? walkingNorth1 : walkingNorth2;
                    }
                }else {
                    image = inventory.isHoldingItem() ? standingNorthEquipped : standingNorth;
                }
                break;
            case SOUTH:
                if(moving) {
                    if (inventory.isHoldingItem()) {
                        image = animationStep ? walkingSouthEquipped1 : walkingSouthEquipped2;
                    } else {
                        image = animationStep ? walkingSouth1 : walkingSouth2;
                    }
                }else {
                    image = inventory.isHoldingItem() ? standingSouthEquipped : standingSouth;
                }
                break;
            case EAST:
                if (moving) {
                    if (inventory.isHoldingItem()) {
                        image = animationStep ? walkingEastEquipped1 : walkingEastEquipped2;
                    } else {
                        image = animationStep ? walkingEast1 : walkingEast2;
                    }
                }else {
                    image = inventory.isHoldingItem() ? standingEastEquipped : standingEast;
                }
                break;
            case WEST:
                if (moving) {
                    if (inventory.isHoldingItem()) {
                        image = animationStep ? walkingWestEquipped1 : walkingWestEquipped2;
                    } else {
                        image = animationStep ? walkingWest1 : walkingWest2;
                    }
                }else {
                    image = inventory.isHoldingItem() ? standingWestEquipped : standingWest;
                }
                break;
        }

        // player
        g2d.drawImage(image, Game.getInstance().camera.calculateScreenX(hitbox.x, Game.tileSize / 4), Game.getInstance().camera.calculateScreenY(hitbox.y, Game.tileSize / 2), Game.tileSize, Game.tileSize, null);
        // item
        if (inventory.isHoldingItem()) {
            inventory.getCurrentItemStack().getItem().drawInHand(g2d, this);
        }

        //DEBUG

        if (Game.getInstance().debug) {
            // draw player hitbox
            this.hitbox.draw(g2d);
            this.hand.draw(g2d);

        }
    }

    public static void loadImages() {
        try {
            standingNorth = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/standing-n.png"));
            walkingNorth1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-n1.png"));
            walkingNorth2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-n2.png"));
            standingSouth = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/standing-s.png"));
            walkingSouth1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-s1.png"));
            walkingSouth2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-s2.png"));
            standingEast = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/standing-e.png"));
            walkingEast1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-e1.png"));
            walkingEast2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-e2.png"));
            standingWest = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/standing-w.png"));
            walkingWest1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-w1.png"));
            walkingWest2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-w2.png"));
            standingNorthEquipped = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/standing-n-hand-out.png"));
            walkingNorthEquipped1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-n-hand-out-1.png"));
            walkingNorthEquipped2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-n-hand-out-2.png"));
            standingSouthEquipped = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/standing-s-hand-out.png"));
            walkingSouthEquipped1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-s-hand-out-1.png"));
            walkingSouthEquipped2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-s-hand-out-2.png"));
            standingEastEquipped = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/standing-e-hand-out.png"));
            walkingEastEquipped1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-e-hand-out-1.png"));
            walkingEastEquipped2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-e-hand-out-2.png"));
            standingWestEquipped = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/standing-w-hand-out.png"));
            walkingWestEquipped1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-w-hand-out-1.png"));
            walkingWestEquipped2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/walk-w-hand-out-2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}