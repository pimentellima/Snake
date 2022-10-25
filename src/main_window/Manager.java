package main_window;

import game_screen.EndGame;
import game_screen.Menu;
import scoreboard_screen.Scoreboard;
import game_screen.Snake;
import game_screen.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Manager extends JPanel {

    private final Menu menu;
    private final Scoreboard scoreBoard;
    private final Snake snake;
    private final World world;
    private final EndGame endGame;
    private final Timer keyboardDelay;
    private final Timer timePass;

    public static final int REFRESH_RATE = 100;
    public static final int INPUT_DELAY = 85;
    public static final Color TEXT_COLOR = new Color(151, 117, 170);
    public static final Color GAME_COLOR = new Color(60, 63, 65);
    public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    public static final int POINT_WIDTH = 30;
    public static final int POINT_HEIGHT = 30;
    public static final int WORLD_HEIGHT = 450;
    public static final int WORLD_WIDTH = 600;

    public Manager() {
        menu = new Menu();
        scoreBoard  = new Scoreboard();
        snake = new Snake();
        endGame = new EndGame();
        world = new World(snake, scoreBoard, endGame);
        keyboardDelay = new Timer(INPUT_DELAY, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardDelay.stop();
            }
        });
        timePass = new Timer(REFRESH_RATE, new AbstractAction() {
            @Override           public void actionPerformed(ActionEvent e) {
                world.update();
            }
        });
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new CardLayout());
        mainContainer.setPreferredSize(new Dimension(600, 450));
        mainContainer.add("menu", menu);
        mainContainer.add("world", world);
        add(mainContainer);
        add(scoreBoard);
    }

    public void addMenuBindings() {
        InputMap inputMap = menu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        ActionMap actionMap = menu.getActionMap();
        actionMap.put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snake.setInitialState();
                world.setInitialState();
                scoreBoard.setInitialState();
                menu.setVisible(false);
                world.setVisible(true);
                timePass.start();
            }
        });
    }

    public void addWorldBindings() {
        InputMap inputMap = world.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = world.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "MoveRight");
        actionMap.put("MoveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!keyboardDelay.isRunning()) {
                    snake.moveRight();
                    keyboardDelay.restart();
                }
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "MoveLeft");
        actionMap.put("MoveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!keyboardDelay.isRunning()) {
                    snake.moveLeft();
                    keyboardDelay.restart();
                }
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "MoveUp");
        actionMap.put("MoveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!keyboardDelay.isRunning()) {
                    snake.moveUp();
                    keyboardDelay.restart();
                }
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "MoveDown");
        actionMap.put("MoveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!keyboardDelay.isRunning()) {
                    snake.moveDown();
                    keyboardDelay.restart();
                }
            }
        });
        world.addStateListener(new Listener() {
            @Override
            public void onGameOver() {
                timePass.stop();
                endGame.setText("GAME OVER");
                endGame.setVisible(true);
            }

            @Override
            public void onGameWon() {
                timePass.stop();
                endGame.setText("VENCEU");
                endGame.setVisible(true);
            }
        });
    }

    public void addEndGameBindings() {
        InputMap inputMap = endGame.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = endGame.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        actionMap.put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setVisible(false);
                endGame.setVisible(false);
                scoreBoard.updateHighScore();
                scoreBoard.setInitialState();
                menu.setVisible(true);
            }});
    }
}

