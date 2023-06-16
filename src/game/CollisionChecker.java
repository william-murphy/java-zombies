package game;

import entity.Entity;
import tile.TileController;
import tile.Tile;

// TODO: move this function to Entity class

public class CollisionChecker {

    public static int getNextXDistance(Entity entity) {
        Tile[] tiles;
        switch (entity.direction) {
            case EAST:
                tiles = new Tile[]{ 
                    TileController.map[entity.getEastBound(entity.speed)][entity.getNorthBound(0)],
                    TileController.map[entity.getEastBound(entity.speed)][entity.getSouthBound(0)]  
                };
                if ((tiles[0].collision && tiles[0].hitbox.getMinX() < entity.hitbox.getMaxX() + entity.speed) || (tiles[1].collision && tiles[1].hitbox.getMinX() < entity.hitbox.getMaxX() + entity.speed )) {
                    return (int)(tiles[0].hitbox.getMinX() - entity.hitbox.getMaxX() - 1);
                } else {
                    return entity.speed;
                }
            case WEST:
                tiles = new Tile[]{ 
                    TileController.map[entity.getWestBound(entity.speed)][entity.getNorthBound(0)],
                    TileController.map[entity.getWestBound(entity.speed)][entity.getSouthBound(0)]  
                };
                if ((tiles[0].collision && tiles[0].hitbox.getMaxX() > entity.hitbox.getMinX() - entity.speed) || (tiles[1].collision && tiles[1].hitbox.getMaxX() > entity.hitbox.getMinX() - entity.speed )) {
                    return (int)(entity.hitbox.getMinX() - tiles[0].hitbox.getMaxX());
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
                    TileController.map[entity.getEastBound(0)][entity.getNorthBound(entity.speed)],
                    TileController.map[entity.getWestBound(0)][entity.getNorthBound(entity.speed)]  
                };
                if ((tiles[0].collision && tiles[0].hitbox.getMaxY() > (entity.hitbox.getMinY() - entity.speed)) || (tiles[1].collision && tiles[1].hitbox.getMaxY() > (entity.hitbox.getMinY() - entity.speed))) {
                    return (int)(entity.hitbox.getMinY() - tiles[0].hitbox.getMaxY());
                } else {
                    return entity.speed;
                }
            case SOUTH:
                tiles = new Tile[]{ 
                    TileController.map[entity.getEastBound(0)][entity.getSouthBound(entity.speed)],
                    TileController.map[entity.getWestBound(0)][entity.getSouthBound(entity.speed)]  
                };
                if ((tiles[0].collision && tiles[0].hitbox.getMinY() < (entity.hitbox.getMaxY() + entity.speed)) || (tiles[1].collision && tiles[1].hitbox.getMinY() < (entity.hitbox.getMaxY() + entity.speed))) {
                    return (int)(tiles[0].hitbox.getMinY() - entity.hitbox.getMaxY() - 1);
                } else {
                    return entity.speed;
                }
            default:
                return entity.speed;
        }
    }

}
