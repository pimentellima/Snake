package game_screen;

import scoreboard_screen.Scoreboard;

import controller.Manager;
import controller.Listener;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class World extends JPanel {

    private final Snake snake;
    private Fruit fruit;
    private final Scoreboard scoreBoard;
    private final ArrayList<Listener> listeners;

    public World(Snake snake, Scoreboard scoreBoard) {
        this.snake = snake;
        this.scoreBoard = scoreBoard;
        listeners = new ArrayList<>();
        setVisible(false);
    }

    public void setInitialState() {
        fruit = generateFruit();
    }

    public void update() {
        snake.moveForward();

        if(snake.hasEaten(fruit)) {
            scoreBoard.increaseScore();
            snake.grow();
            fruit = generateFruit();
        }

        if(snake.hasCollided()) {
            gameOver();
        }

        if(snake.getBody().size() - 1 == 300) {
            gameOver();
        }
        repaint();
    }

    private Fruit generateFruit() {
        int height = Manager.WORLD_HEIGHT;
        int width = Manager.WORLD_WIDTH;
        int pointWidth = Manager.POINT_WIDTH;
        int pointHeight = Manager.POINT_HEIGHT;
        boolean valid;
        int px, py;
        do {
            valid = true;
            px = (int) (Math.random() * width / (pointWidth + 2)) * pointWidth;
            py = (int) (Math.random() * height / (pointHeight + 2)) * pointHeight;
            for (Point point : snake.getBody()) {
                if (px == point.getPx() && py == point.getPy()) {
                    valid = false;
                    break;
                }
            }
        } while (!valid);
        return new Fruit(px, py);
    }

    public void addStateListener(Listener listener) {
        listeners.add(listener);
    }

    private void gameOver() {
        for(Listener listener : listeners) {
            listener.onGameOver();
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Manager.GAME_COLOR);
        graphics.fillRect(0,0, Manager.WORLD_WIDTH, Manager.WORLD_HEIGHT);
        fruit.draw(g);
        snake.draw(g);
    }
}

