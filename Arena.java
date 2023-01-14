import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Arena extends JComponent {

    private int WIDTH;
    private int HEIGHT;
    
    public Arena(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double r = new Rectangle2D.Double(50, 75, 100, 250);
        g2d.setColor(Color.BLUE);
        g2d.fill(r);
    }

}