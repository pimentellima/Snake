import javax.swing.*;
import java.awt.*;

public class ScoreView extends JLabel {

    State state;
    static final Color DEFAULT_COLOR = new Color(151, 117, 170);
    static final Color BACKGROUND_COLOR = new Color(43, 43, 44);

    public ScoreView(State state){
        super("ScoreView");
        this.state = state;
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());

        graphics.setColor(DEFAULT_COLOR);
        String pontuacao = "Pontuacao = " + state.score + "           " + "Maior Pontuacao = " + state.record;
        graphics.drawString(pontuacao, 0, getHeight() - 20);
    }
}
