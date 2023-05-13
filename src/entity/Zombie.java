package entity;

import game.Game;
import game.KeyHandler.Direction;
import tile.*;
import ai.Pathfinder;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

public class Zombie extends Entity {

    static BufferedImage standingNorth, walkingNorth1, walkingNorth2, standingSouth, walkingSouth1, walkingSouth2, standingEast, walkingEast1, walkingEast2, standingWest, walkingWest1, walkingWest2;

    Game game;
    Pathfinder pathFinder;

    public Zombie(Game game, int spawnX, int spawnY) {
        this.game = game;
        this.x = spawnX;
        this.y = spawnY;
        this.hitbox = new Rectangle(this.x + (Game.tileSize / 4), this.y + (Game.tileSize / 2), Game.tileSize / 2, Game.tileSize / 2);
        this.pathFinder = new Pathfinder(this);
        setDefaultValues();
    }

    private void setDefaultValues() {
        direction = Direction.SOUTH;
        maxHealth = 10;
        speed = 3;
        health = maxHealth;
        onPath = true;
    }

    public void searchPath(Tile goal) {
        Tile start = this.getTile();
        if (start == goal) {
            return;
        }
        pathFinder.setNodes(start.col, start.row, goal.col, goal.row);

        if (pathFinder.search()) {
            Tile next = TileController.getTile(pathFinder.pathList.get(0).col, pathFinder.pathList.get(0).row);
            Direction nextDirection = start.getDirection(next);
            move(nextDirection);
        }
    }

    public void update() {

        if (onPath) {
            searchPath(game.entityController.player.getTile());
        }

        updatePosition();

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

        // draw path for debugging
        g2d.setColor(new Color(255, 0, 0, 70));
        for (int i=0; i < pathFinder.pathList.size(); i++) {
            int screenX = (pathFinder.pathList.get(i).col * Game.tileSize) - game.entityController.player.x + game.entityController.player.screenX;
            int screenY = (pathFinder.pathList.get(i).row * Game.tileSize) - game.entityController.player.y + game.entityController.player.screenY;
            g2d.fillRect(screenX, screenY, Game.tileSize, Game.tileSize);
        }
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
