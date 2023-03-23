package game;

import entity.Entity;
import tile.TileController;

public class CollisionChecker {

    Game game;

    public CollisionChecker(Game game) {
        this.game = game;
    }

    //check collision between a tile and entity
    public boolean checkTileCollision(Entity entity) {
        switch (entity.direction) {
            case NORTH:
                return (TileController.map[entity.hitbox.x / Game.tileSize][entity.hitbox.y / Game.tileSize].collides(entity.hitbox) || TileController.map[(entity.hitbox.x + entity.hitbox.width) / Game.tileSize][entity.hitbox.y / Game.tileSize].collides(entity.hitbox));
            case SOUTH:
                return (TileController.map[entity.hitbox.x / Game.tileSize][(entity.hitbox.y + entity.hitbox.height) / Game.tileSize].collides(entity.hitbox) || TileController.map[(entity.hitbox.x + entity.hitbox.width) / Game.tileSize][(entity.hitbox.y + entity.hitbox.height) / Game.tileSize].collides(entity.hitbox));
            case EAST:
                return (TileController.map[(entity.hitbox.x + entity.hitbox.width) / Game.tileSize][entity.hitbox.y / Game.tileSize].collides(entity.hitbox) || TileController.map[(entity.hitbox.x + entity.hitbox.width) / Game.tileSize][(entity.hitbox.y + entity.hitbox.height) / Game.tileSize].collides(entity.hitbox));
            case WEST:
                return (TileController.map[entity.hitbox.x / Game.tileSize][entity.hitbox.y / Game.tileSize].collides(entity.hitbox) || TileController.map[entity.hitbox.x / Game.tileSize][(entity.hitbox.y + entity.hitbox.height) / Game.tileSize].collides(entity.hitbox));
        }
        return false;
    }

}
