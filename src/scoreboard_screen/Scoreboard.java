package scoreboard_screen;

import main_window.Manager;
import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JPanel{

    private int score;
    private int highScore;
    private final ScoreLabel scoreLabel;
    private final ScoreLabel highScoreLabel;
    public static final Color SCOREBOARD_COLOR = new Color(43, 43, 44);

    public Scoreboard() {
        highScore = 0;
        this.scoreLabel = new ScoreLabel("Pontuação = ", 0);
        this.highScoreLabel = new ScoreLabel("Maior pontuação = ", 0);
        setBackground(SCOREBOARD_COLOR);
        setLayout(new GridLayout());
        setPreferredSize(new Dimension(600, 100));
        add(scoreLabel);
        add(highScoreLabel);
        setVisible(true);
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
