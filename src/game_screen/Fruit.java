package game_screen;

import java.awt.*;
import main_window.Manager;

public class Fruit {
    private final int px;
    private final int py;
    protected static final Color FRUIT_COLOR = new Color(253, 152, 67, 255);

    public Fruit(int px, int py) {
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
        g.setColor(FRUIT_COLOR);
        g.fillRoundRect(px, py, Manager.POINT_WIDTH, Manager.POINT_HEIGHT, 20, 20);
    }
}
