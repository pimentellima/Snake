package game_screen;

import main_window.Manager;

import javax.swing.*;
import java.awt.*;

public class Menu extends JLabel {

    public static final Font TITLE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 42);
    public static final Font SUB_FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);

    public Menu() {
        setLayout(new GridLayout(3,1));
        setBackground(Manager.GAME_COLOR);
        setOpaque(true);
        JLabel mainText = new JLabel("SNAKE");
        JLabel textOne = new JLabel("PRESSIONE ENTER PARA INICIAR");
        JLabel textTwo = new JLabel("USE AS SETAS DIRECIONAIS PARA SE MOVER");
        mainText.setFont(TITLE_FONT);
        mainText.setForeground(Manager.TEXT_COLOR);
        textOne.setForeground(Manager.TEXT_COLOR);
        textOne.setFont(SUB_FONT);
        textTwo.setFont(SUB_FONT);
        textTwo.setForeground(Manager.TEXT_COLOR);
        mainText.setHorizontalAlignment(SwingConstants.CENTER);
        textOne.setHorizontalAlignment(SwingConstants.CENTER);
        textOne.setVerticalAlignment(SwingConstants.CENTER);
        textTwo.setHorizontalAlignment(SwingConstants.CENTER);
        textTwo.setVerticalAlignment(SwingConstants.CENTER);
        add(mainText);
        add(textOne);
        add(textTwo);
        setVisible(true);
    }
}
