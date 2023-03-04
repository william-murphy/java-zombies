package entity;

import game.GamePanel;

import java.awt.Graphics2D;
import java.awt.Color;

public class Zombie extends Entity {

    private GamePanel gp;

    public Zombie(GamePanel gp, int spawnX, int spawnY) {
        this.gp = gp;
        this.x = spawnX;
        this.y = spawnY;
    }

    public void draw(Graphics2D g2d) {
        screenX = gp.camera.calculateScreenX(this.x);
        screenY = gp.camera.calculateScreenY(this.y);
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(screenX, screenY, 50, 50);
    }

}
