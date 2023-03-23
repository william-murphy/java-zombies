package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

import menu.Menu;
import game.Game;

public class Window extends JFrame {
    
    public CardLayout cards = new CardLayout();
    public JPanel container = new JPanel(cards);
    public Menu menu = new Menu(this);
    public Game game = new Game(this);

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
        //load resources for game
        Game.loadResources();

        //switch to game on CardLayout
        cards.show(container, "game");
        game.startGameThread();
    }

}
