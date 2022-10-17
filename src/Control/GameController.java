package Control;

import Model.State;
import Model.StateListener;
import View.MenuView;
import View.WorldView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameController implements StateListener {

    private final State state;
    private final MenuView menu;
    private final WorldView world;
    private final Timer updateRate;
    private final Timer inputDelay;

    public GameController(State state, MenuView menu, WorldView world) {
        this.state = state;
        this.menu = menu;
        this.world = world;
        updateRate = new Timer (150, new updateAction());
        inputDelay = new Timer(150, new inputDelayAction());
        state.addStateListener(this);

        InputMap menuInputMap = menu.getInputMap();
        ActionMap menuActionMap = menu.getActionMap();

        menuInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        menuActionMap.put("Enter", new MenuStartAction());
    }

    private class updateAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.update();
        }
    }

    private class inputDelayAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputDelay.stop();
        }
    }

    private class MoveRightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.goRight();
            if(!inputDelay.isRunning()) {
                state.update();
                inputDelay.restart();
            }
        }
    }
    private class MoveLeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.goLeft();
            if(!inputDelay.isRunning()) {
                state.update();
                inputDelay.restart();
            }
        }
    }
    private class MoveUpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.goUp();
            if (!inputDelay.isRunning()) {
                state.update();
                inputDelay.restart();
            }
        }
    }

    private class MoveDownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.goDown();
            if (!inputDelay.isRunning()) {
                state.update();
                inputDelay.restart();
            }
        }
    }

    private class ResetWorldAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.reset();
            setMovementBindings();
        }
    }

    private class MenuStartAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            setMovementBindings();

            world.setVisible(true);
            menu.setVisible(false);
            updateRate.start();
        }
    }

    @Override
    public void changeOccurred() {
        if(state.isGameOver()) {
            setRestartBindings();
            updateRate.stop();
        }

        if(!state.isGameOver() && !updateRate.isRunning()) {
            setMovementBindings();
            updateRate.start();
        }
    }

    private void setMovementBindings() {
        InputMap worldInputMap = world.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap worldActionMap = world.getActionMap();
        worldActionMap.remove("Enter");

        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "MoveRight");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "MoveLeft");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "MoveUp");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "MoveDown");

        worldActionMap.put("MoveRight",new MoveRightAction());
        worldActionMap.put("MoveLeft", new MoveLeftAction());
        worldActionMap.put("MoveUp", new MoveUpAction());
        worldActionMap.put("MoveDown", new MoveDownAction());
    }

    private void setRestartBindings() {
        InputMap worldInputMap = world.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap worldActionMap = world.getActionMap();
        worldActionMap.clear();

        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        worldActionMap.put("Enter", new ResetWorldAction());
    }
}



