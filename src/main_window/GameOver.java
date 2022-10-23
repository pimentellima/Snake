package main_window;

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
