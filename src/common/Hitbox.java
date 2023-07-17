package common;

import game.Game;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;

public class Hitbox extends Rectangle {
    
    public Hitbox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.drawRect(Game.getInstance().camera.calculateScreenX(x, 0), Game.getInstance().camera.calculateScreenY(y, 0), width, height);
    }

}
