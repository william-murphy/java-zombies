package game;

import common.Direction;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.ActionMap;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;

public class KeyHandler {

    public KeyHandler() {
        setKeyBindings();
    }

    private void setKeyBindings() {
        InputMap inputMap = Game.getInstance().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = Game.getInstance().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("pressed W"), "pw");
        actionMap.put("pw", new Move(Direction.NORTH));

        inputMap.put(KeyStroke.getKeyStroke("released W"), "rw");
        actionMap.put("rw", new StopMove(Direction.NORTH));

        inputMap.put(KeyStroke.getKeyStroke("pressed S"), "ps");
        actionMap.put("ps", new Move(Direction.SOUTH));

        inputMap.put(KeyStroke.getKeyStroke("released S"), "rs");
        actionMap.put("rs", new StopMove(Direction.SOUTH));

        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "pd");
        actionMap.put("pd", new Move(Direction.EAST));

        inputMap.put(KeyStroke.getKeyStroke("released D"), "rd");
        actionMap.put("rd", new StopMove(Direction.EAST));

        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "pa");
        actionMap.put("pa", new Move(Direction.WEST));

        inputMap.put(KeyStroke.getKeyStroke("released A"), "ra");
        actionMap.put("ra", new StopMove(Direction.WEST));

        inputMap.put(KeyStroke.getKeyStroke("pressed BACK_QUOTE"), "bq");
        actionMap.put("bq", new EnableDebug());

        inputMap.put(KeyStroke.getKeyStroke("pressed L"), "pl");
        actionMap.put("pl", new UseItem());

        inputMap.put(KeyStroke.getKeyStroke("released L"), "rl");
        actionMap.put("rl", new StopUseItem());

        inputMap.put(KeyStroke.getKeyStroke("pressed P"), "pp");
        actionMap.put("pp", new DropItem());
    }

    private class Move extends AbstractAction {
        Direction ma;
        public Move(Direction ma) {
            this.ma = ma;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Game.getInstance().entityList.player.move(ma);
        }
    }

    private class StopMove extends AbstractAction {
        Direction ma;
        public StopMove(Direction ma) {
            this.ma = ma;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Game.getInstance().entityList.player.stopMove(ma);
        }
    }

    private class EnableDebug extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Game.getInstance().debug = !Game.getInstance().debug;
        }
    }

    private class UseItem extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Game.getInstance().entityList.player.useItem();
        }
    }

    private class StopUseItem extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Game.getInstance().entityList.player.stopUseItem();
        }
    }

    private class DropItem extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Game.getInstance().entityList.player.dropItem();
        }
    }
}
