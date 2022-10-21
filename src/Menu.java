import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    public Menu() {
        setBackground(Manager.GAME_COLOR);
        setLayout(new GridLayout(2,1));
        JLabel mainText = new JLabel("SNAKE");
        JLabel subText = new JLabel("PRESSIONE ENTER PARA INICIAR");
        mainText.setFont(Manager.TITLE_FONT);
        mainText.setForeground(Manager.TEXT_COLOR);
        subText.setForeground(Manager.TEXT_COLOR);
        subText.setFont(Manager.DEFAULT_FONT);
        mainText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setVerticalAlignment(SwingConstants.CENTER);
        add(mainText);
        add(subText);
    }
}
