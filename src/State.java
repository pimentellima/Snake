import java.util.ArrayList;
import static java.awt.event.KeyEvent.*;

public class State {
    
    private ArrayList<Point> snake;
    private Point fruit;
    private int direction;
    private int score;
    private int highScore;
    private Boolean collided;

    public State() {
        ArrayList<Point> snake = new ArrayList<>();
        snake.add(new Point(40,120));
        snake.add(new Point(20,120));
        snake.add(new Point(0,120));
        this.snake = snake;
        fruit = new Point(100, 240);
        direction = VK_RIGHT;
        collided = false;
    }

    public void onMoveSnake() {
        Point head = snake.get(0);

        int previous_px = head.getPx();
        int previous_py = head.getPy();
        head.move(direction);

        if(head.getPx() == 340 || head.getPx() < 0 || head.getPy() == 400 || head.getPy() < 0) {
            collided = true;
        }

        for(int i = 1; i < snake.size(); i++) {
            Point point = snake.get(i);
            int current_px = point.getPx();
            int current_py = point.getPy();
            point.setPosition(previous_px, previous_py);
            previous_px = current_px;
            previous_py = current_py;

            if (point.getPx() == head.getPx() && point.getPy() == head.getPy()) {
                collided = true;
            }
        }

        if(head.getPx() == fruit.getPx() && head.getPy() == fruit.getPy()) {
            score++;
            boolean empty;
            int random_px, random_py;
            do {
                empty = true;
                random_px = (int) (Math.random() * 360/22) * 20;
                random_py = (int) (Math.random() * 400/22) * 20;
                for (Point point : snake) {
                    if (random_px == point.getPx() && random_py == point.getPy()) {
                        empty = false;
                        break;
                    }
                }
            } while(!empty);
            fruit.setPosition(random_px, random_py);
            snake.add(new Point(previous_px, previous_py));
        }
    }

    public void changeDirection(int direction) {
        if (direction == VK_RIGHT && (this.direction == VK_UP || this.direction == VK_DOWN)) {
            this.direction = direction;
        }
        else if (direction == VK_LEFT && (this.direction == VK_UP || this.direction == VK_DOWN)) {
            this.direction = direction;
        }
        else if (direction == VK_UP && (this.direction == VK_RIGHT || this.direction == VK_LEFT)) {
            this.direction = direction;
        }
        else if (direction == VK_DOWN && (this.direction == VK_RIGHT || this.direction == VK_LEFT)) {
            this.direction = direction;
        }
    }

    public void reset() {
        ArrayList<Point> snake = new ArrayList<>();
        snake.add(new Point(40,120));
        snake.add(new Point(20,120));
        snake.add(new Point(0,120));
        this.snake = snake;
        fruit = new Point(100, 240);
        direction = VK_RIGHT;

        if(score > highScore) {
            highScore = score;
        }
        score = 0;
        collided = false;
    }

    public ArrayList<Point> getSnake() { return snake; }
    public Point getFruit() { return fruit; }
    public Boolean hasCollided() { return collided; }
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


