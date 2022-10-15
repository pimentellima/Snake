import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

public class Update implements ActionListener {

    State state;
    MenuUI menu;
    WorldUI world;
    ScoreBoardUI scoreBoard;

    private final Timer movement_delay;
    private final Timer kb_delay;

    public Update(State state, MenuUI menu, WorldUI world, ScoreBoardUI scoreBoard) {
        this.state = state;
        this.menu = menu;
        this.world = world;
        this.scoreBoard = scoreBoard;
        movement_delay = new Timer (100, this);
        movement_delay.start();
        kb_delay = new Timer(100, this);
    }

    public void handleKeyPress(KeyEvent e) {
        int key = e.getKeyCode();
        if(menu.isVisible() && key == VK_ENTER) {
            menu.setVisible(false);
            world.setVisible(true);
        }

        if(world.isVisible() && state.isGameOver() && key == VK_ENTER) {
            state.reset();
        }

        if (world.isVisible() && !state.isGameOver()) {
            state.getSnake().changeDirection(key);

            if (!kb_delay.isRunning()) {
                state.onMoveSnake();
                kb_delay.restart();
            }

            world.repaint();
            scoreBoard.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(world.isVisible() && !state.isGameOver()) {
            if (e.getSource() == movement_delay) {
                state.onMoveSnake();
                world.repaint();
                scoreBoard.repaint();
            }
            if (e.getSource() == kb_delay) {
                kb_delay.stop();
            }
        }
    }
}
