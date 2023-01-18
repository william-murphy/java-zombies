package entity;

import game.GamePanel;
import game.KeyHandler;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler kh;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
        this.setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "south";
    }

    public void getPlayerImage() {
        try {
            System.out.println("hello");
            standingNorth = ImageIO.read(getClass().getResourceAsStream("/res/player/standing-n.png"));
            standingSouth = ImageIO.read(getClass().getResourceAsStream("/res/player/standing-s.png"));
            standingEast = ImageIO.read(getClass().getResourceAsStream("/res/player/standing-e.png"));
            standingWest = ImageIO.read(getClass().getResourceAsStream("/res/player/standing-w.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        if (kh.upPressed == true) {
            direction = "north";
            y -= speed;
        }
        if (kh.downPressed == true) {
            direction = "south";
            y += speed;
        }
        if (kh.leftPressed == true) {
            direction = "west";
            x -= speed;
        }
        if (kh.rightPressed == true) {
            direction = "east";
            x += speed;
        }
    }

    public void draw(Graphics2D g2d) {
        // g2d.setColor(Color.WHITE);
        // g2d.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "north":
                image = standingNorth;
                break;
            case "south":
                image = standingSouth;
                break;
            case "east":
                image = standingEast;
                break;
            case "west":
                image = standingWest;
                break;
        }

        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}