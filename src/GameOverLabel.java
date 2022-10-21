import javax.swing.*;

public class GameOverLabel extends JLabel {

    public GameOverLabel() {
        setText("PRESSIONE ENTER PARA RECOMEÇAR");
        setFont(Manager.DEFAULT_FONT);
        setForeground(Manager.TEXT_COLOR);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setVisible(false);
    }
}
