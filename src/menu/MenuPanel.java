package menu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.Color;

import main.Window;

public class MenuPanel extends JPanel {

    Window window;
    KeyHandler keyHandler = new KeyHandler();

    public MenuPanel(Window window) {
        this.window = window;
        this.setPreferredSize(new Dimension(1024, 768));
        this.setBackground(Color.LIGHT_GRAY);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        //add the components
        JLabel instruction = new JLabel("Press Any Key to Start");
        this.add(instruction);
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
