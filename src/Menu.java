import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    public Menu() {
        setBackground(Board.GAME_COLOR);
        setLayout(new GridLayout(2,1));
        JLabel mainText = new JLabel("SNAKE");
        JLabel subText = new JLabel("PRESSIONE ENTER PARA INICIAR");
        mainText.setFont(Board.TITLE_FONT);
        mainText.setForeground(Board.TEXT_COLOR);
        subText.setForeground(Board.TEXT_COLOR);
        subText.setFont(Board.DEFAULT_FONT);
        mainText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setHorizontalAlignment(SwingConstants.CENTER);
        subText.setVerticalAlignment(SwingConstants.CENTER);
        add(mainText);
        add(subText);
    }
}
