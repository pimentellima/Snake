package Model;

import java.util.ArrayList;

public class State {

    public Snake snake;
    public Point fruit;

    private Boolean gameOver;
    private int score;
    private int highScore;
    private final ArrayList<StateListener> listeners;

    public State() {
        snake = new Snake();
        fruit = generateValidPoint();
        gameOver = false;
        score = 0;
        highScore = 0;
        listeners = new ArrayList<>();
    }

    public void onSnakeMove() {
        snake.moveForward();

        if(snake.hasEaten(fruit)) {
            score++;
            snake.grow();
            fruit = generateValidPoint();
        }

        if(snake.hasCollided()) {
            gameOver = true;
        }
        notifyStateChange();
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
    
    public Boolean isGameOver() { return gameOver; }
    public int getScore() { return score; }
    public int getHighScore() { return highScore; }
    
    public void reset() {
        snake = new Snake();
        fruit = generateValidPoint();
        gameOver = false;
    
        if(score > highScore) {
            highScore = score;
        }
        score = 0;
        
        notifyStateChange();
    }
    
    public void notifyStateChange() {
        for(StateListener listener: listeners) {
            listener.onStateChange();
        }
    }
    
    public void addStateListener(StateListener listener) {
        listeners.add(listener);
    }
}
