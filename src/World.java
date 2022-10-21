import javax.swing.*;
import java.awt.*;

public class World extends JPanel {

    Snake snake;
    Point fruit;
    private Boolean gameOver;
    private final Scoreboard scoreBoard;
    private final Timer timePass;
    private final WorldStateListener worldStateListener;

    public World(Snake snake, Scoreboard scoreBoard, Timer timePass, WorldStateListener worldStateListener) {
        this.snake = snake;
        this.scoreBoard = scoreBoard;
        this.timePass = timePass;
        this.worldStateListener = worldStateListener;
        gameOver = false;
        snake.setDefault();
        fruit = generateFruit();
        timePass.start();
        setLayout(new GridLayout());
    }

    public void setDefault() {
        snake.setDefault();
        fruit = generateFruit();
        gameOver = false;
        timePass.start();
    }

    public void update() {
        snake.moveForward();

        if(snake.hasEaten(fruit)) {
            scoreBoard.increaseScore();
            snake.grow();
            fruit = generateFruit();
        }

        if(snake.hasCollided()) {
            gameOver = true;
            timePass.stop();
            stateChanged();
        }

        if(snake.getBody().size() - 1 == 300) {
            timePass.stop();
            stateChanged();

        }
        repaint();
    }

    private Point generateFruit() {
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

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Manager.GAME_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());
        Color snakeColor = Manager.SNAKE_COLOR;
        Color fruitColor = Manager.FRUIT_COLOR;

        if(gameOver) {
            snakeColor = Manager.SNAKE_COLOR_TP;
            fruitColor = Manager.FRUIT_COLOR_TP;
        }

        graphics.setColor(fruitColor);
        graphics.fillRoundRect(fruit.getPx(), fruit.getPy(), Manager.POINT_WIDTH, Manager.POINT_HEIGHT, 15, 15);

        graphics.setColor(snakeColor);
        for(Point point : snake.getBody()) {
            graphics.fillRoundRect(point.getPx(), point.getPy(), Manager.POINT_WIDTH, Manager.POINT_HEIGHT, 15, 15);
        }
    }

    public void stateChanged() {
        worldStateListener.onStateChange();
    }
}