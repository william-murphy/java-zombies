package common;

public interface Collidable {

    public abstract boolean getCollision();

    public abstract Hitbox getHitbox();

    public abstract int getNorthBound(int padding);

    public abstract int getSouthBound(int padding);

    public abstract int getEastBound(int padding);

    public abstract int getWestBound(int padding);

    // TODO: make an interface called Movable for things that are movable ... currently only Entity

}
