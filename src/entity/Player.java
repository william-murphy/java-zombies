package entity;

import game.KeyHandler;
import game.Camera;
import game.GamePanel;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Player extends Entity {

    public Camera camera;
    private GamePanel gp;
    private KeyHandler kh;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
        this.camera = new Camera(this, this.gp);

        hitbox = new Rectangle(gp.tileSize / 4, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize / 2);

        setDefaultValues();
        getPlayerImages();
    }

    public void setDefaultValues() {
        x = gp.playerSpawnX;
        y = gp.playerSpawnY;
        speed = 4;
        direction = "south";
        moving = false;
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

    public void move() {
        if (kh.upPressed) {
            moving = true;
            direction = "north";
            if (!gp.tc.collides(this)) {
                y -= speed;
            }
        } else if (kh.downPressed) {
            moving = true;
            direction = "south";
            if (!gp.tc.collides(this)) {
                y += speed;
            }
        } else if (kh.leftPressed) {
            moving = true;
            direction = "west";
            if (!gp.tc.collides(this)) {
                x -= speed;
            }
        } else if (kh.rightPressed) {
            moving = true;
            direction = "east";
            if (!gp.tc.collides(this)) {
                x += speed;
            }
        } else {
            moving = false;
        }

        camera.update(x, y);

        animationCounter++;
        if (animationCounter > 15) {
            animationStep = !animationStep;
            animationCounter = 0;
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

        screenX = camera.calculateScreenX(this.x);
        screenY = camera.calculateScreenY(this.y);

        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}