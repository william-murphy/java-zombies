package entity;

import game.GamePanel;
import game.KeyHandler.Direction;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Zombie extends Entity {

    private GamePanel gp;
    public static BufferedImage standingNorth, walkingNorth1, walkingNorth2, standingSouth, walkingSouth1, walkingSouth2, standingEast, walkingEast1, walkingEast2, standingWest, walkingWest1, walkingWest2;

    public Zombie(GamePanel gp, int spawnX, int spawnY) {
        this.gp = gp;
        this.x = spawnX;
        this.y = spawnY;
        setDefaultValues();
    }

    private void setDefaultValues() {
        direction = Direction.SOUTH;
        maxHealth = 10;
        health = maxHealth;
        moving = true; //temporary
    }

    public static void loadZombieImages() {
        System.out.println("loading zombie images");
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

    public void update() {

        if (moving) {

            switch(direction) {
                case NORTH:
                    break;
                case SOUTH:
                    break;
                case EAST:
                    break;
                case WEST:
                    break;
            }

            animationCounter++;
            if (animationCounter > 15) {
                animationStep = !animationStep;
                animationCounter = 0;
            }

        }

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

        screenX = gp.camera.calculateScreenX(this.x);
        screenY = gp.camera.calculateScreenY(this.y);

        g2d.drawImage(image, screenX, screenY, gp.tileSize / 2, gp.tileSize, null);
    }

}
