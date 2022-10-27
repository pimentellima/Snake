import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.awt.event.KeyEvent.*;

interface Listener {
    void gameOver();
    void gameWon();
}

public class Canvas extends JPanel implements Listener {

    private final JLabel menu;
    private final JLabel gameEnd;
    private final Scoreboard scoreBoard;
    private final World world;

    public static final Color TEXT_COLOR = new Color(151, 117, 170);
    public static final Color GAME_COLOR = new Color(60, 63, 65);
    public static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 24);

    public Canvas() {
        menu = new JLabel();
        gameEnd = new JLabel();
        scoreBoard  = new Scoreboard();
        world = new World(scoreBoard);
        world.addStateListener(this);

        menu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_ENTER, 0), "Enter");
        menu.getActionMap().put("Enter", new StartAction());
        gameEnd.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_ENTER, 0), "Enter");
        gameEnd.getActionMap().put("Enter", new RestartAction());

        menu.setText("<html><h1><center>PRESSIONE ENTER PARA INICIAR<br>" +
                "USE AS SETAS DIRECIONAIS PARA SE MOVER");
        menu.setForeground(TEXT_COLOR);
        menu.setHorizontalAlignment(SwingConstants.CENTER);
        menu.setVisible(true);
        gameEnd.setFont(DEFAULT_FONT);
        gameEnd.setForeground(TEXT_COLOR);
        gameEnd.setVisible(false);
        world.add(gameEnd);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel board = new JPanel();
        board.setLayout(new CardLayout());
        board.setBackground(GAME_COLOR);
        board.setPreferredSize(new Dimension(600, 450));
        board.add("menu", menu);
        board.add("world", world);
        add(board);
        add(scoreBoard);
    }

    @Override
    public void gameOver() {
        gameEnd.setText("<html><center>PERDEU");
        gameEnd.setVisible(true);
    }

    @Override
    public void gameWon() {
        gameEnd.setText("<html><center>VENCEU");
        gameEnd.setVisible(true);
    }

    private class StartAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            scoreBoard.setInitialState();
            gameEnd.setVisible(false);
            world.initWorld();
            world.setVisible(true);
            menu.setVisible(false);
        }
    }

    private class RestartAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            scoreBoard.setInitialState();
            world.initWorld();
            world.setVisible(false);
            gameEnd.setVisible(false);
            menu.setVisible(true);
        }
    }
}

