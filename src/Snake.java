import java.util.ArrayList;
import static java.awt.event.KeyEvent.*;

public class Snake {

    private final ArrayList<Point> body;
    private Point trail;
    private int direction;

    public Snake() {
        ArrayList<Point> body = new ArrayList<>();
        body.add(new Point(40,120));
        body.add(new Point(20,120));
        body.add(new Point(0,120));
        this.body = body;
        trail = body.get(body.size() - 1);
        direction = VK_RIGHT;
    }

    public void move() {
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

    public void grow() { body.add(new Point(trail.getPx(), trail.getPy())); }
    public void shrink() { body.remove(body.get(body.size() - 1)); }

    public void changeDirection(int direction) {
        if (this.direction == VK_RIGHT && (direction == VK_UP || direction == VK_DOWN) ||
                this.direction == VK_LEFT && (direction == VK_UP || direction == VK_DOWN) ||
                this.direction == VK_UP && (direction == VK_RIGHT || direction == VK_LEFT) ||
                this.direction == VK_DOWN && (direction == VK_RIGHT || direction == VK_LEFT)) {
            this.direction = direction;
        }
    }

    public Boolean hasEaten(Point point) {
        if(point == null) {
            return false;
        }
        Point head = body.get(0);
        return head.getPx() == point.getPx() && head.getPy() == point.getPy();
    }

    public Boolean hasCollided() {
        Point head = body.get(0);

        if(head.getPx() == 340 || head.getPx() < 0 || head.getPy() == 400 || head.getPy() < 0) {
            return true;
        }
        for(int i = 1; i < body.size(); i++) {
            Point point = body.get(i);
            if (point.getPx() == head.getPx() && point.getPy() == head.getPy()) {
                    return true;
                }
            } return false;
        }

    public ArrayList<Point> getBody() { return body; }
}
