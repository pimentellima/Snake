import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JPanel {

    private int score;
    private int highScore;
    private final JLabel scoreLabel;
    private final JLabel highScoreLabel;
    public static final Color SCOREBOARD_COLOR = new Color(43, 43, 44);

    public Scoreboard() {
        score = 0;
        highScore = 0;
        this.scoreLabel = new JLabel("Pontuação = " + 0);
        this.highScoreLabel = new JLabel("Maior pontuação = " + 0);
        scoreLabel.setForeground(Canvas.TEXT_COLOR);
        highScoreLabel.setForeground(Canvas.TEXT_COLOR);
        scoreLabel.setFont(Canvas.DEFAULT_FONT);
        highScoreLabel.setFont(Canvas.DEFAULT_FONT);
        setBackground(SCOREBOARD_COLOR);
        setLayout(new GridLayout());
        setPreferredSize(new Dimension(600, 100));
        add(scoreLabel);
        add(highScoreLabel);
        setVisible(true);
    }

    public void reset() {
        if(score > highScore) {
            highScore = score;
        }
        highScoreLabel.setText("Maior pontuação = " + highScore);
        score = 0;
        scoreLabel.setText("Pontuação = " + score);
        highScoreLabel.setText("Maior pontuação = " + highScore);
    }

    public void increaseScore() {
        score++;
        scoreLabel.setText("Pontuação = " + score);
    }
}
