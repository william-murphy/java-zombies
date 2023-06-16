package common;

public interface Collidable {

    public abstract boolean hasCollision();

    public abstract int getMaxX(int padding);

    public abstract int getMinX(int padding);

    public abstract int getMaxY(int padding);

    public abstract int getMinY(int padding);

    // TODO: make an interface called Movable for things that are movable ... currently only Entity

}
