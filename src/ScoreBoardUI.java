import javax.swing.*;
import java.awt.*;

public class ScoreBoardUI extends JLabel {

    State state;

    public ScoreBoardUI(State state){
        super("Score");
        this.state = state;
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Styles.SCOREBOARD_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());
        graphics.setColor(Styles.TEXT_COLOR);
        graphics.setFont(Styles.DEFAULT_FONT);
        graphics.drawString("Pontuacao = " + state.getScore(), 0, getHeight() - 20);
        graphics.drawString("Maior Pontuacao = " + state.getHighScore(), 150, getHeight() - 20);
    }
}
