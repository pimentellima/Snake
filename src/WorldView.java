import javax.swing.*;
import java.awt.*;

public class WorldView extends JPanel implements StateListener {

    private final State state;

    static final Color SNAKE_COLOR = new Color(103, 133, 88, 255);
    static final Color SNAKE_COLOR_TP = new Color(103, 133, 88, 100);
    static final Color FRUIT_COLOR = new Color(253, 152, 67, 255);
    static final Color FRUIT_COLOR_TP = new Color(253, 152, 67, 100);
    static final Color POISON_COLOR = new Color(151, 117, 170);
    static final Color POISON_COLOR_TP = new Color(151, 117, 170, 100);
    static final Color TEXT_COLOR = new Color(151, 117, 170);
    static final Color GAME_COLOR = new Color(43, 43, 44);
    static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);

    public WorldView(State state) {
        this.state = state;
        state.addStateListener(this);
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(GAME_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());
        graphics.setFont(DEFAULT_FONT);
        Color snakeColor = SNAKE_COLOR;
        Color fruitColor = FRUIT_COLOR;
        Color poisonColor = POISON_COLOR;

        if(state.isGameOver()) {
            snakeColor = SNAKE_COLOR_TP;
            fruitColor = FRUIT_COLOR_TP;
            poisonColor = POISON_COLOR_TP;
            graphics.setColor(TEXT_COLOR);
            graphics.drawString("FIM DE JOGO", (getWidth()/2) - 45,(getHeight()/2) - 100);
            graphics.drawString("PRESSIONE ENTER PARA RECOMECAR", (getWidth()/2) - 110,getHeight()/2);
        }

        graphics.setColor(fruitColor);
        graphics.fillRoundRect(state.getFruit().getPx(), state.getFruit().getPy(), 20, 20, 10, 10);

        if(state.getPoison() != null) {
            graphics.setColor(poisonColor);
            graphics.fillRoundRect(state.getPoison().getPx(), state.getPoison().getPy(), 20, 20, 10, 10);
        }

        graphics.setColor(snakeColor);
        for(Point point : state.getSnake().getBody()) {
            graphics.fillRoundRect(point.getPx(), point.getPy(), 20, 20, 10, 10);
        }
    }

    @Override
    public void changeOccurred() {
        repaint();
    }
}