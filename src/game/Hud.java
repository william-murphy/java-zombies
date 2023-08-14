package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Font;

public class Hud {

    static { loadImages(); }

    static BufferedImage heartFull, heartHalf;
    final static int heartSize = Game.tileSize / 2;
    final static int heartX = Game.tileSize * 3;
    final static int heartY = Game.tileSize * 10;
    final static int roundX = Game.tileSize * 15;
    final static int roundY = Game.tileSize;
    final static Font roundFont = new Font("Arial", Font.BOLD, Game.tileSize);
    final static int inventoryX = heartX;
    final static int inventoryY = heartY + heartSize + 4;
    final static Font inventoryFont = new Font("Arial", Font.PLAIN, 10);
    final static int itemHudX = inventoryX + Game.tileSize * 5;
    final static int itemHudY = inventoryY + Game.tileSize / 2;
    final static Font itemHudFont = new Font("Arial", Font.ITALIC, Game.tileSize / 2);

    private void drawHealthBar(Graphics2D g2d) {
        int i = 0;
        while (i + 2 <= Game.getInstance().entityList.player.health) {
            g2d.drawImage(heartFull, heartX + (Game.tileSize / 2 + 4) * (i / 2), heartY, heartSize, heartSize, null); 
            i += 2;
        }
        if (i < Game.getInstance().entityList.player.health) {
            g2d.drawImage(heartHalf, heartX + (Game.tileSize / 2 + 4) * (i / 2), heartY, heartSize, heartSize, null);
        }
    }

    private void drawCurrentRound(Graphics2D g2d) {
        g2d.setFont(roundFont);
        g2d.drawString(String.valueOf(Game.getInstance().entityList.round), roundX, roundY);
    }

    private void drawInventory(Graphics2D g2d) {
        g2d.setFont(inventoryFont);
        Game.getInstance().entityList.player.inventory.draw(g2d, inventoryX, inventoryY);
    }

    private void drawItemHud(Graphics2D g2d) {
        if (Game.getInstance().entityList.player.inventory.isHoldingItem()) {
            g2d.setFont(itemHudFont);
            Game.getInstance().entityList.player.inventory.getCurrentItem().drawHud(g2d, itemHudX, itemHudY);
        }
    }

    public void drawHud(Graphics2D g2d) {
        drawHealthBar(g2d);
        drawCurrentRound(g2d);
        drawInventory(g2d);
        drawItemHud(g2d);
    }

    private static void loadImages() {
        try {
            heartFull = ImageIO.read(Hud.class.getResourceAsStream("/res/hud/heart-full.png"));
            heartHalf = ImageIO.read(Hud.class.getResourceAsStream("/res/hud/heart-half.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
