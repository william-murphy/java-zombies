import javax.swing.JFrame;

public class Window {
    private final int WIDTH = 640;
    private final int HEIGHT = 480;
    private final String TITLE = "JAVA ZOMBIES";

    public void create() {
        JFrame f = new JFrame();
        Arena arena = new Arena(WIDTH, HEIGHT);
        f.setSize(WIDTH, HEIGHT);
        f.setTitle(TITLE);
        f.add(arena);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}