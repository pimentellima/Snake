import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameController implements StateListener {

    private final State state;
    private final MenuView menu;
    private final WorldView world;
    private final Timer update_rate;
    private final Timer input_delay;

    public GameController(State state, MenuView menu, WorldView world) {
        this.state = state;
        this.menu = menu;
        this.world = world;
        update_rate = new Timer (150, new updateAction());
        input_delay = new Timer(150, new inputDelayAction());
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
            input_delay.stop();
        }
    }

    private class MoveRightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.goRight();
            if(!input_delay.isRunning()) {
                state.update();
                input_delay.restart();
            }
        }
    }
    private class MoveLeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.goLeft();
            if(!input_delay.isRunning()) {
                state.update();
                input_delay.restart();
            }
        }
    }
    private class MoveUpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.goUp();
            if (!input_delay.isRunning()) {
                state.update();
                input_delay.restart();
            }
        }
    }

    private class MoveDownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.goDown();
            if (!input_delay.isRunning()) {
                state.update();
                input_delay.restart();
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
            update_rate.start();
        }
    }

    @Override
    public void changeOccurred() {
        if(state.isGameOver()) {
            setRestartBindings();
            update_rate.stop();
        }

        if(!state.isGameOver()) {
            setMovementBindings();
            update_rate.start();
        }
    }

    public void setMovementBindings() {
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

    public void setRestartBindings() {
        InputMap worldInputMap = world.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap worldActionMap = world.getActionMap();
        worldActionMap.clear();

        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        worldActionMap.put("Enter", new ResetWorldAction());
    }
}



