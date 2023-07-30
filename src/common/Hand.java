package common;

import game.Game;

import entity.livingentity.Player;

import java.awt.Color;
import java.awt.Graphics2D;

public class Hand {

    Player player;
    public int x, y;

    public Hand(Player player) {
        this.player = player;
    }

    public final void update() {
        switch(player.direction) {
            case NORTH:
                x = player.hitbox.x + player.hitbox.width - 1;
                y = player.hitbox.y - 6;
                break;
            case SOUTH:
                x = player.hitbox.x + 16;
                y = player.hitbox.y + player.hitbox.height - 17;
                break;
            case EAST:
                x = player.hitbox.x + player.hitbox.width + 3;
                y = player.hitbox.y + 1;
                break;
            case WEST:
                x = player.hitbox.x - 3;
                y = player.hitbox.y + 2;
                break;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawRect(Game.getInstance().camera.calculateScreenX(x - 1, 0), Game.getInstance().camera.calculateScreenY(y - 1, 0), 3, 3);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(Game.getInstance().camera.calculateScreenX(x, 0), Game.getInstance().camera.calculateScreenY(y, 0), 1, 1);
    }

}
