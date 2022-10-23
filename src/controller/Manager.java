package controller;

import game_screen.GameOver;
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
    private final Snake snake;
    private final Scoreboard scoreBoard;
    private final GameOver gameOver;
    private final World world;
    private final Timer keyboardDelay;
    private final Timer timePass;

    public static final Color DEFAULT_COLOR = new Color(151, 117, 170);
    public static final Color SCOREBOARD_COLOR = new Color(43, 43, 44);
    public static final Color GAME_COLOR = new Color(60, 63, 65);
    public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    public static final Font TITLE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 42);
    public static final int POINT_WIDTH = 30;
    public static final int POINT_HEIGHT = 30;
    public static final int WORLD_HEIGHT = 450;
    public static final int WORLD_WIDTH = 600;

    public Manager() {
        menu = new Menu();
        snake = new Snake();
        scoreBoard  = new Scoreboard();
        world = new World(snake, scoreBoard);
        gameOver = new GameOver();
        world.add(gameOver);
        keyboardDelay = new Timer(85, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardDelay.stop();
            }
        });
        timePass = new Timer(100, new AbstractAction() {
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

    public void init() {
        addMenuBindings();
        addWorldBindings();
        addGameOverBindings();
        world.addStateListener(new Listener() {
            @Override
            public void onGameOver() {
                timePass.stop();
                gameOver.setVisible(true);
            }
        });
    }

    public void addMenuBindings() {
        InputMap menuInputMap = menu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap menuActionMap = menu.getActionMap();
        menuInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        menuActionMap.put("Enter", new AbstractAction() {
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
        InputMap worldInputMap = world.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap worldActionMap = world.getActionMap();
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "MoveRight");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "MoveLeft");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "MoveUp");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "MoveDown");
        worldActionMap.put("MoveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!keyboardDelay.isRunning()) {
                    snake.moveRight();
                    keyboardDelay.restart();
                }
            }
        });
        worldActionMap.put("MoveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!keyboardDelay.isRunning()) {
                    snake.moveLeft();
                    keyboardDelay.restart();
                }
            }
        });
        worldActionMap.put("MoveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!keyboardDelay.isRunning()) {
                    snake.moveUp();
                    keyboardDelay.restart();
                }
            }
        });
        worldActionMap.put("MoveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!keyboardDelay.isRunning()) {
                    snake.moveDown();
                    keyboardDelay.restart();
                }
            }
        });
    }

    public void addGameOverBindings() {
        InputMap gameOverInputMap = gameOver.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        gameOverInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        ActionMap gameOverActionMap = gameOver.getActionMap();
        gameOverActionMap.put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setVisible(false);
                gameOver.setVisible(false);
                scoreBoard.updateHighScore();
                scoreBoard.setInitialState();
                menu.setVisible(true);
            }});
    }
}

