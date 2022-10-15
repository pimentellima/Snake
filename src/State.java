import static java.awt.event.KeyEvent.*;

public class State{

    private Snake snake;
    private Point fruit;
    private Point poison;
    private Boolean gameOver;
    private int score;
    private int highScore;

    public State() {
        snake = new Snake();
        fruit = new Point(100, 240);
        poison = null;
        gameOver = false;
        score = 0;
        highScore = 0;
    }

    public void onMoveSnake() {
        snake.move();

        if(snake.hasEaten(fruit)) {
            score++;
            snake.grow();
            generateFruit();
            handlePoison();
        }

        if(snake.hasEaten(poison)) {
            score--;
            snake.shrink();
            removePoison();
        }

        if(snake.hasCollided()) {
            gameOver = true;
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
        fruit = new Point(100, 240);
        poison = null;
        gameOver = false;

        if(score > highScore) {
            highScore = score;
        }
        score = 0;
    }

    public Boolean isGameOver() { return gameOver; }
    public Snake getSnake() { return snake; }
    public Point getFruit() { return fruit; }
    public Point getPoison() { return poison; }
    public int getScore() { return score; }
    public int getHighScore() { return highScore; }
}

class Point {
    private int px;
    private int py;

    public Point(int px, int py) {
        this.px = px;
        this.py = py;
    }

    public void setPosition(int px, int py) {
        this.px = px;
        this.py = py;
    }

    public int getPx() { return px; }
    public int getPy() { return py; }

    public void move(int direction) {
        if(direction == VK_RIGHT) { px += 20; }
        else if(direction == VK_LEFT) { px -= 20; }
        else if(direction == VK_UP) { py -= 20; }
        else if(direction == VK_DOWN) { py += 20; }
    }
}


