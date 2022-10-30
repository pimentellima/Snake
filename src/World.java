import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class World extends JPanel implements ActionListener, KeyListener {

    private final ArrayList<Point> snake;
    private final Point food;
    private Point trail;
    private boolean leftDirection;
    private boolean rightDirection;
    private boolean upDirection;
    private boolean downDirection;
    private final Timer timer;
    private final ArrayList<Listener> listeners;

    private static final Color FRUIT_COLOR = new Color(253, 152, 67, 255);
    private static final Color SNAKE_COLOR = new Color(103, 133, 88, 255);
    private static final int REFRESH_RATE = 100;
    private static final int WORLD_HEIGHT = 450;
    private static final int WORLD_WIDTH = 600;
    public static final int POINT_HEIGHT = 30;
    public static final int POINT_WIDTH = 30;

    public World() {
        this.snake = new ArrayList<>();
        this.food = new Point(0,0);
        listeners = new ArrayList<>();
        timer = new Timer(REFRESH_RATE, this);

        addKeyListener(this);
        setFocusable(true);
    }

    public void reset() {
        snake.clear();
        snake.add(new Point(60,120));
        snake.add(new Point(30,120));
        snake.add(new Point(0,120));
        trail = snake.get(snake.size() - 1);
        newFoodLocation();
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
        timer.start();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && (leftDirection || rightDirection)) {
            upDirection = true;
            leftDirection = false;
            rightDirection = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && (leftDirection || rightDirection)) {
            downDirection = true;
            leftDirection = false;
            rightDirection = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && (upDirection || downDirection)) {
            leftDirection = true;
            upDirection = false;
            downDirection = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && (upDirection || downDirection)) {
            rightDirection = true;
            upDirection = false;
            downDirection = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveForward();
        checkFoodConsumed();
        checkCollision();
        checkMaximumSize();
        repaint();
    }

    private void moveForward() {
        trail = snake.get(snake.size() - 1);
        Point head = snake.get(0);
        int next_px = head.getPx();
        int next_py = head.getPy();

        if(rightDirection) { head.setLocation(head.getPx() + POINT_WIDTH, head.getPy()); }
        else if(leftDirection) { head.setLocation(head.getPx() - POINT_WIDTH, head.getPy()); }
        else if(upDirection) { head.setLocation(head.getPx(), head.getPy() - POINT_HEIGHT); }
        else if(downDirection) { head.setLocation(head.getPx(), head.getPy() + POINT_HEIGHT); }

        for(int i = 1; i < snake.size(); i++) {
            Point point = snake.get(i);
            int aux_px = point.getPx();
            int aux_py = point.getPy();
            point.setLocation(next_px, next_py);
            next_px = aux_px;
            next_py = aux_py;
        }
    }

    private void checkFoodConsumed() {
        Point head = snake.get(0);
        if(head.getPx() == food.getPx() && head.getPy() == food.getPy()) {
            grow();
            scoreIncreased();
            newFoodLocation();
        }
    }

    private void checkCollision() {
        Point head = snake.get(0);
        if(head.getPx() == WORLD_WIDTH || head.getPx() < 0 ||
                head.getPy() == WORLD_HEIGHT || head.getPy() < 0) {
            timer.stop();
            gameLost();
            return;
        }

        for(int i = 1; i < snake.size(); i++) {
            Point point = snake.get(i);
            if (point.getPx() == head.getPx() && point.getPy() == head.getPy()) {
                timer.stop();
                gameLost();
                return;
            }
        }
    }

    private void checkMaximumSize() {
        if(snake.size() - 1 == (WORLD_HEIGHT / POINT_WIDTH) * (WORLD_HEIGHT / POINT_HEIGHT)) {
            timer.stop();
            gameWon();
        }
    }

    private void newFoodLocation() {
        boolean valid;
        int px, py;
        do {
            valid = true;
            px = (int) (Math.random() * WORLD_WIDTH / (POINT_WIDTH + 2)) * POINT_WIDTH;
            py = (int) (Math.random() * WORLD_HEIGHT / (POINT_HEIGHT + 2)) * POINT_HEIGHT;
            for (Point point : snake) {
                if (px == point.getPx() && py == point.getPy()) {
                    valid = false;
                    break;
                }
            }
        } while (!valid);
        food.setLocation(px, py);
    }

    private void grow() {
        snake.add(new Point(trail.getPx(), trail.getPy()));
    }

    private void gameWon() {
        for(Listener listener : listeners) {
            listener.onGameWon();
        }
    }

    private void gameLost() {
        for(Listener listener : listeners) {
            listener.onGameLost();
        }
    }

    private void scoreIncreased() {
        for(Listener listener : listeners) {
            listener.onScoreIncrease();
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Canvas.GAME_COLOR);
        graphics.fillRect(0,0, WORLD_WIDTH, WORLD_HEIGHT);

        graphics.setColor(FRUIT_COLOR);
        food.draw(g);

        graphics.setColor(SNAKE_COLOR);
        for(Point point : snake) {
            point.draw(g);
        }
    }
}

