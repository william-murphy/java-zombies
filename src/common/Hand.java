package common;

import game.Game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Hand {
    
    public int x, y;

    public Hand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawRect(Game.getInstance().camera.calculateScreenX(x - 1, 0), Game.getInstance().camera.calculateScreenY(y - 1, 0), 3, 3);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(Game.getInstance().camera.calculateScreenX(x, 0), Game.getInstance().camera.calculateScreenY(y, 0), 1, 1);
    }

}
