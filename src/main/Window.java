package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import menu.MenuPanel;
import game.GamePanel;

public class Window extends JFrame {
    
    public CardLayout cards = new CardLayout();
    public JPanel container = new JPanel(cards);
    public MenuPanel menu = new MenuPanel(this);
    public GamePanel game = new GamePanel(this);

    public Window() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("JAVA ZOMBIES");
        this.setLocationRelativeTo(null);
        
        this.add(container);

        container.add(menu, "menu");
        container.add(game, "game");

        launchMenu();

        this.pack();
        this.setVisible(true);
    }

    public void launchMenu() {
        game.stopGameThread();
        cards.show(container, "menu");
    }

    public void launchGame() {
        cards.show(container, "game");
        game.startGameThread();
    }

}
