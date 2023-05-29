package common;

import java.awt.Rectangle;

public interface Collidable {

    public boolean getCollision();

    public Rectangle getHitbox();

    public default boolean collides(Collidable other) {
        return this.getCollision() && other.getCollision() && this.getHitbox().intersects(other.getHitbox());
    }

}
