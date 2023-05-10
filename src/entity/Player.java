package entity;

import game.Game;
import tile.TileController;
import game.KeyHandler.Direction;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Player extends Entity {

    Game game;
    static BufferedImage standingNorth, walkingNorth1, walkingNorth2, standingSouth, walkingSouth1, walkingSouth2, standingEast, walkingEast1, walkingEast2, standingWest, walkingWest1, walkingWest2;
    public int spawnZombieRadius;

    public Player(Game game) {
        this.game = game;
        this.hitbox = new Rectangle(TileController.playerSpawnX + (Game.tileSize / 4), TileController.playerSpawnY + (Game.tileSize / 2), Game.tileSize / 2, Game.tileSize / 2);
        setDefaultValues();
    }

    private void setDefaultValues() {
        x = TileController.playerSpawnX;
        y = TileController.playerSpawnY;
        speed = 4;
        direction = Direction.SOUTH;
        moving = false;
        spawnZombieRadius = Game.tileSize * 5;
        maxHealth = 20;
        health = maxHealth;
    }

    public void update() {

        updatePosition();

        game.camera.update(x, y);

    }

    public void draw(Graphics2D g2d) {

        BufferedImage image = null;

        switch (direction) {
            
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

        g2d.drawImage(image, screenX, screenY, Game.tileSize, Game.tileSize, null);
    }

    public static void loadImages() {
        try {
            standingNorth = ImageIO.read(Player.class.getResourceAsStream("/res/player/standing-n.png"));
            walkingNorth1 = ImageIO.read(Player.class.getResourceAsStream("/res/player/walk-n1.png"));
            walkingNorth2 = ImageIO.read(Player.class.getResourceAsStream("/res/player/walk-n2.png"));
            standingSouth = ImageIO.read(Player.class.getResourceAsStream("/res/player/standing-s.png"));
            walkingSouth1 = ImageIO.read(Player.class.getResourceAsStream("/res/player/walk-s1.png"));
            walkingSouth2 = ImageIO.read(Player.class.getResourceAsStream("/res/player/walk-s2.png"));
            standingEast = ImageIO.read(Player.class.getResourceAsStream("/res/player/standing-e.png"));
            walkingEast1 = ImageIO.read(Player.class.getResourceAsStream("/res/player/walk-e1.png"));
            walkingEast2 = ImageIO.read(Player.class.getResourceAsStream("/res/player/walk-e2.png"));
            standingWest = ImageIO.read(Player.class.getResourceAsStream("/res/player/standing-w.png"));
            walkingWest1 = ImageIO.read(Player.class.getResourceAsStream("/res/player/walk-w1.png"));
            walkingWest2 = ImageIO.read(Player.class.getResourceAsStream("/res/player/walk-w2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}