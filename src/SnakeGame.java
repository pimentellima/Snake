import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class SnakeGame {
    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameGUI GUI = new GameGUI();
                GUI.setVisible(true);
            }
        });
    }
}

class GameGUI extends JFrame {

    public static final Color SNAKE_COLOR = new Color(103, 133, 88, 255);
    public static final Color SNAKE_COLOR_TP = new Color(103, 133, 88, 100);
    public static final Color FRUIT_COLOR = new Color(253, 152, 67, 255);
    public static final Color FRUIT_COLOR_TP = new Color(253, 152, 67, 100);
    public static final Color TEXT_COLOR = new Color(151, 117, 170);
    public static final Color SCOREBOARD_COLOR = new Color(43, 43, 44);
    public static final Color GAME_COLOR = new Color(60, 63, 65);
    public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    public static final Font TITLE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 42);
    public static final int POINT_WIDTH = 30;
    public static final int POINT_HEIGHT = 30;

    public GameGUI() {
        Menu menu = new Menu();
        ScoreBoard scoreBoard = new ScoreBoard();
        World world = new World(scoreBoard);
        menu.setVisible(true);
        world.setVisible(false);
        scoreBoard.setVisible(true);

        InputMap menuInputMap = menu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        menuInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");

        menu.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                world.setVisible(true);
                world.start();
            }
        });

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new CardLayout());
        centerContainer.add("menu", menu);
        centerContainer.add("world", world);
        centerContainer.setMaximumSize(new Dimension(600, 450));
        scoreBoard.setMaximumSize(new Dimension(600, 100));
        mainContainer.add(centerContainer);
        mainContainer.add(scoreBoard);
        add(mainContainer);
        mainContainer.setVisible(true);
        setSize(614, 590);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


