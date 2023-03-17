package game;

import entity.Entity;

public class CollisionChecker {

    private GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    //check collision between a tile and entity
    public boolean checkTileCollision(Entity entity) {
        switch (entity.direction) {
            case NORTH:
                return (this.gp.tileController.map[entity.hitbox.x / gp.tileSize][entity.hitbox.y / gp.tileSize].collides(entity.hitbox) || this.gp.tileController.map[(entity.hitbox.x + entity.hitbox.width) / gp.tileSize][entity.hitbox.y / gp.tileSize].collides(entity.hitbox));
            case SOUTH:
                return (this.gp.tileController.map[entity.hitbox.x / gp.tileSize][(entity.hitbox.y + entity.hitbox.height) / gp.tileSize].collides(entity.hitbox) || this.gp.tileController.map[(entity.hitbox.x + entity.hitbox.width) / gp.tileSize][(entity.hitbox.y + entity.hitbox.height) / gp.tileSize].collides(entity.hitbox));
            case EAST:
                return (this.gp.tileController.map[(entity.hitbox.x + entity.hitbox.width) / gp.tileSize][entity.hitbox.y / gp.tileSize].collides(entity.hitbox) || this.gp.tileController.map[(entity.hitbox.x + entity.hitbox.width) / gp.tileSize][(entity.hitbox.y + entity.hitbox.height) / gp.tileSize].collides(entity.hitbox));
            case WEST:
                return (this.gp.tileController.map[entity.hitbox.x / gp.tileSize][entity.hitbox.y / gp.tileSize].collides(entity.hitbox) || this.gp.tileController.map[entity.hitbox.x / gp.tileSize][(entity.hitbox.y + entity.hitbox.height) / gp.tileSize].collides(entity.hitbox));
        }
        return false;
    }

}
