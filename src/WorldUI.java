import javax.swing.*;
import java.awt.*;

public class WorldUI extends JLabel {

    State state;

    public WorldUI(State state) {
        super("World");
        this.state = state;
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Styles.GAME_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());
        graphics.setFont(Styles.DEFAULT_FONT);
        Color snakeColor = Styles.SNAKE_COLOR;
        Color fruitColor = Styles.FRUIT_COLOR;

        if(state.hasCollided()) {
            snakeColor = Styles.SNAKE_COLOR_TP;
            fruitColor = Styles.FRUIT_COLOR_TP;
            graphics.setColor(Styles.TEXT_COLOR);
            graphics.drawString("FIM DE JOGO", (getWidth()/2) - 45,(getHeight()/2) - 100);
            graphics.drawString("PRESSIONE ENTER PARA RECOMECAR", (getWidth()/2) - 110,getHeight()/2);
        }

        graphics.setColor(fruitColor);
        graphics.fillRoundRect(state.getFruit().getPx(), state.getFruit().getPy(), 20, 20, 10, 10);

        graphics.setColor(snakeColor);
        for(Point point : state.getSnake()) {
            graphics.fillRoundRect(point.getPx(), point.getPy(), 20, 20, 10, 10);
        }
    }
}