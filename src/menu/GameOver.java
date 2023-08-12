package menu;

import main.Window;
import game.Game;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.Color;

public class GameOver extends JPanel {
    
    KeyHandler keyHandler = new KeyHandler();

    JLabel instruction = new JLabel("Game over! Press Any Key to return to the main menu.");

    public GameOver() {
        this.setPreferredSize(new Dimension(Game.screenWidth, Game.screenHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusable(true);

        //add the components
        this.add(instruction);

        //add the key listener
        this.addKeyListener(keyHandler);
    }

    public static GameOver getInstance() {
        return Window.gameOver;
    }

    private class KeyHandler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            Window.launchMenu();
        }

        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    
    }

}
