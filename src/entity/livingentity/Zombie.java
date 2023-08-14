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

    static { loadImages(); }

    static BufferedImage north, movingNorth1, movingNorth2, south, movingSouth1, movingSouth2, east, movingEast1, movingEast2, west, movingWest1, movingWest2;
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

    private void checkPlayerCollision() {
        if (this.hitbox.intersects(Game.getInstance().entityList.player.hitbox)) {
            damage(Game.getInstance().entityList.player, strength);
        }
    }

    @Override
    public void receiveDamage(int amount) {
        this.health -= amount;
        if (this.health <= 0) {
            this.dead = true;
            this.despawn();
            Game.getInstance().entityList.decrementZombieCount();
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
        updatePosition();
        checkCollision();
        checkPlayerCollision();
        updateTile();
        updateAnimation();
    }

    @Override
    public void draw(Graphics2D g2d) {

        BufferedImage image = null;

        switch(direction) {
            case NORTH:
                if (moving) {
                    image = animationStep ? movingNorth1  : movingNorth2;
                }else {
                    image = north;
                }
                break;
            case SOUTH:
                if(moving) {
                    image = animationStep ? movingSouth1 : movingSouth2;
                }else {
                    image = south;
                }
                break;
            case EAST:
                if (moving) {
                    image = animationStep ? movingEast1 : movingEast2;
                }else {
                    image = east;
                }
                break;
            case WEST:
                if (moving) {
                    image = animationStep ? movingWest1 : movingWest2;
                }else {
                    image = west;
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

    private static void loadImages() {
        try {
            north = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/north.png"));
            movingNorth1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/movingNorth1.png"));
            movingNorth2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/movingNorth2.png"));
            south = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/south.png"));
            movingSouth1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/movingSouth1.png"));
            movingSouth2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/movingSouth2.png"));
            east = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/east.png"));
            movingEast1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/movingEast1.png"));
            movingEast2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/movingEast2.png"));
            west = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/west.png"));
            movingWest1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/movingWest1.png"));
            movingWest2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/entity/zombie/movingWest2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
