package Control;

import Model.State;
import Model.StateListener;
import View.MenuView;
import View.ScoreBoardView;
import View.WorldView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameController implements StateListener {

    private final State state;
    private final MenuView menu;
    private final WorldView world;

    private final Timer movementRate;
    private final Timer keyboardDelay;

    private final ActionMap movementBindings;
    private final ActionMap gameOverBindings;
    private final ActionMap menuBindings;

    public GameController(State state, MenuView menu, WorldView world, ScoreBoardView scoreBoard) {
        this.state = state;
        this.menu = menu;
        this.world = world;

        state.addStateListener(this);
        state.addStateListener(world);
        state.addStateListener(scoreBoard);

        movementRate = new Timer(150, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.onSnakeMove();
            }
        });
        keyboardDelay = new Timer(100, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardDelay.stop();
            }
        });

        // add keyboard bindings
        InputMap worldInputMap = world.getInputMap();
        InputMap menuInputMap = menu.getInputMap();
        movementBindings = new ActionMap();
        gameOverBindings = new ActionMap();
        menuBindings = new ActionMap();

        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "MoveRight");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "MoveLeft");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "MoveUp");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "MoveDown");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        menuInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");

        movementBindings.put("MoveRight",new MoveRightAction());
        movementBindings.put("MoveLeft", new MoveLeftAction());
        movementBindings.put("MoveUp", new MoveUpAction());
        movementBindings.put("MoveDown", new MoveDownAction());
        menuBindings.put("Enter", new MenuStartAction());
        gameOverBindings.put("Enter", new ResetWorldAction());

        menu.setActionMap(menuBindings);
        world.setActionMap(movementBindings);

        world.setVisible(false);
        menu.setVisible(true);
        scoreBoard.setVisible(true);
    }

    private class MoveRightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!keyboardDelay.isRunning()) {
                state.snake.moveRight();
                keyboardDelay.restart();
            }
        }
    }

    private class MoveLeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!keyboardDelay.isRunning()) {
                state.snake.moveLeft();
                keyboardDelay.restart();
            }
        }
    }
    private class MoveUpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!keyboardDelay.isRunning()) {
                state.snake.moveUp();
                keyboardDelay.restart();
            }
        }
    }

    private class MoveDownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!keyboardDelay.isRunning()) {
                state.snake.moveDown();
                keyboardDelay.restart();
            }
        }
    }

    private class ResetWorldAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.reset();
            world.setActionMap(movementBindings);
        }
    }

    private class MenuStartAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            world.setVisible(true);
            menu.setVisible(false);
            movementRate.start();
        }
    }

    @Override
    public void onStateChange() {
        if(state.isGameOver()) {
            world.setActionMap(gameOverBindings);
            movementRate.stop();
        }
        if(!state.isGameOver() && !movementRate.isRunning()) {
            world.setActionMap(movementBindings);
            movementRate.start();
        }
    }
}



