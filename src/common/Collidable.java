package common;

public interface Collidable {

    public abstract boolean hasCollision();

    public abstract Hitbox getHitbox();

    public abstract Direction getDirection(Collidable other);

    public abstract int getMaxX(int padding);

    public abstract int getMinX(int padding);

    public abstract int getMaxY(int padding);

    public abstract int getMinY(int padding);

    // TODO: make an interface called Movable for things that are movable ... currently only Entity
    // TODO: instead of the above, start inheriting the different templates in this package so like Collidable inherits Movable or something and Drawable too

}
