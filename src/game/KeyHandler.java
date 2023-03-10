package game;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.ActionMap;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;

public class KeyHandler {

    GamePanel game;
    public boolean movement = false;
    public boolean upPressed = false;
    public boolean downPressed = false;
    public boolean leftPressed = false;
    public boolean rightPressed = false;

    public KeyHandler(GamePanel game) {
        this.game = game;
        setKeyBindings();
    }

    private void setKeyBindings() {
        InputMap inputMap = game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = game.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("pressed W"), "-Y");
        actionMap.put("-Y", new MoveAction("north"));

        inputMap.put(KeyStroke.getKeyStroke("released W"), "stop");
        actionMap.put("stop", new MoveAction("stop"));

        inputMap.put(KeyStroke.getKeyStroke("pressed S"), "+Y");
        actionMap.put("+Y", new MoveAction("south"));

        inputMap.put(KeyStroke.getKeyStroke("released S"), "stop");
        actionMap.put("stop", new MoveAction("stop"));

        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "-X");
        actionMap.put("-X", new MoveAction("west"));

        inputMap.put(KeyStroke.getKeyStroke("released A"), "stop");
        actionMap.put("stop", new MoveAction("stop"));

        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "+X");
        actionMap.put("+X", new MoveAction("east"));

        inputMap.put(KeyStroke.getKeyStroke("released D"), "stop");
        actionMap.put("stop", new MoveAction("stop"));
    }

    private class MoveAction extends AbstractAction {
        
        String direction;

        public MoveAction(String direction) {
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(direction) {
                case "north":
                    movement = true;
                    upPressed = true;
                    break;
                case "south":
                    movement = true;
                    downPressed = true;
                    break;
                case "west":
                    movement = true;
                    leftPressed = true;
                    break;
                case "east":
                    movement = true;
                    rightPressed = true;
                    break;
                default: 
                    movement = false;
                    upPressed = false;
                    downPressed = false;
                    leftPressed = false;
                    rightPressed = false;
            }
        }
    }
}
