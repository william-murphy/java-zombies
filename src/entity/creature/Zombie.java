package entity.creature;

import game.Game;
import common.*;
import tile.Tile;
import ai.pathfinder.*;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;

public class Zombie extends Creature {

    // zombie specific fields
    static BufferedImage standingNorth, walkingNorth1, walkingNorth2, standingSouth, walkingSouth1, walkingSouth2, standingEast, walkingEast1, walkingEast2, standingWest, walkingWest1, walkingWest2;
    Pathfinder pathFinder;

    public Zombie(int spawnX, int spawnY) {
        this.hitbox = new Hitbox(spawnX, spawnY, Game.tileSize / 2, Game.tileSize / 2);
        this.pathFinder = new Pathfinder(this, Game.getInstance().entityController.player);
        setDefaultValues();
    }

    private void setDefaultValues() {
        direction = Direction.SOUTH;
        maxHealth = 10;
        speed = 3;
        health = maxHealth;
    }

    @Override
    public Point getHand() { // TODO - change this to store a Point rather than creating a new one all the time ... maybe
        int x = this.direction == Direction.EAST ? (hitbox.x + 5) : (hitbox.x + hitbox.width - 5);
        int y = hitbox.y + (hitbox.height / 2);
        return new Point(x, y);
    }

    @Override
    public void update() {

        pathFinder.update();

        updatePosition();
        // TODO - split position update and collision check into two different functions

    }

    // TODO: figure out why excpetion randomly happens in draw function when in debug mode
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
