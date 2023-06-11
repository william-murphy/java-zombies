package entity;

import game.Game;
import common.*;
import tile.Tile;
import tile.TileController;
import ai.*;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

public class Zombie extends Entity implements Drawable {

    static BufferedImage standingNorth, walkingNorth1, walkingNorth2, standingSouth, walkingSouth1, walkingSouth2, standingEast, walkingEast1, walkingEast2, standingWest, walkingWest1, walkingWest2;

    Game game;
    Pathfinder pathFinder;

    public Zombie(Game game, int spawnX, int spawnY) {
        this.game = game;
        // this.hitbox = new Rectangle(this.x, this.y + (Game.tileSize / 2), Game.tileSize / 2, Game.tileSize / 2);
        this.hitbox = new Hitbox(spawnX, spawnY, Game.tileSize / 2, Game.tileSize / 2);
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
            move(start.getDirection(next));
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

        screenX = game.camera.calculateScreenX(this);
        screenY = game.camera.calculateScreenY(this);

        g2d.drawImage(image, screenX, screenY, Game.tileSize / 2, Game.tileSize, null);

        //DEBUG
        if (game.debug) {
            // draw path
            g2d.setColor(new Color(255, 0, 0, 70));
            for (Node node : pathFinder.pathList) {
                g2d.fillRect(game.camera.calculateScreenX(TileController.map[node.col][node.row]), game.camera.calculateScreenY(TileController.map[node.col][node.row]), Game.tileSize, Game.tileSize);
            }
            // draw zombie hitbox
            g2d.setColor(Color.YELLOW);
            g2d.drawRect(game.camera.calculateScreenX(hitbox), game.camera.calculateScreenY(hitbox), hitbox.width, hitbox.height);
        }
    }

    @Override
    public int getWorldX() {
        return this.hitbox.x;
    }

    @Override
    public int getWorldY() {
        return this.hitbox.y;
    }

    @Override
    public int getHorizontalOffset() {
        return 0;
    }

    @Override
    public int getVerticalOffset() {
        return Game.tileSize / 2;
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
