import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        super("Snake game");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        State state = new State();
        WorldUI world = new WorldUI(state);
        MenuUI menu = new MenuUI(state);
        ScoreBoardUI scoreBoard = new ScoreBoardUI(state);
        mainPanel.add("menu", menu);
        mainPanel.add("world", world);
        Update update = new Update(state, menu, world, scoreBoard);
        mainPanel.setSize(360, 400);
        scoreBoard.setSize(360, 100);
        add(mainPanel);
        add(scoreBoard);
        world.setVisible(false);
        menu.setVisible(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                update.handleKeyPress(e);
            }
        });
        setSize(360, 500);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

    }

    public static void main (String [] args) {
        SnakeGame snakeGame = new SnakeGame();
    }

}

