package Model;

import static java.awt.event.KeyEvent.*;

public class Point {
    protected int px;
    protected int py;

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

