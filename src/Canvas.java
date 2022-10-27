import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

interface Listener {
    void onGameOver();
    void onGameWon();
    void onScoreIncrease();
}

public class Canvas extends JPanel {

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
        world = new World();

        menu.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Enter");
        menu.getActionMap().put("Enter", new StartAction());
        menu.setText("<html><h1><center>PRESSIONE ENTER PARA INICIAR<br>USE AS SETAS DIRECIONAIS PARA SE MOVER");
        menu.setForeground(TEXT_COLOR);
        menu.setHorizontalAlignment(SwingConstants.CENTER);
        menu.setVisible(true);

        gameEnd.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Enter");
        gameEnd.getActionMap().put("Enter", new RestartAction());
        gameEnd.setFont(DEFAULT_FONT);
        gameEnd.setForeground(TEXT_COLOR);

        world.addStateListener(new WorldListener());
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

    private class WorldListener implements Listener {
        @Override
        public void onGameOver() {
            gameEnd.setText("<html><center>PERDEU");
            gameEnd.setVisible(true);
        }

        @Override
        public void onGameWon() {
            gameEnd.setText("<html><center>VENCEU");
            gameEnd.setVisible(true);
        }

        @Override
        public void onScoreIncrease() {
            scoreBoard.increaseScore();
        }
    }

    private class StartAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            world.reset();
            world.setVisible(true);
            gameEnd.setVisible(false);
            menu.setVisible(false);
        }
    }

    private class RestartAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            scoreBoard.reset();
            world.setVisible(false);
            gameEnd.setVisible(false);
            menu.setVisible(true);
        }
    }
}

