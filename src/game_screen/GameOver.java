package game_screen;

import main_window.Manager;

import javax.swing.*;

public class GameOver extends JLabel {

    public GameOver() {
        setText("GAME OVER");
        setFont(Manager.DEFAULT_FONT);
        setForeground(Manager.DEFAULT_COLOR);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setVisible(false);
    }
}
