package entity;

import game.GamePanel;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Zombie extends Entity {

    private GamePanel gp;

    public Zombie(GamePanel gp, int spawnX, int spawnY) {
        this.gp = gp;
        this.x = spawnX;
        this.y = spawnY;
        this.direction = "south";
        getPlayerImages();
    }

    public void getPlayerImages() {
        //need to change this to not load new images on every spawn
        try {
            standingNorth = ImageIO.read(getClass().getResourceAsStream("/res/zombie/standing-n.png"));
            walkingNorth1 = ImageIO.read(getClass().getResourceAsStream("/res/zombie/walk-n1.png"));
            walkingNorth2 = ImageIO.read(getClass().getResourceAsStream("/res/zombie/walk-n2.png"));
            standingSouth = ImageIO.read(getClass().getResourceAsStream("/res/zombie/standing-s.png"));
            walkingSouth1 = ImageIO.read(getClass().getResourceAsStream("/res/zombie/walk-s1.png"));
            walkingSouth2 = ImageIO.read(getClass().getResourceAsStream("/res/zombie/walk-s2.png"));
            standingEast = ImageIO.read(getClass().getResourceAsStream("/res/zombie/standing-e.png"));
            walkingEast1 = ImageIO.read(getClass().getResourceAsStream("/res/zombie/walk-e1.png"));
            walkingEast2 = ImageIO.read(getClass().getResourceAsStream("/res/zombie/walk-e2.png"));
            standingWest = ImageIO.read(getClass().getResourceAsStream("/res/zombie/standing-w.png"));
            walkingWest1 = ImageIO.read(getClass().getResourceAsStream("/res/zombie/walk-w1.png"));
            walkingWest2 = ImageIO.read(getClass().getResourceAsStream("/res/zombie/walk-w2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (true) {
            moving = true;

            animationCounter++;
            if (animationCounter > 15) {
                animationStep = !animationStep;
                animationCounter = 0;
            }

        }else {
            moving = false;
        }

    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch (direction) {
            case "north":
                if (moving) {
                    image = animationStep ? walkingNorth1  : walkingNorth2;
                }else {
                    image = standingNorth;
                }
                break;
            case "south":
                if(moving) {
                    image = animationStep ? walkingSouth1 : walkingSouth2;
                }else {
                    image = standingSouth;
                }
                break;
            case "east":
                if (moving) {
                    image = animationStep ? walkingEast1 : walkingEast2;
                }else {
                    image = standingEast;
                }
                break;
            case "west":
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
