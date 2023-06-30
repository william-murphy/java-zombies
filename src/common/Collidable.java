package common;

import java.lang.Math;

public interface Collidable {

    public abstract boolean hasCollision();

    public abstract Hitbox getHitbox();

    public default Direction getDirection(Collidable other) {
        double angle = Math.toDegrees(Math.atan2(other.getHitbox().getCenterY() - this.getHitbox().getCenterY(), other.getHitbox().getCenterX() - this.getHitbox().getCenterX()));
        if (angle < -45 && angle >= -135) {
            return Direction.NORTH;
        } else if (angle < 135 && angle >= 45) {
            return Direction.SOUTH;
        } else if ((angle < 45 && angle >= 0) || (angle <= 0 && angle >= -45)) {
            return Direction.EAST;
        } else if ((angle < -135 && angle >= -180) || (angle <= 180 && angle >= 135)) {
            return Direction.WEST;
        } else {
            System.out.println(angle);
            return null;
        }
    }

    public default boolean collides(Collidable other) {
        return this.hasCollision() && other.hasCollision() && this.getHitbox().intersects(other.getHitbox());
    }

}
