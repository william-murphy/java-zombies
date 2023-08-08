package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

import menu.Menu;
import game.Game;

public class Window extends JFrame {
    
    public static Menu menu = new Menu();
    public static Game game = new Game();

    static CardLayout cards = new CardLayout();
    static JPanel container = new JPanel(cards);

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

    public static void launchMenu() {
        game.stopGameThread();
        cards.show(container, "menu");
        menu.requestFocus();
    }

    public static void launchGame() {
        game.initialize();
        cards.show(container, "game");
        game.requestFocus();
        game.startGameThread();
    }

}
