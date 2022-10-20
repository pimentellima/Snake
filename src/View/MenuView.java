package View;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {

    public MenuView() {
        setBackground(Styles.GAME_COLOR);
        setLayout(new GridLayout(2,1));
        JLabel mainText = new JLabel("SNAKE");
        JLabel subText = new JLabel("PRESSIONE ENTER PARA INICIAR");
        mainText.setFont(Styles.TITLE_FONT);
        mainText.setForeground(Styles.TEXT_COLOR);
        subText.setForeground(Styles.TEXT_COLOR);
        subText.setFont(Styles.DEFAULT_FONT);
        mainText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setVerticalAlignment(SwingConstants.CENTER);
        add(mainText);
        add(subText);
    }
}
