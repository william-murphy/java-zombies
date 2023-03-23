package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.lang.Math;
import java.awt.Font;

public class Hud {

    Game game;
    static BufferedImage heartFull, heartHalf;
    static int heartSize = Game.tileSize - 16;
    static int heartX = Game.tileSize * 3;
    static int heartY = Game.tileSize * 10;
    static int roundX = Game.tileSize * 15;
    static int roundY = Game.tileSize;
    static Font roundFont = new Font("Arial", Font.BOLD, Game.tileSize);
    int currentHealth;
    
    public Hud (Game game) {
        this.game = game;
    }

    private void drawHealthBar(Graphics2D g2d) {
        currentHealth = game.entityController.player.health;
        int i = 0;
        while (i < Math.floorDiv(game.entityController.player.health, 2)) {
            g2d.drawImage(heartFull, heartX + Game.tileSize * i, heartY, heartSize, heartSize, null);
            i += 1;
        }
        if (!(game.entityController.player.health % 2 == 0)) {
            g2d.drawImage(heartHalf, heartX + Game.tileSize * i, heartY, heartSize, heartSize, null);
        }
    }

    private void drawCurrentRound(Graphics2D g2d) {
        g2d.setFont(roundFont);
        g2d.drawString(String.valueOf(game.round), roundX, roundY);
    }

    public void drawHud(Graphics2D g2d) {
        drawHealthBar(g2d);
        drawCurrentRound(g2d);
    }

    public static void loadImages() {
        try {
            heartFull = ImageIO.read(Hud.class.getResourceAsStream("/res/hud/heart-full.png"));
            heartHalf = ImageIO.read(Hud.class.getResourceAsStream("/res/hud/heart-half.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
