import javax.swing.*;
import java.awt.*;

public class Canvas extends JLabel {

    State state;
    static final Color SNAKE_COLOR = new Color(103, 133, 88, 255);
    static final Color SNAKE_COLOR_TP = new Color(103, 133, 88, 100);
    static final Color DOT_COLOR = new Color(253, 152, 67, 255);
    static final Color DOT_COLOR_TP = new Color(253, 152, 67, 100);
    static final Color DEFAULT_COLOR = new Color(151, 117, 170);
    static final Color GAME_COLOR = new Color(60, 63, 65);

    public Canvas(State state) {
        super("Canvas");
        this.state = state;
    }

    public void paintComponent(Graphics g) {
        Color snakeColor = SNAKE_COLOR;
        Color dotColor = DOT_COLOR;
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(GAME_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());

        if(state.screen.equals("initial-screen")) {
            graphics.setColor(DEFAULT_COLOR);
            graphics.drawString("SNAKE GAME", getWidth()/2 - 50,(getHeight()/2) - 100);
            graphics.drawString("PRESSIONE ENTER PARA INICIAR", getWidth()/2 - 100,(getHeight()/2));
        }

        else {
            if(state.screen.equals("game-over-screen")) {
                snakeColor = SNAKE_COLOR_TP;
                dotColor = DOT_COLOR_TP;
                graphics.setColor(DEFAULT_COLOR);
                graphics.drawString("FIM DE JOGO", (getWidth()/2) - 45,(getHeight()/2) - 100);
                graphics.drawString("PRESSIONE ENTER PARA RECOMECAR", (getWidth()/2) - 110,getHeight()/2);
            }

            graphics.setColor(dotColor);
            graphics.fillRoundRect(state.blackDot.px, state.blackDot.py, 20, 20, 10, 10);

            graphics.setColor(snakeColor);
            for(Dot dot: state.dots) {
                graphics.fillRoundRect(dot.px, dot.py, 20, 20, 10, 10);
            }

        }
    }
}