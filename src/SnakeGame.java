import Controller.GameController;
import Model.State;
import View.MenuView;
import View.ScoreBoardView;
import View.WorldView;
import javax.swing.*;
import java.awt.*;

public class SnakeGame {

    static class GameGUI extends JFrame {

        public GameGUI() {
            super("Snake game");

            State state = new State();

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new CardLayout());
            mainPanel.setSize(360, 400);
            add(mainPanel);

            WorldView world = new WorldView(state);
            MenuView menu = new MenuView(state);
            mainPanel.add("menu", menu);
            mainPanel.add("world", world);

            ScoreBoardView scoreBoard = new ScoreBoardView(state);
            scoreBoard.setSize(360, 100);
            add(scoreBoard);

            new GameController(state, menu, world);

            world.setVisible(false);
            menu.setVisible(true);
            scoreBoard.setVisible(true);

            setSize(360, 500);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        }
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameGUI GUI = new GameGUI();
                GUI.setVisible(true);
            }
        });
    }
}


