package game_screen;

import main_window.Manager;

import java.awt.*;
import java.util.ArrayList;
import static java.awt.event.KeyEvent.*;

public class Snake {

    private final ArrayList<Point> body;
    private Point trail;
    private int direction;

    public Snake() {
        this.body = new ArrayList<>();
        direction = VK_RIGHT;
    }

    public void setInitialState() {
        body.clear();
        body.add(new Point(60,120));
        body.add(new Point(30,120));
        body.add(new Point(0,120));
        trail = body.get(body.size() - 1);
        direction = VK_RIGHT;
    }

    public void moveForward() {
        trail = body.get(body.size() - 1);

        Point head = body.get(0);
        int next_px = head.getPx();
        int next_py = head.getPy();
        head.move(direction);

        for(int i = 1; i < body.size(); i++) {
            Point point = body.get(i);
            int aux_px = point.getPx();
            int aux_py = point.getPy();
            point.setPosition(next_px, next_py);
            next_px = aux_px;
            next_py = aux_py;
        }
    }

    public void moveLeft() {
        if (direction == VK_UP || direction == VK_DOWN) {
            direction = VK_LEFT;
        }
    }
    public void moveRight() {
        if (direction == VK_UP || direction == VK_DOWN) {
            direction = VK_RIGHT;
        }
    }
    public void moveUp() {
        if(direction == VK_RIGHT || direction == VK_LEFT) {
            direction = VK_UP;
        }
    }
    public void moveDown() {
        if(direction == VK_RIGHT || direction == VK_LEFT) {
            direction = VK_DOWN;
        }
    }

    public void grow() {
        body.add(new Point(trail.getPx(), trail.getPy()));
    }

    public Boolean hasEaten(Fruit fruit) {
        Point head = body.get(0);
        return head.getPx() == fruit.getPx() && head.getPy() == fruit.getPy();
    }

    public Boolean hasMaximumSize() {
        return body.size() - 1 == (Manager.WORLD_HEIGHT / Manager.POINT_WIDTH) * (Manager.WORLD_HEIGHT / Manager.POINT_HEIGHT);
    }

    public Boolean hasCollided() {
        Point head = body.get(0);
        if(head.getPx() == Manager.WORLD_WIDTH || head.getPx() < 0 ||
                head.getPy() == Manager.WORLD_HEIGHT || head.getPy() < 0) {
            return true;
        }
        for(int i = 1; i < body.size(); i++) {
            Point point = body.get(i);
            if (point.getPx() == head.getPx() && point.getPy() == head.getPy()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Point> getBody() { return body; }

    public void draw(Graphics g) {
        for(Point point : body) {
            point.draw(g);
        }
    }
}

