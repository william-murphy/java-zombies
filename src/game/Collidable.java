package game;

import java.awt.Rectangle;

public interface Collidable {

    public Rectangle getHitbox();

    public boolean collides(Collidable other);

}
