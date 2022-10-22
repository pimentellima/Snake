import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JPanel{

    private int score;
    private int highScore;
    private final ScoreLabel scoreLabel;
    private final ScoreLabel highScoreLabel;

    public Scoreboard() {
        highScore = 0;
        this.scoreLabel = new ScoreLabel("Pontuação = ", 0);
        this.highScoreLabel = new ScoreLabel("Maior pontuação = ", 0);
        setBackground(Manager.SCOREBOARD_COLOR);
        setLayout(new GridLayout());
        setMaximumSize(new Dimension(600, 100));
        add(scoreLabel);
        add(highScoreLabel);
    }

    public void setInitialState() {
        score = 0;
        scoreLabel.setScore(score);
    }

    public void increaseScore() {
        score++;
        scoreLabel.setScore(score);
    }

    public void updateHighScore() {
        if(score > highScore) {
            highScore = score;
        }
        highScoreLabel.setScore(highScore);
    }

    private static class ScoreLabel extends JLabel {

        String text;

        public ScoreLabel(String text, int score) {
            super(text + score);
            this.text = text;
            setFont(Manager.DEFAULT_FONT);
            setForeground(Manager.TEXT_COLOR);
        }

        public void setScore(int score) {
            setText(text + score);
        }
    }
}
