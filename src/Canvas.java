import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

interface Listener {
    void onGameLost();
    void onGameWon();
    void onScoreIncrease();
}

public class Canvas extends JPanel implements KeyListener {

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

        setFocusable(true);
        addKeyListener(this);

        world.addListener(new WorldListener());
        world.add(gameEnd);

        menu.setText("<html><h1><center>PRESSIONE ENTER PARA INICIAR<br>USE AS SETAS DIRECIONAIS PARA SE MOVER");
        menu.setForeground(TEXT_COLOR);
        menu.setHorizontalAlignment(SwingConstants.CENTER);
        menu.setVisible(true);

        gameEnd.setFont(DEFAULT_FONT);
        gameEnd.setForeground(TEXT_COLOR);

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
        public void onGameLost() {
            gameEnd.setText("<html><center>PERDEU<br><br>PRESSIONE ENTER PARA VOLTAR AO MENU</html>");
            requestFocusInWindow();
        }

        @Override
        public void onGameWon() {
            gameEnd.setText("<html><center>VENCEU<br><br>PRESSIONE ENTER PARA VOLTAR AO MENU</html>");
            gameEnd.setVisible(true);
            requestFocusInWindow();
        }

        @Override
        public void onScoreIncrease() {
            scoreBoard.increaseScore();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER && menu.isVisible())  {
            world.reset();
            menu.setVisible(false);
            world.setVisible(true);
            world.requestFocusInWindow();
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER && gameEnd.isVisible()) {
            scoreBoard.reset();
            gameEnd.setVisible(false);
            world.setVisible(false);
            menu.setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

