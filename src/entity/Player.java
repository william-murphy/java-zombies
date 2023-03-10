package entity;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import game.GamePanel;

import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Player extends Entity {

    private GamePanel gp;
    public int spawnZombieRadius;

    public Player(GamePanel gp) {
        this.gp = gp;
        setDefaultValues();
        this.hitbox = new Rectangle(gp.playerSpawnX + (gp.tileSize / 4), gp.playerSpawnY + (gp.tileSize / 2), gp.tileSize / 2, gp.tileSize / 2);
        getPlayerImages();
    }

    public void setDefaultValues() {
        x = gp.playerSpawnX;
        y = gp.playerSpawnY;
        speed = 4;
        direction = "south";
        moving = false;
        spawnZombieRadius = gp.tileSize * 5;
    }

    public void getPlayerImages() {
        try {
            standingNorth = ImageIO.read(getClass().getResourceAsStream("/res/player/standing-n.png"));
            walkingNorth1 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-n1.png"));
            walkingNorth2 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-n2.png"));
            standingSouth = ImageIO.read(getClass().getResourceAsStream("/res/player/standing-s.png"));
            walkingSouth1 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-s1.png"));
            walkingSouth2 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-s2.png"));
            standingEast = ImageIO.read(getClass().getResourceAsStream("/res/player/standing-e.png"));
            walkingEast1 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-e1.png"));
            walkingEast2 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-e2.png"));
            standingWest = ImageIO.read(getClass().getResourceAsStream("/res/player/standing-w.png"));
            walkingWest1 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-w1.png"));
            walkingWest2 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-w2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (gp.keyHandler.movement) {
            moving = true;

            if (gp.keyHandler.upPressed) {
                moving = true;
                direction = "north";
                hitbox.translate(0, -speed);
                if (!this.gp.collisionChecker.checkTileCollision(this)) {
                    y -= speed;
                }else {
                    hitbox.translate(0, speed);
                }
            } else if (gp.keyHandler.downPressed) {
                moving = true;
                direction = "south";
                hitbox.translate(0, speed);
                if (!this.gp.collisionChecker.checkTileCollision(this)) {
                    y += speed;
                }else {
                    hitbox.translate(0, -speed);
                }
            } else if (gp.keyHandler.leftPressed) {
                moving = true;
                direction = "west";
                hitbox.translate(-speed, 0);
                if (!this.gp.collisionChecker.checkTileCollision(this)) {
                    x -= speed;
                }else {
                    hitbox.translate(speed, 0);
                }
            } else if (gp.keyHandler.rightPressed) {
                moving = true;
                direction = "east";
                hitbox.translate(speed, 0);
                if (!this.gp.collisionChecker.checkTileCollision(this)) {
                    x += speed;
                }else {
                    hitbox.translate(-speed, 0);
                }
            }

            gp.camera.update(x, y);

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

        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}