package entity.livingentity;

import game.Game;
import common.*;
import tile.Tile;
import ai.pathfinder.*;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

public class Zombie extends LivingEntity {

    public static int numZombies = 0;

    // zombie specific fields
    static BufferedImage standingNorth, walkingNorth1, walkingNorth2, standingSouth, walkingSouth1, walkingSouth2, standingEast, walkingEast1, walkingEast2, standingWest, walkingWest1, walkingWest2;
    Pathfinder pathFinder;

    public Zombie() {
        this.hitbox = new Hitbox(0, 0, Game.tileSize / 2, Game.tileSize / 2);
        this.pathFinder = new Pathfinder(this, Game.getInstance().entityList.player);
        setDefaultValues();
    }

    private void setDefaultValues() {
        direction = Direction.SOUTH;
        maxHealth = 10;
        speed = 3;
        health = maxHealth;
        strength = 5;
    }

    public static int getMaxZombies() {
        // return Game.getInstance().round * 2;
        return 1;
    }

    @Override
    public void damage(LivingEntity entity) {
        entity.receiveDamage(this.strength);
    }

    @Override
    public void receiveDamage(int amount) {
        this.health -= amount;
        if (this.health <= 0) {
            dead = true;
            despawn();
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
        pathFinder.update();
        if (pathFinder.goalReached) {
            updatePosition();
            checkCollision();
            updateTile();
        }
        updateAnimation();
    }

    @Override
    public void draw(Graphics2D g2d) {

        BufferedImage image = null;

        switch(direction) {
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

        g2d.drawImage(image, Game.getInstance().camera.calculateScreenX(hitbox.x, 0), Game.getInstance().camera.calculateScreenY(hitbox.y, Game.tileSize / 2), Game.tileSize / 2, Game.tileSize, null);

        //DEBUG
        if (Game.getInstance().debug) {
            // draw path
            g2d.setColor(new Color(255, 0, 0, 70));
            for (Node node : pathFinder.pathList) {
                g2d.fillRect(Game.getInstance().camera.calculateScreenX(Tile.map[node.col][node.row].hitbox.x, 0), Game.getInstance().camera.calculateScreenY(Tile.map[node.col][node.row].hitbox.y, 0), Game.tileSize, Game.tileSize);
            }
            // draw zombie hitbox
            hitbox.draw(g2d);
        }
    }

    public static void loadImages() {
        try {
            standingNorth = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/standing-n.png"));
            walkingNorth1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/walk-n1.png"));
            walkingNorth2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/walk-n2.png"));
            standingSouth = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/standing-s.png"));
            walkingSouth1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/walk-s1.png"));
            walkingSouth2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/walk-s2.png"));
            standingEast = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/standing-e.png"));
            walkingEast1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/walk-e1.png"));
            walkingEast2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/walk-e2.png"));
            standingWest = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/standing-w.png"));
            walkingWest1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/walk-w1.png"));
            walkingWest2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/walk-w2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
