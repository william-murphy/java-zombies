package game;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.ActionMap;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;

public class KeyHandler {

    Game game;
    
    public static enum Direction { 
        NORTH, SOUTH, EAST, WEST;
        public static final int size;
        static {
           size = values().length;
        } 
    };

    public KeyHandler(Game game) {
        this.game = game;
        setKeyBindings();
    }

    private void setKeyBindings() {
        InputMap inputMap = game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = game.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("pressed W"), "pw");
        actionMap.put("pw", new MoveAction(Direction.NORTH));

        inputMap.put(KeyStroke.getKeyStroke("released W"), "rw");
        actionMap.put("rw", new StopMoveAction(Direction.NORTH));

        inputMap.put(KeyStroke.getKeyStroke("pressed S"), "ps");
        actionMap.put("ps", new MoveAction(Direction.SOUTH));

        inputMap.put(KeyStroke.getKeyStroke("released S"), "rs");
        actionMap.put("rs", new StopMoveAction(Direction.SOUTH));

        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "pd");
        actionMap.put("pd", new MoveAction(Direction.EAST));

        inputMap.put(KeyStroke.getKeyStroke("released D"), "rd");
        actionMap.put("rd", new StopMoveAction(Direction.EAST));

        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "pa");
        actionMap.put("pa", new MoveAction(Direction.WEST));

        inputMap.put(KeyStroke.getKeyStroke("released A"), "ra");
        actionMap.put("ra", new StopMoveAction(Direction.WEST));
    }

    private class MoveAction extends AbstractAction {
        
        Direction ma;

        public MoveAction(Direction ma) {
            this.ma = ma;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            game.entityController.player.move(ma);
        }
    }

    private class StopMoveAction extends AbstractAction {

        Direction ma;

        public StopMoveAction(Direction ma) {
            this.ma = ma;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            game.entityController.player.stopMove(ma);
        }

    }

}
