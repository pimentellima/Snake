import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class World extends JPanel{

    private Snake snake;
    private Point fruit;
    private Boolean gameOver;
    private final ScoreBoard scoreBoard;
    private final JLabel gameOverLabel;

    private final Timer timePass;
    private final Timer keyboardDelay;

    public World(ScoreBoard scoreBoard) {
        snake = new Snake();
        fruit = generateValidPoint();
        gameOver = false;
        this.scoreBoard = scoreBoard;
        this.gameOverLabel = new JLabel("PRESSIONE ENTER PARA RECOMEÇAR");

        timePass = new Timer(150, new timePassAction());

        keyboardDelay = new Timer(100, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardDelay.stop();
            }
        });

        setWorldBindings();

        setLayout(new GridLayout());
        gameOverLabel.setFont(Board.DEFAULT_FONT);
        gameOverLabel.setForeground(Board.TEXT_COLOR);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(gameOverLabel);
        gameOverLabel.setVisible(false);
    }

    private class timePassAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            snake.moveForward();
            if(snake.hasEaten(fruit)) {
                scoreBoard.increaseScore();
                snake.grow();
                fruit = generateValidPoint();
            }

            if(snake.hasCollided()) {
                gameOver = true;
                timePass.stop();
                gameOverLabel.setVisible(true);
            }

            repaint();
        }
    }

    public void start() {
        timePass.start();
    }

    public Point generateValidPoint() {
        boolean valid;
        int px, py;
        do {
            valid = true;
            px = (int) (Math.random() * 360 / 32) * 30;
            py = (int) (Math.random() * 400 / 32) * 30;
            for (Point point : snake.getBody()) {
                if (px == point.getPx() && py == point.getPy()) {
                    valid = false;
                    break;
                }
            }
        } while (!valid);
        return new Point(px, py);
    }

    public void setWorldBindings() {
        InputMap worldInputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        InputMap gameOverInputMap = gameOverLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap worldActionMap = this.getActionMap();
        ActionMap gameOverActionMap = gameOverLabel.getActionMap();

        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "MoveRight");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "MoveLeft");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "MoveUp");
        worldInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "MoveDown");
        gameOverInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");

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

        gameOverActionMap.put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snake = new Snake();
                fruit = generateValidPoint();
                gameOver = false;
                scoreBoard.resetScore();
                timePass.restart();
                gameOverLabel.setVisible(false);
            }
        });
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Board.GAME_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());
        Color snakeColor = Board.SNAKE_COLOR;
        Color fruitColor = Board.FRUIT_COLOR;

        if(gameOver) {
            snakeColor = Board.SNAKE_COLOR_TP;
            fruitColor = Board.FRUIT_COLOR_TP;
        }

        graphics.setColor(fruitColor);
        graphics.fillRoundRect(fruit.getPx(), fruit.getPy(), Board.POINT_WIDTH, Board.POINT_HEIGHT, 15, 15);

        graphics.setColor(snakeColor);
        for(Point point : snake.getBody()) {
            graphics.fillRoundRect(point.getPx(), point.getPy(), Board.POINT_WIDTH, Board.POINT_HEIGHT, 15, 15);
        }
    }
}