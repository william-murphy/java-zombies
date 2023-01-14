import javax.swing.*;

public class Frame {

    private final int WIDTH = 640;
    private final int HEIGHT = 480;
    private final String TITLE = "JAVA ZOMBIES";

    public void launch() {
        JFrame f = new JFrame();
        f.setSize(WIDTH, HEIGHT);
        f.setTitle(TITLE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}