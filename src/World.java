import javax.swing.*;
import java.awt.*;

public class World extends JPanel{

    Snake snake;
    Point fruit;
    private Boolean gameOver;
    private ScoreBoard scoreBoard;
    private JLabel gameOverLabel;
    private Timer timePass;

    public void addSnake(Snake snake) { this.snake = snake; }
    public void addFruit(Point fruit) { this.fruit = fruit; }
    public void addScoreboard(ScoreBoard scoreBoard) {this.scoreBoard = scoreBoard;}
    public void addGameOverLabel(JLabel gameOverLabel) { this.gameOverLabel = gameOverLabel; }
    public void addTimer(Timer timePass) {this.timePass = timePass;}

    public void build(int difficulty) {
        snake.make();
        fruit = generateFruit();
        gameOver = false;
        scoreBoard.reset();
        timePass.start();
        gameOverLabel.setVisible(false);
        gameOverLabel.setText("PRESSIONE ENTER PARA RECOMEÇAR");
        setLayout(new GridLayout());
        gameOverLabel.setFont(Container.DEFAULT_FONT);
        gameOverLabel.setForeground(Container.TEXT_COLOR);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(gameOverLabel);
        gameOverLabel.setVisible(false);
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
            gameOverLabel.setVisible(true);
            timePass.stop();
        }
        repaint();
    }

    public Point generateFruit() {
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
        graphics.setColor(Container.GAME_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());
        Color snakeColor = Container.SNAKE_COLOR;
        Color fruitColor = Container.FRUIT_COLOR;

        if(gameOver) {
            snakeColor = Container.SNAKE_COLOR_TP;
            fruitColor = Container.FRUIT_COLOR_TP;
        }

        graphics.setColor(fruitColor);
        graphics.fillRoundRect(fruit.getPx(), fruit.getPy(), Container.POINT_WIDTH, Container.POINT_HEIGHT, 15, 15);

        graphics.setColor(snakeColor);
        for(Point point : snake.getBody()) {
            graphics.fillRoundRect(point.getPx(), point.getPy(), Container.POINT_WIDTH, Container.POINT_HEIGHT, 15, 15);
        }
    }
}