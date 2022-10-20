package View;

import Model.State;
import Model.StateListener;
import javax.swing.*;
import java.awt.*;

public class ScoreBoardView extends JPanel implements StateListener {

    private final State state;
    private final ScoreLabel scoreLabel;
    private final ScoreLabel highScoreLabel;

    public ScoreBoardView(State state) {
        this.state = state;
        setBackground(Styles.SCOREBOARD_COLOR);
        setLayout(new GridLayout());
        this.scoreLabel = new ScoreLabel("Pontuação = ", state.getScore());
        this.highScoreLabel = new ScoreLabel("Maior pontuação = ", state.getHighScore());
        add(scoreLabel);
        add(highScoreLabel);
    }

    private static class ScoreLabel extends JLabel {

        String text;

        public ScoreLabel(String text, int score) {
            super(text + score);
            this.text = text;
            setFont(Styles.DEFAULT_FONT);
            setForeground(Styles.TEXT_COLOR);
        }
        public void updateScore(int score) {
            setText(text + score);
        }
    }

    @Override
    public void onStateChange() {
        scoreLabel.updateScore(state.getScore());
        highScoreLabel.updateScore(state.getHighScore());
    }
}
