package entity;

import game.GamePanel;
import game.KeyHandler;
import game.Camera;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public Camera camera;
    private GamePanel gp;
    private KeyHandler kh;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
        this.camera = new Camera(gp);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = gp.playerSpawnX;
        y = gp.playerSpawnY;
        screenX = camera.calculateScreenX(this.x);
        screenY = camera.calculateScreenY(this.y);
        speed = 4;
        direction = "south";
        moving = false;
    }

    public void getPlayerImage() {
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

    public void move() {

        if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {

            moving = true;

            if (kh.upPressed) {
                direction = "north";
                y -= speed;
            }
            if (kh.downPressed) {
                direction = "south";
                y += speed;
            }
            if (kh.leftPressed) {
                direction = "west";
                x -= speed;
            }
            if (kh.rightPressed) {
                direction = "east";
                x += speed;
            }

            animationCounter++;
            if (animationCounter > 15) {
                animationStep = !animationStep;
                animationCounter = 0;
            }

            camera.update(this.x, this.y);

        } else {
            moving = false;
        }
    }

    public void draw(Graphics2D g2d) {

        BufferedImage image = null;

        switch (direction) {
            case "north":
                image = !moving ? standingNorth : animationStep ? walkingNorth1 : walkingNorth2;
                break;
            case "south":
                image = !moving ? standingSouth : animationStep ? walkingSouth1 : walkingSouth2;
                break;
            case "east":
                image = !moving ? standingEast : animationStep ? walkingEast1 : walkingEast2;
                break;
            case "west":
                image = !moving ? standingWest : animationStep ? walkingWest1 : walkingWest2;
                break;
        }

        g2d.drawImage(image, camera.calculateScreenX(this.x), camera.calculateScreenY(this.y), gp.tileSize, gp.tileSize, null);
    }

}