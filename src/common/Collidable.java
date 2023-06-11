package common;

public interface Collidable {

    public abstract boolean getCollision();

    public abstract Hitbox getHitbox();

    public default boolean collides(Collidable other) {
        return this.getCollision() && other.getCollision() && this.getHitbox().intersects(other.getHitbox());
    }

}
