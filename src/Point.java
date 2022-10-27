import java.awt.*;

public class Point {
    private int px;
    private int py;

    public Point(int px, int py) {
        this.px = px;
        this.py = py;
    }

    public void setLocation(int px, int py) {
        this.px = px;
        this.py = py;
    }

    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }

    public void draw(Graphics g) {
        g.fillRoundRect(px, py, World.POINT_WIDTH, World.POINT_HEIGHT, 15, 15);
    }
}