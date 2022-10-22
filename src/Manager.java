import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

interface WorldStateListener {
    void onStateChange();
}

public class Manager extends JPanel implements WorldStateListener {

    private final Menu menu;
    private final Snake snake;
    private final Scoreboard scoreBoard;
    private final GameOverLabel gameOverLabel;
    private final World world;
    private final Timer keyboardDelay;
    private final Timer timePass;

    public static final Color SNAKE_COLOR = new Color(103, 133, 88, 255);
    public static final Color SNAKE_COLOR_TP = new Color(103, 133, 88, 100);
    public static final Color FRUIT_COLOR = new Color(253, 152, 67, 255);
    public static final Color FRUIT_COLOR_TP = new Color(253, 152, 67, 100);
    public static final Color TEXT_COLOR = new Color(151, 117, 170);
    public static final Color SCOREBOARD_COLOR = new Color(43, 43, 44);
    public static final Color GAME_COLOR = new Color(60, 63, 65);
    public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    public static final Font TITLE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 42);
    public static final int WORLD_HEIGHT = 450;
    public static final int WORLD_WIDTH = 600;
    public static final int POINT_WIDTH = 30;
    public static final int POINT_HEIGHT = 30;

    public Manager() {
        this.menu = new Menu();
        this.snake = new Snake();
        this.scoreBoard  = new Scoreboard();
        this.gameOverLabel = new GameOverLabel();
        this.world = new World(snake, scoreBoard, this);
        keyboardDelay = new Timer(100, new AbstractAction() {
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
        setMenuBindings();
        setWorldBindings();
        setGameOverBindings();

        menu.setVisible(true);
        world.setVisible(false);
        scoreBoard.setVisible(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new CardLayout());
        centerContainer.setMaximumSize(new Dimension(600, 450));
        centerContainer.add("menu", menu);
        centerContainer.add("world", world);
        world.add(gameOverLabel);
        add(centerContainer);
        add(scoreBoard);
    }

    public void setMenuBindings() {
        InputMap menuInputMap = menu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap menuActionMap = menu.getActionMap();
        menuInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        menuActionMap.put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setInitialState();
                timePass.start();
                menu.setVisible(false);
                world.setVisible(true);
            }
        });
    }

    public void setWorldBindings() {
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

    public void setGameOverBindings() {
        InputMap gameOverInputMap = gameOverLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        gameOverInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        ActionMap gameOverActionMap = gameOverLabel.getActionMap();
        gameOverActionMap.put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setVisible(false);
                gameOverLabel.setVisible(false);
                scoreBoard.updateHighScore();
                scoreBoard.setInitialState();
                menu.setVisible(true);
            }});
    }

    @Override
    public void onStateChange() {
        timePass.stop();
        gameOverLabel.setVisible(true);
    }
}

class GameOverLabel extends JLabel {

    public GameOverLabel() {
        setText("GAME OVER");
        setFont(Manager.DEFAULT_FONT);
        setForeground(Manager.TEXT_COLOR);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setVisible(false);
    }
}

class Menu extends JPanel {

    public Menu() {
        setBackground(Manager.GAME_COLOR);
        setLayout(new GridLayout(2,1));
        JLabel mainText = new JLabel("SNAKE");
        JLabel subText = new JLabel("PRESSIONE ENTER PARA INICIAR");
        mainText.setFont(Manager.TITLE_FONT);
        mainText.setForeground(Manager.TEXT_COLOR);
        subText.setForeground(Manager.TEXT_COLOR);
        subText.setFont(Manager.DEFAULT_FONT);
        mainText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setVerticalAlignment(SwingConstants.CENTER);
        add(mainText);
        add(subText);
    }
}


