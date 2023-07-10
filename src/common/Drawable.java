package common;

import java.awt.Graphics2D;

public interface Drawable {
    public abstract void draw(Graphics2D g2d);
    // // the world X and world Y of the Drawable object
    // public abstract int getHitbox().x;
    // public abstract int getHitbox().y;

    // // the number of pixels to the left of the world X to start drawing from
    // public abstract int getHorizontalOffset();
    // // the number of pixels above the worldY to start drawing from
    // public abstract int getVerticalOffset();
    // // NOTE: a Drawable object's world x and world y are that of the Rectangle that is its hitbox, 
    // // which means its the top left corner of this Rectangle we get, so the vertical and horizontal offset
    // // should be determined in relation to the top left corner of the entity's hitbox

}
