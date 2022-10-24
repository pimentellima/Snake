package game_screen;

import main_window.Manager;

import javax.swing.*;
import java.awt.*;

public class Menu extends JLabel {

    public Menu() {
        setBackground(Manager.GAME_COLOR);
        setLayout(new GridLayout(2,1));
        JLabel mainText = new JLabel("SNAKE");
        JLabel subText = new JLabel("PRESSIONE ENTER PARA INICIAR");
        mainText.setFont(Manager.TITLE_FONT);
        mainText.setForeground(Manager.DEFAULT_COLOR);
        subText.setForeground(Manager.DEFAULT_COLOR);
        subText.setFont(Manager.DEFAULT_FONT);
        mainText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setVerticalAlignment(SwingConstants.CENTER);
        add(mainText);
        add(subText);
        setVisible(true);
    }
}
