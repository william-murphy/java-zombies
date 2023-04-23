package entity;

import game.Game;
import game.KeyHandler.Direction;
import ai.Pathfinder;
import tile.Tile;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Zombie extends Entity {

    static BufferedImage standingNorth, walkingNorth1, walkingNorth2, standingSouth, walkingSouth1, walkingSouth2, standingEast, walkingEast1, walkingEast2, standingWest, walkingWest1, walkingWest2;

    Game game;
    Pathfinder pather = new Pathfinder(this);

    public Zombie(Game game, int spawnX, int spawnY) {
        this.game = game;
        this.x = spawnX;
        this.y = spawnY;
        this.hitbox = new Rectangle(spawnX + (Game.tileSize / 4), spawnY + (Game.tileSize / 2), Game.tileSize / 2, Game.tileSize / 2);
        setDefaultValues();
    }

    private void setDefaultValues() {
        direction = Direction.SOUTH;
        maxHealth = 10;
        health = maxHealth;
        moving = true; //temporary
    }

    public void update() {
        Tile[] path = pather.findPath(game.entityController.player);
        System.out.println("START OF PATH");
        for (Tile t : path) {
            t.print();
        }
        System.out.println("END OF PATH");
    }

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

        screenX = game.camera.calculateScreenX(this.x);
        screenY = game.camera.calculateScreenY(this.y);

        g2d.drawImage(image, screenX, screenY, Game.tileSize / 2, Game.tileSize, null);
    }

    public static void loadImages() {
        try {
            standingNorth = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/standing-n.png"));
            walkingNorth1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/walk-n1.png"));
            walkingNorth2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/walk-n2.png"));
            standingSouth = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/standing-s.png"));
            walkingSouth1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/walk-s1.png"));
            walkingSouth2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/walk-s2.png"));
            standingEast = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/standing-e.png"));
            walkingEast1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/walk-e1.png"));
            walkingEast2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/walk-e2.png"));
            standingWest = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/standing-w.png"));
            walkingWest1 = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/walk-w1.png"));
            walkingWest2 = ImageIO.read(Zombie.class.getResourceAsStream("/res/zombie/walk-w2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
