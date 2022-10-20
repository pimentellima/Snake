import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel{

    int score;
    int highScore;
    private final ScoreLabel scoreLabel;
    private final ScoreLabel highScoreLabel;

    public ScoreBoard() {
        score = 0;
        highScore = 0;
        setBackground(GameGUI.SCOREBOARD_COLOR);
        setLayout(new GridLayout());
        this.scoreLabel = new ScoreLabel("Pontuação = ", 0);
        this.highScoreLabel = new ScoreLabel("Maior pontuação = ", 0);
        add(scoreLabel);
        add(highScoreLabel);
    }

    public void increaseScore() {
        score++;
        scoreLabel.updateScore(score);
    }

    public void resetScore() {
        if(score > highScore) {
            highScore = score;
        }
        score = 0;
        scoreLabel.updateScore(score);
        highScoreLabel.updateScore(highScore);
    }

    private static class ScoreLabel extends JLabel {

        String text;

        public ScoreLabel(String text, int score) {
            super(text + score);
            this.text = text;
            setFont(GameGUI.DEFAULT_FONT);
            setForeground(GameGUI.TEXT_COLOR);
        }

        public void updateScore(int score) {
            setText(text + score);
        }
    }
}
