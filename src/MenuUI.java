import javax.swing.*;
import java.awt.*;

public class MenuUI extends JLabel {

    State state;

    public MenuUI(State state) {
        super("Menu");
        this.state = state;
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Styles.GAME_COLOR);
        graphics.fillRect(0,0,getWidth(),getHeight());
        graphics.setColor(Styles.TEXT_COLOR);
        graphics.setFont(Styles.TITLE_FONT);
        graphics.drawString("SNAKE GAME", getWidth()/2 - 50,(getHeight()/2) - 100);
        graphics.setFont(Styles.DEFAULT_FONT);
        graphics.drawString("PRESSIONE ENTER PARA INICIAR", getWidth()/2 - 100,(getHeight()/2));
    }
}
