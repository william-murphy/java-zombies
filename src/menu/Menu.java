package menu;

import main.Window;
import game.Game;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.Color;

public class Menu extends JPanel {

    Window window;
    KeyHandler keyHandler = new KeyHandler();

    JLabel instruction = new JLabel("Press Any Key to Start");

    public Menu(Window window) {
        this.window = window;
        this.setPreferredSize(new Dimension(Game.screenWidth, Game.screenHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusable(true);

        //add the components
        this.add(instruction);

        //add the key listener
        this.addKeyListener(keyHandler);
    }

    private class KeyHandler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
           window.launchGame();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // do nothing
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // do nothing
        }
    
    }

}
