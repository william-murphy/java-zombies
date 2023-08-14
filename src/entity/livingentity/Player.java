package entity.livingentity;

import game.Game;
import item.ItemStack;
import item.weapon.Weapon;
import common.*;
import entity.EntityItem;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Player extends LivingEntity {
    
    static { loadImages(); }

    static BufferedImage north, movingNorth1, movingNorth2, south, movingSouth1, movingSouth2, east, movingEast1, movingEast2, west, movingWest1, movingWest2;
    static BufferedImage equippedNorth, movingEquippedNorth1, movingEquippedNorth2, equippedSouth, movingEquippedSouth1, movingEquippedSouth2, equippedEast, movingEquippedEast1, movingEquippedEast2, equippedWest, movingEquippedWest1, movingEquippedWest2;

    public Inventory inventory = new Inventory(this, 8);
    public Hand hand = new Hand(this);

    public Player() {
        this.hitbox = new Hitbox(0, 0, Game.tileSize / 2, Game.tileSize / 2);
        inventory.add(new ItemStack(Weapon.tac40));
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
    public void receiveDamage(int amount) {
        this.health -= amount;
        if (this.health <= 0) {
            this.dead = true;
            this.despawn();
            Game.getInstance().gameOver();
        }
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
    }

    @Override
    public void update() {
        regenerateHealth();
        updatePosition();
        updateAnimation();
        checkCollision();
        updateTile();
        if (inventory.isHoldingItem()) {
            hand.update();
        }
        Game.getInstance().camera.update(hitbox.x, hitbox.y);
    }

    @Override
    public void draw(Graphics2D g2d) {

        BufferedImage image = null;

        switch (direction) {
            
            case NORTH:
                if (moving) {
                    if (inventory.isHoldingItem()) {
                        image = animationStep ? movingEquippedNorth1 : movingEquippedNorth2;
                    } else {
                        image = animationStep ? movingNorth1 : movingNorth2;
                    }
                }else {
                    image = inventory.isHoldingItem() ? equippedNorth : north;
                }
                break;
            case SOUTH:
                if(moving) {
                    if (inventory.isHoldingItem()) {
                        image = animationStep ? movingEquippedSouth1 : movingEquippedSouth2;
                    } else {
                        image = animationStep ? movingSouth1 : movingSouth2;
                    }
                }else {
                    image = inventory.isHoldingItem() ? equippedSouth : south;
                }
                break;
            case EAST:
                if (moving) {
                    if (inventory.isHoldingItem()) {
                        image = animationStep ? movingEquippedEast1 : movingEquippedEast2;
                    } else {
                        image = animationStep ? movingEast1 : movingEast2;
                    }
                }else {
                    image = inventory.isHoldingItem() ? equippedEast : east;
                }
                break;
            case WEST:
                if (moving) {
                    if (inventory.isHoldingItem()) {
                        image = animationStep ? movingEquippedWest1 : movingEquippedWest2;
                    } else {
                        image = animationStep ? movingWest1 : movingWest2;
                    }
                }else {
                    image = inventory.isHoldingItem() ? equippedWest : west;
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

    private static void loadImages() {
        try {
            north = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/north.png"));
            movingNorth1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingNorth1.png"));
            movingNorth2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingNorth2.png"));
            south = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/south.png"));
            movingSouth1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingSouth1.png"));
            movingSouth2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingSouth2.png"));
            east = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/east.png"));
            movingEast1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingEast1.png"));
            movingEast2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingEast2.png"));
            west = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/west.png"));
            movingWest1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingWest1.png"));
            movingWest2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingWest2.png"));
            equippedNorth = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/equippedNorth.png"));
            movingEquippedNorth1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingEquippedNorth1.png"));
            movingEquippedNorth2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingEquippedNorth2.png"));
            equippedSouth = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/equippedSouth.png"));
            movingEquippedSouth1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingEquippedSouth1.png"));
            movingEquippedSouth2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingEquippedSouth2.png"));
            equippedEast = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/equippedEast.png"));
            movingEquippedEast1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingEquippedEast1.png"));
            movingEquippedEast2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingEquippedEast2.png"));
            equippedWest = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/equippedWest.png"));
            movingEquippedWest1 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingEquippedWest1.png"));
            movingEquippedWest2 = ImageIO.read(Player.class.getResourceAsStream("/res/entity/player/movingEquippedWest2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}