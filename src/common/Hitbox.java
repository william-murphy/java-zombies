package common;

import java.awt.Rectangle;

public class Hitbox extends Rectangle implements Drawable {
    
    public Hitbox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public int getWorldX() {
        return this.x;
    }

    @Override
    public int getWorldY() {
        return this.y;
    }

    @Override
    public int getHorizontalOffset() {
        return 0;
    }

    @Override
    public int getVerticalOffset() {
        return 0;
    }

}
