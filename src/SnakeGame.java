import javax.swing.*;

public class SnakeGame {

    private static void initWindow() {
        JFrame window = new JFrame("Snake Game");
        Board GUI = new Board();
        window.add(GUI);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(614, 590);
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initWindow();
            }
        });
    }
}

