import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import static java.awt.event.KeyEvent.*;

public class World extends JPanel implements ActionListener {

    private final Scoreboard scoreboard;
    private final ArrayList<Point> snake;
    private final Point food;
    private Point trail;
    private boolean leftDirection;
    private boolean rightDirection;
    private boolean upDirection;
    private boolean downDirection;
    private final Timer timer;
    private final ArrayList<Listener> listeners;

    protected static final Color FRUIT_COLOR = new Color(253, 152, 67, 255);
    protected static final Color SNAKE_COLOR = new Color(103, 133, 88, 255);
    public static final int REFRESH_RATE = 100;
    public static final int WORLD_HEIGHT = 450;
    public static final int WORLD_WIDTH = 600;
    public static final int POINT_WIDTH = 30;
    public static final int POINT_HEIGHT = 30;

    public World(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        this.snake = new ArrayList<>();
        this.food = new Point(0,0);
        listeners = new ArrayList<>();
        timer = new Timer(REFRESH_RATE, this);

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_RIGHT, 0), "Right");
        this.getActionMap().put("Right", new RightAction());
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_LEFT, 0), "Left");
        this.getActionMap().put("Left", new LeftAction());
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "Up");
        this.getActionMap().put("Up", new UpAction());
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Down");
        this.getActionMap().put("Down", new DownAction());
    }

    public void setInitialState() {
        snake.clear();
        snake.add(new Point(60,120));
        snake.add(new Point(30,120));
        snake.add(new Point(0,120));
        newFruitLocation();
        trail = snake.get(snake.size() - 1);
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
        timer.start();
    }

    private class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (upDirection || downDirection) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
        }
    }

    private class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (upDirection || downDirection) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
        }
    }

    private class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(rightDirection || leftDirection) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }

    private class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(rightDirection || leftDirection) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveForward();
        checkFoodConsumed();
        checkCollision();
        checkMaximumSize();
        repaint();
    }

    public void moveForward() {
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

    public void checkFoodConsumed() {
        Point head = snake.get(0);
        if(head.getPx() == food.getPx() && head.getPy() == food.getPy()) {
            grow();
            scoreboard.increaseScore();
            newFruitLocation();
        }
    }

    public void checkCollision() {
        Point head = snake.get(0);
        if(head.getPx() == WORLD_WIDTH || head.getPx() < 0 ||
                head.getPy() == WORLD_HEIGHT || head.getPy() < 0) {
            timer.stop();
            gameOver();
            return;
        }

        for(int i = 1; i < snake.size(); i++) {
            Point point = snake.get(i);
            if (point.getPx() == head.getPx() && point.getPy() == head.getPy()) {
                timer.stop();
                gameOver();
                return;
            }
        }
    }

    public void checkMaximumSize() {
        if(snake.size() - 1 == (WORLD_HEIGHT / POINT_WIDTH) * (WORLD_HEIGHT / POINT_HEIGHT)) {
            timer.stop();
            gameWon();
        }
    }

    public void grow() {
        snake.add(new Point(trail.getPx(), trail.getPy()));
    }

    private void newFruitLocation() {
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

    public void addStateListener(Listener listener) {
        listeners.add(listener);
    }

    private void gameOver() {
        for(Listener listener : listeners) {
            listener.gameOver();
        }
    }

    private void gameWon() {
        for(Listener listener : listeners) {
            listener.gameWon();
        }
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

