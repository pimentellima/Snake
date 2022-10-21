import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    public Menu() {
        setBackground(Container.GAME_COLOR);
        setLayout(new GridLayout(2,1));
        JLabel mainText = new JLabel("SNAKE");
        JLabel subText = new JLabel("PRESSIONE ENTER PARA INICIAR");
        mainText.setFont(Container.TITLE_FONT);
        mainText.setForeground(Container.TEXT_COLOR);
        subText.setForeground(Container.TEXT_COLOR);
        subText.setFont(Container.DEFAULT_FONT);
        mainText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setVerticalAlignment(SwingConstants.CENTER);
        add(mainText);
        add(subText);
    }
}
