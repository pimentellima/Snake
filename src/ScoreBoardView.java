import javax.swing.*;
import java.awt.*;

public class ScoreBoardView extends JLabel implements StateListener{

    State state;

    static final Color SCOREBOARD_COLOR = new Color(60, 63, 65);
    static final Color TEXT_COLOR = new Color(151, 117, 170);
    static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);

    public ScoreBoardView(State state) {
        super("ScoreBoard");
        this.state = state;
        state.addStateListener(this);
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(SCOREBOARD_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());
        graphics.setColor(TEXT_COLOR);
        graphics.setFont(DEFAULT_FONT);
        graphics.drawString("Pontuacao = " + state.getScore(), 0, getHeight() - 20);
        graphics.drawString("Maior Pontuacao = " + state.getHighScore(), 150, getHeight() - 20);
    }

    @Override
    public void changeOccurred() {
        repaint();
    }
}
