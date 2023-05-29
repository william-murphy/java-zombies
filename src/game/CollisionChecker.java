package game;

import entity.Entity;
import tile.TileController;

public class CollisionChecker {

    //check collision between a tile and entity
    public static boolean checkTileCollision(Entity entity) {
        switch (entity.direction) {
            case NORTH:
                return (TileController.map[entity.hitbox.x / Game.tileSize][(entity.hitbox.y - entity.speed) / Game.tileSize].collision || TileController.map[(entity.hitbox.x + entity.hitbox.width) / Game.tileSize][(entity.hitbox.y - entity.speed) / Game.tileSize].collision);
            case SOUTH:
                return (TileController.map[entity.hitbox.x / Game.tileSize][(entity.hitbox.y + entity.hitbox.height + entity.speed) / Game.tileSize].collision || TileController.map[(entity.hitbox.x + entity.hitbox.width) / Game.tileSize][(entity.hitbox.y + entity.hitbox.height + entity.speed) / Game.tileSize].collision);
            case EAST:
                return (TileController.map[(entity.hitbox.x + entity.hitbox.width + entity.speed) / Game.tileSize][entity.hitbox.y / Game.tileSize].collision || TileController.map[(entity.hitbox.x + entity.hitbox.width + entity.speed) / Game.tileSize][(entity.hitbox.y + entity.hitbox.height) / Game.tileSize].collision);
            case WEST:
                return (TileController.map[(entity.hitbox.x - entity.speed) / Game.tileSize][entity.hitbox.y / Game.tileSize].collision || TileController.map[(entity.hitbox.x - entity.speed) / Game.tileSize][(entity.hitbox.y + entity.hitbox.height) / Game.tileSize].collision);
        }
        return false;
    }

}
