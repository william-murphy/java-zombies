package game;

import entity.Entity;

public class CollisionChecker {

    private Game game;

    public CollisionChecker(Game game) {
        this.game = game;
    }

    //check collision between a tile and entity
    public boolean checkTileCollision(Entity entity) {
        switch (entity.direction) {
            case NORTH:
                return (this.game.tileController.map[entity.hitbox.x / Game.tileSize][entity.hitbox.y / Game.tileSize].collides(entity.hitbox) || this.game.tileController.map[(entity.hitbox.x + entity.hitbox.width) / Game.tileSize][entity.hitbox.y / Game.tileSize].collides(entity.hitbox));
            case SOUTH:
                return (this.game.tileController.map[entity.hitbox.x / Game.tileSize][(entity.hitbox.y + entity.hitbox.height) / Game.tileSize].collides(entity.hitbox) || this.game.tileController.map[(entity.hitbox.x + entity.hitbox.width) / Game.tileSize][(entity.hitbox.y + entity.hitbox.height) / Game.tileSize].collides(entity.hitbox));
            case EAST:
                return (this.game.tileController.map[(entity.hitbox.x + entity.hitbox.width) / Game.tileSize][entity.hitbox.y / Game.tileSize].collides(entity.hitbox) || this.game.tileController.map[(entity.hitbox.x + entity.hitbox.width) / Game.tileSize][(entity.hitbox.y + entity.hitbox.height) / Game.tileSize].collides(entity.hitbox));
            case WEST:
                return (this.game.tileController.map[entity.hitbox.x / Game.tileSize][entity.hitbox.y / Game.tileSize].collides(entity.hitbox) || this.game.tileController.map[entity.hitbox.x / Game.tileSize][(entity.hitbox.y + entity.hitbox.height) / Game.tileSize].collides(entity.hitbox));
        }
        return false;
    }

}
