import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    public Menu() {
        setBackground(GameGUI.GAME_COLOR);
        setLayout(new GridLayout(2,1));
        JLabel mainText = new JLabel("SNAKE");
        JLabel subText = new JLabel("PRESSIONE ENTER PARA INICIAR");
        mainText.setFont(GameGUI.TITLE_FONT);
        mainText.setForeground(GameGUI.TEXT_COLOR);
        subText.setForeground(GameGUI.TEXT_COLOR);
        subText.setFont(GameGUI.DEFAULT_FONT);
        mainText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setVerticalAlignment(SwingConstants.CENTER);
        add(mainText);
        add(subText);
    }
}
