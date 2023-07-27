package entity.creature;

import game.Game;
import common.*;
import item.Item;
import tile.Tile;
import entity.EntityItem;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Point;

public class Player extends Creature {
    
    public static final int playerSpawnX = (Tile.worldWidth / 2) - (Game.tileSize / 2);
    public static final int playerSpawnY = (Tile.worldHeight / 2) - (Game.tileSize / 2);
    public static final int spawnZombieRadius = 5;
    public static final int spawnDelay = 5 * Game.FPS;

    // player specific fields
    static BufferedImage standingNorth, walkingNorth1, walkingNorth2, standingSouth, walkingSouth1, walkingSouth2, standingEast, walkingEast1, walkingEast2, standingWest, walkingWest1, walkingWest2;
    static BufferedImage standingNorthEquipped, walkingNorthEquipped1, walkingNorthEquipped2, standingSouthEquipped, walkingSouthEquipped1, walkingSouthEquipped2, standingEastEquipped, walkingEastEquipped1, walkingEastEquipped2, standingWestEquipped, walkingWestEquipped1, walkingWestEquipped2;

    public Player(int spawnX, int spawnY) {
        this.hitbox = new Hitbox(spawnX, spawnY, Game.tileSize / 2, Game.tileSize / 2);
        setDefaultValues();
    }

    private void setDefaultValues() {
        speed = 4;
        direction = Direction.SOUTH;
        moving = false;
        maxHealth = 20;
        health = maxHealth;
        inventorySize = 8;
        items = new Item[inventorySize];
    }

    public void pickupItem(EntityItem item) {
        item.despawn();
        items[curItem] = item.getItem();
    }

    public void dropItem() {
        if (isHoldingItem()) {
            // TODO - should x and y be passed to constructor or passed to spawn()?
            items[curItem].createEntityItem(hitbox.x - 32, hitbox.y).spawn();
            items[curItem] = null;
        }
    }

    public void useItem() {
        if (isHoldingItem()) {
            items[curItem].use();
        }
    }

    public void stopUseItem() {
        if (isHoldingItem()) {
            items[curItem].stopUse();
        }
    }

    @Override
    public Point getHand() {
        switch(direction) {
            case NORTH:
                return new Point(hitbox.x + hitbox.width - 6, hitbox.y - 13);
            case SOUTH:
                return new Point(hitbox.x + 11, hitbox.y + hitbox.height - 18);
            case EAST:
                return new Point(hitbox.x + hitbox.width + 2, hitbox.y - 5);
            case WEST:
                return new Point(hitbox.x - 13, hitbox.y - 3);
        }
        return null;
    }

    @Override
    public void spawn() {
        Game.getInstance().entityList.add(this);
    }

    @Override
    public void despawn() {
        Game.getInstance().entityList.remove(this);
    }

    @Override
    public void update() {
        updatePosition();
        Game.getInstance().camera.update(hitbox.x, hitbox.y);
    }

    @Override
    public void draw(Graphics2D g2d) {

        BufferedImage image = null;

        switch (direction) {
            
            case NORTH:
                if (moving) {
                    if (isHoldingItem()) {
                        image = animationStep ? walkingNorthEquipped1 : walkingNorthEquipped2;
                    } else {
                        image = animationStep ? walkingNorth1 : walkingNorth2;
                    }
                }else {
                    image = isHoldingItem() ? standingNorthEquipped : standingNorth;
                }
                break;
            case SOUTH:
                if(moving) {
                    if (isHoldingItem()) {
                        image = animationStep ? walkingSouthEquipped1 : walkingSouthEquipped2;
                    } else {
                        image = animationStep ? walkingSouth1 : walkingSouth2;
                    }
                }else {
                    image = isHoldingItem() ? standingSouthEquipped : standingSouth;
                }
                break;
            case EAST:
                if (moving) {
                    if (isHoldingItem()) {
                        image = animationStep ? walkingEastEquipped1 : walkingEastEquipped2;
                    } else {
                        image = animationStep ? walkingEast1 : walkingEast2;
                    }
                }else {
                    image = isHoldingItem() ? standingEastEquipped : standingEast;
                }
                break;
            case WEST:
                if (moving) {
                    if (isHoldingItem()) {
                        image = animationStep ? walkingWestEquipped1 : walkingWestEquipped2;
                    } else {
                        image = animationStep ? walkingWest1 : walkingWest2;
                    }
                }else {
                    image = isHoldingItem() ? standingWestEquipped : standingWest;
                }
                break;
        }

        // player
        g2d.drawImage(image, Game.getInstance().camera.calculateScreenX(hitbox.x, Game.tileSize / 4), Game.getInstance().camera.calculateScreenY(hitbox.y, Game.tileSize / 2), Game.tileSize, Game.tileSize, null);
        // item
        if (items[curItem] != null) {
            items[curItem].drawInHand(g2d, this);
        }

        //DEBUG

        if (Game.getInstance().debug) {
            // draw player hitbox
            this.hitbox.draw(g2d);
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