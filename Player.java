import java.awt.Graphics2D;
import java.awt.Color;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler kh;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
        this.setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void move() {
        if (kh.upPressed == true) {
            y -= speed;
        }
        if (kh.downPressed == true) {
            y += speed;
        }
        if (kh.leftPressed == true) {
            x -= speed;
        }
        if (kh.rightPressed == true) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, gp.tileSize, gp.tileSize);
    }

}