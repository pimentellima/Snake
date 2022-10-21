import javax.swing.*;

public class SnakeGame {

    private static void windowInit() {
        JFrame window = new JFrame("Snake Game");
        Container gameContainer = new Container();
        window.add(gameContainer);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setSize(614, 590);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                windowInit();
            }
        });
    }
}


