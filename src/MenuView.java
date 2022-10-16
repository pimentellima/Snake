import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {

    State state;
    static final Color GAME_COLOR = new Color(43, 43, 44);
    static final Font TITLE_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 18);
    static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
    static final Color TEXT_COLOR = new Color(151, 117, 170);

    public MenuView(State state) {
        this.state = state;
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(GAME_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());
        graphics.setColor(TEXT_COLOR);
        graphics.setFont(TITLE_FONT);
        graphics.drawString("SNAKE GAME", getWidth()/2 - 75,(getHeight()/2) - 100);
        graphics.setFont(DEFAULT_FONT);
        graphics.drawString("PRESSIONE ENTER PARA INICIAR", getWidth()/2 - 100,(getHeight()/2));
    }
}
