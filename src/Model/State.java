package Model;

import java.util.ArrayList;
import static java.awt.event.KeyEvent.*;

public class State {

    private Snake snake;
    private Point fruit;
    private Point poison;
    private Boolean gameOver;
    private int score;
    private int highScore;
    private final ArrayList<StateListener> stateListeners;

    public State() {
        snake = new Snake();
        fruit = generateValidPoint();
        poison = null;
        gameOver = false;
        score = 0;
        highScore = 0;
        stateListeners = new ArrayList<>();
    }

    public void update() {
        snake.move();

        if(snake.hasEaten(fruit)) {
            increaseScore();
            snake.grow();
            generateFruit();
            handlePoison();
        }

        if(snake.hasEaten(poison)) {
            decreaseScore();
            snake.shrink();
            removePoison();
        }

        if(snake.hasCollided()) {
            gameOver = true;
        }
        fireWorldEvent();
    }

    public void increaseScore() {
        score++;
    }

    public void decreaseScore() {
        score--;
    }

    public void goLeft() {
        int direction = snake.getDirection();
        if (direction == VK_UP || direction == VK_DOWN) {
            snake.setDirection(VK_LEFT);
        }
    }
    public void goRight() {
        int direction = snake.getDirection();
        if (direction == VK_UP || snake.getDirection() == VK_DOWN) {
            snake.setDirection(VK_RIGHT);
        }
    }
    public void goUp() {
        int direction = snake.getDirection();
        if(direction == VK_RIGHT || direction == VK_LEFT) {
            snake.setDirection(VK_UP);
        }
    }
    public void goDown() {
        int direction = snake.getDirection();
        if(direction == VK_RIGHT || direction == VK_LEFT) {
            snake.setDirection(VK_DOWN);
        }
    }

    public Point generateValidPoint() {
        boolean valid;
        int px, py;
        do {
            valid = true;
            px = (int) (Math.random() * 360 / 22) * 20;
            py = (int) (Math.random() * 400 / 22) * 20;
            for (Point point : snake.getBody()) {
                if (px == point.getPx() && py == point.getPy()) {
                    valid = false;
                    break;
                }
            }
        } while (!valid);
        return new Point(px, py);
    }

    public void generateFruit() {
        fruit = generateValidPoint();
    }

    public void generatePoison() {
        poison = generateValidPoint();
    }

    public void removePoison() {
        poison = null;
    }

    public void handlePoison() {
        double chance = Math.random();
        if(poison == null && chance > 0.7) {
            generatePoison();
        }
        else{
            removePoison();
        }
    }

    public void reset() {
        snake = new Snake();
        generateFruit();
        poison = null;
        gameOver = false;

        if(score > highScore) {
            highScore = score;
        }
        score = 0;

        fireWorldEvent();
    }

    public Boolean isGameOver() { return gameOver; }
    public Snake getSnake() { return snake; }
    public Point getFruit() { return fruit; }
    public Point getPoison() { return poison; }
    public int getScore() { return score; }
    public int getHighScore() { return highScore; }

    public void addStateListener(StateListener listener) {
        stateListeners.add(listener);
    }

    public void fireWorldEvent() {
        for(StateListener listener: stateListeners) {
            listener.changeOccurred();
        }
    }

}
