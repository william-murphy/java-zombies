package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean movement;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
            movement = true;
        } else if (code == KeyEvent.VK_S) {
            downPressed = true;
            movement = true;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = true;
            movement = true;
        } else if (code == KeyEvent.VK_D) {
            rightPressed = true;
            movement = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W) {
            movement = false;
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            movement = false;
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            movement = false;
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            movement = false;
            rightPressed = false;
        }
    }

}