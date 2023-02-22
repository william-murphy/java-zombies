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
            case "north":
                return (this.gp.tc.map[entity.hitbox.x / gp.tileSize][entity.hitbox.y / gp.tileSize].collides(entity.hitbox) || this.gp.tc.map[(entity.hitbox.x + entity.hitbox.width) / gp.tileSize][entity.hitbox.y / gp.tileSize].collides(entity.hitbox));
            case "south":
                return (this.gp.tc.map[entity.hitbox.x / gp.tileSize][(entity.hitbox.y + entity.hitbox.height) / gp.tileSize].collides(entity.hitbox) || this.gp.tc.map[(entity.hitbox.x + entity.hitbox.width) / gp.tileSize][(entity.hitbox.y + entity.hitbox.height) / gp.tileSize].collides(entity.hitbox));
            case "west":
                return (this.gp.tc.map[entity.hitbox.x / gp.tileSize][entity.hitbox.y / gp.tileSize].collides(entity.hitbox) || this.gp.tc.map[entity.hitbox.x / gp.tileSize][(entity.hitbox.y + entity.hitbox.height) / gp.tileSize].collides(entity.hitbox));
            case "east":
                return (this.gp.tc.map[(entity.hitbox.x + entity.hitbox.width) / gp.tileSize][entity.hitbox.y / gp.tileSize].collides(entity.hitbox) || this.gp.tc.map[(entity.hitbox.x + entity.hitbox.width) / gp.tileSize][(entity.hitbox.y + entity.hitbox.height) / gp.tileSize].collides(entity.hitbox));
            default:
                return false;
        }
    }

}
