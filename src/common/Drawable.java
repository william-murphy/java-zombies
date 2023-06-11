package common;

public interface Drawable {

    // the world X and world Y of the Drawable object
    public abstract int getWorldX();
    public abstract int getWorldY();

    // the number of pixels to the left of the world X to start drawing from
    public abstract int getHorizontalOffset();
    // the number of pixels above the worldY to start drawing from
    public abstract int getVerticalOffset();
    // NOTE: a Drawable object's world x and world y are that of the Rectangle that is its hitbox, 
    // which means its the top left corner of this Rectangle we get, so the vertical and horizontal offset
    // should be determined in relation to the top left corner of the entity's hitbox

}
