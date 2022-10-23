package game_screen;

import main_window.Manager;

import java.awt.*;

import static java.awt.event.KeyEvent.*;

public class Point {
    private int px;
    private int py;
    protected static final Color SNAKE_COLOR = new Color(103, 133, 88, 255);

    public Point(int px, int py) {
        this.px = px;
        this.py = py;
    }

    public void setPosition(int px, int py) {
        this.px = px;
        this.py = py;
    }

    public void move(int direction) {
        if(direction == VK_RIGHT) { px += Manager.POINT_WIDTH; }
        else if(direction == VK_LEFT) { px -= Manager.POINT_WIDTH; }
        else if(direction == VK_UP) { py -= Manager.POINT_HEIGHT; }
        else if(direction == VK_DOWN) { py += Manager.POINT_HEIGHT; }
    }

    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }

    public void draw(Graphics g) {
        g.setColor(SNAKE_COLOR);
        g.fillRoundRect(px, py, Manager.POINT_WIDTH, Manager.POINT_HEIGHT, 15, 15);
    }
}