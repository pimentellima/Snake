import controller.Manager;

import javax.swing.*;

public class SnakeGame {

    private static void init() {
        JFrame window = new JFrame("Snake Game");
        Manager manager = new Manager();
        window.add(manager);
        window.setResizable(false);
        window.setSize(614, 590);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                init();
            }
        });
    }
}


