package View;

import Model.Point;
import Model.State;
import Model.StateListener;
import javax.swing.*;
import java.awt.*;

public class WorldView extends JPanel implements StateListener {

    private final State state;
    private final JLabel gameOverLabel;
    private static final int POINT_WIDTH = 30;
    private static final int POINT_HEIGHT = 30;

    public WorldView(State state) {
        this.state = state;
        setLayout(new GridLayout());
        this.gameOverLabel = new JLabel("PRESSIONE ENTER PARA RECOMEÇAR");
        gameOverLabel.setFont(Styles.DEFAULT_FONT);
        gameOverLabel.setForeground(Styles.TEXT_COLOR);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVisible(false);
        add(gameOverLabel);
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Styles.GAME_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());
        Color snakeColor = Styles.SNAKE_COLOR;
        Color fruitColor = Styles.FRUIT_COLOR;

        if(state.isGameOver()) {
            snakeColor = Styles.SNAKE_COLOR_TP;
            fruitColor = Styles.FRUIT_COLOR_TP;
        }

        graphics.setColor(fruitColor);
        graphics.fillRoundRect(state.fruit.getPx(), state.fruit.getPy(), POINT_WIDTH, POINT_HEIGHT, 15, 15);

        graphics.setColor(snakeColor);
        for(Point point : state.snake.getBody()) {
            graphics.fillRoundRect(point.getPx(), point.getPy(), POINT_WIDTH, POINT_HEIGHT, 15, 15);
        }
    }

    @Override
    public void onStateChange() {
        gameOverLabel.setVisible(state.isGameOver());
        repaint();
    }
}