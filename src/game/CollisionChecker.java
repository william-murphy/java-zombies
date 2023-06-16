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
