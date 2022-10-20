import Control.GameController;
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
            MenuView menu = new MenuView();
            WorldView world = new WorldView(state);
            ScoreBoardView scoreBoard = new ScoreBoardView(state);
            new GameController(state, menu, world, scoreBoard);

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

    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameGUI GUI = new GameGUI();
                GUI.setVisible(true);
            }
        });
    }
}


