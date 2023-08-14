package menu;

import main.Window;
import game.Game;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.Color;

public class Menu extends JPanel {

    KeyHandler keyHandler = new KeyHandler();

    final Box textPanel = Box.createVerticalBox();
    final JLabel instruction = new JLabel("Press Any Key to Start");
    final JLabel controlText = new JLabel("Controls:");
    final JLabel control1 = new JLabel("- Move with WASD");
    final JLabel control2 = new JLabel("- Walk over items to pick them up");
    final JLabel control3 = new JLabel("- Drop item with P");
    final JLabel control4 = new JLabel("- Reload with L");
    final JLabel control5 = new JLabel("- Shoot with K");
    final JLabel control6 = new JLabel("- Show debug mode with tilda(~)");

    public Menu() {
        this.setPreferredSize(new Dimension(Game.screenWidth, Game.screenHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusable(true);

        //add the components
        textPanel.add(instruction);
        textPanel.add(instruction);
        textPanel.add(controlText);
        textPanel.add(control1);
        textPanel.add(control2);
        textPanel.add(control3);
        textPanel.add(control4);
        textPanel.add(control5);
        textPanel.add(control6);
        this.add(textPanel);

        //add the key listener
        this.addKeyListener(keyHandler);
    }

    public static Menu getInstance() {
        return Window.menu;
    }

    private class KeyHandler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            Window.launchGame();
        }

        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    
    }

}
