package common;

public enum Direction { 
    NORTH, SOUTH, EAST, WEST;
    public static final int size;
    static {
        size = values().length;
    } 
};
