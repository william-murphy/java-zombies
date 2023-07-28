package common;

import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Color;

public class Hand extends Point {
    
    public Hand(int x, int y) {
        super(x, y);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawRect(x - 1, y - 1, 3, 3);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(x, y, 1, 1);
    }

}
