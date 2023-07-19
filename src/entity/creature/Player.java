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
    int inventorySize = 8;
    int currentInventorySize = 0;
    Item[] items = new Item[inventorySize];
    int curItem = 0;

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
    }

    public void pickupItem(EntityItem item) {
        Game.getInstance().entityController.remove(item);
        items[curItem] = item.getItem();
    }

    public void dropItem() {
        if (items[curItem] != null) {
            Game.getInstance().entityController.add(items[curItem].createEntityItem(hitbox.x - 32, hitbox.y));
            items[curItem] = null;
        }
    }

    public void useItem() {
        if (items[curItem] != null) {
            items[curItem].use();
        }
    }

    public void stopUseItem() {
        if (items[curItem] != null) {
            items[curItem].stopUse();
        }
    }

    public void update() {
        updatePosition();
        Game.getInstance().camera.update(hitbox.x, hitbox.y);
    }

    @Override
    public Point getHand() {
        int x = this.direction == Direction.EAST ? (hitbox.x + hitbox.width - 10) : (hitbox.x + 5);
        int y = hitbox.y + (hitbox.height / 2) - 5;
        return new Point(x, y);
    }

    @Override
    public void draw(Graphics2D g2d) {

        BufferedImage image = null;

        switch (direction) {
            
            case NORTH:
                if (moving) {
                    image = animationStep ? walkingNorth1  : walkingNorth2;
                }else {
                    image = standingNorth;
                }
                break;
            case SOUTH:
                if(moving) {
                    image = animationStep ? walkingSouth1 : walkingSouth2;
                }else {
                    image = standingSouth;
                }
                break;
            case EAST:
                if (moving) {
                    image = animationStep ? walkingEast1 : walkingEast2;
                }else {
                    image = standingEast;
                }
                break;
            case WEST:
                if (moving) {
                    image = animationStep ? walkingWest1 : walkingWest2;
                }else {
                    image = standingWest;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}