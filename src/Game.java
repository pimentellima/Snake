import javax.swing.*;

public class Game extends JFrame {

    public Game () {
        super("Snake");
        State state = new State();
        Canvas canvas = new Canvas(state);
        ScoreView scoreView = new ScoreView(state);
        Update update = new Update(state, canvas, scoreView);
        canvas.setSize(360,400);
        scoreView.setSize(360, 100);
        add(canvas);
        add(scoreView);

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                update.changeDirection(e);
            }
        });

        setSize(360, 500);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }

    public static void main (String [] args) {
        Game game = new Game();
    }

}

