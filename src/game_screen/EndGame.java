package game_screen;

import main_window.Manager;

import javax.swing.*;

public class EndGame extends JLabel {

    public EndGame() {
        setFont(Manager.DEFAULT_FONT);
        setForeground(Manager.TEXT_COLOR);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setVisible(false);
    }
}
