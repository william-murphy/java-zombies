package game;

import entity.Entity;
import tile.TileController;
import tile.Tile;
import common.*;

import java.lang.Math;

// TODO: move this function to Entity class

public class CollisionChecker {

    public static double angleBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
    }

    public static Direction dirFromAngle(double angle) {
        if (angle > 45 && angle <= 135) {
            return Direction.SOUTH; // north and south are flipped because +y for Rectangle moves it downwards on the coordinate plane
        } else if (angle > 225 && angle <= 315) {
            return Direction.NORTH;
        } else if ((angle > 315 && angle <= 360) || (angle >= 0 && angle <= 45)) {
            return Direction.EAST;
        } else if (angle > 135 && angle <= 225) {
            return Direction.WEST;
        } else {
            return null;
        }
    }

    public static void moveZombie(Entity entity, Tile nextTile) {
        switch (entity.getDirection(nextTile)) {
            case NORTH:
                if (nextTile.fitsHorizontally(entity)) {
                    entity.move(Direction.NORTH);
                }
            break;
            case SOUTH:
                if (nextTile.fitsHorizontally(entity)) {
                    entity.move(Direction.SOUTH);
                }
            break;
            case EAST:
                if (nextTile.fitsVertically(entity)) {
                    entity.move(Direction.EAST);
                }
            break;
            case WEST:
                if (nextTile.fitsVertically(entity)) {
                    entity.move(Direction.WEST);
                }
            break;
        }
    }

    public static int getNextXDistance(Entity entity) {
        Tile[] tiles;
        switch (entity.direction) {
            case EAST:
                tiles = new Tile[]{ 
                    TileController.map[entity.getMaxX(entity.speed) / Game.tileSize][entity.getMinY(0) / Game.tileSize],
                    TileController.map[entity.getMaxX(entity.speed) / Game.tileSize][entity.getMaxY(0) / Game.tileSize]  
                };
                if ((tiles[0].collision && tiles[0].hitbox.getMinX() < entity.hitbox.getMaxX() + entity.speed) || (tiles[1].collision && tiles[1].hitbox.getMinX() < entity.hitbox.getMaxX() + entity.speed )) {
                    return tiles[0].getMinX(0) - entity.getMaxX(0) - 1;
                } else {
                    return entity.speed;
                }
            case WEST:
                tiles = new Tile[]{ 
                    TileController.map[entity.getMinX(entity.speed) / Game.tileSize][entity.getMinY(0) / Game.tileSize],
                    TileController.map[entity.getMinX(entity.speed) / Game.tileSize][entity.getMaxY(0) / Game.tileSize]  
                };
                if ((tiles[0].collision && tiles[0].hitbox.getMaxX() > entity.hitbox.getMinX() - entity.speed) || (tiles[1].collision && tiles[1].hitbox.getMaxX() > entity.hitbox.getMinX() - entity.speed )) {
                    return entity.getMinX(0) - tiles[0].getMaxX(0);
                } else {
                    return entity.speed;
                }
            default:
                return entity.speed;
        }
    }

    public static int getNextYDistance(Entity entity) {
        Tile[] tiles;
        switch (entity.direction) {
            case NORTH:
                tiles = new Tile[]{ 
                    TileController.map[entity.getMaxX(0) / Game.tileSize][entity.getMinY(entity.speed) / Game.tileSize],
                    TileController.map[entity.getMinX(0) / Game.tileSize][entity.getMinY(entity.speed) / Game.tileSize]  
                };
                if ((tiles[0].collision && tiles[0].hitbox.getMaxY() > (entity.hitbox.getMinY() - entity.speed)) || (tiles[1].collision && tiles[1].hitbox.getMaxY() > (entity.hitbox.getMinY() - entity.speed))) {
                    return entity.getMinY(0) - tiles[0].getMaxY(0);
                } else {
                    return entity.speed;
                }
            case SOUTH:
                tiles = new Tile[]{ 
                    TileController.map[entity.getMaxX(0) / Game.tileSize][entity.getMaxY(entity.speed) / Game.tileSize],
                    TileController.map[entity.getMinX(0) / Game.tileSize][entity.getMaxY(entity.speed) / Game.tileSize]  
                };
                if ((tiles[0].collision && tiles[0].hitbox.getMinY() < (entity.hitbox.getMaxY() + entity.speed)) || (tiles[1].collision && tiles[1].hitbox.getMinY() < (entity.hitbox.getMaxY() + entity.speed))) {
                    return tiles[0].getMinY(0) - entity.getMaxY(0) - 1;
                } else {
                    return entity.speed;
                }
            default:
                return entity.speed;
        }
    }

}
