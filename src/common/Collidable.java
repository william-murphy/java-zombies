package common;

public interface Collidable {

    public abstract boolean hasCollision();

    public abstract Hitbox getHitbox();

    public default boolean collides(Collidable other) {
        return this.hasCollision() && other.hasCollision() && this.getHitbox().intersects(other.getHitbox());
    }

    // TODO: make an interface called Movable for things that are movable ... currently only Entity
    // TODO: instead of the above, start inheriting the different templates in this package so like Collidable inherits Movable or something and Drawable too

}
