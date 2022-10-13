import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Update implements ActionListener {

    State state;
    Canvas canvas;
    ScoreView scoreView;
    private final Timer movement_delay;
    private final Timer kb_delay;

    public Update(State state, Canvas canvas, ScoreView scoreView) {
        this.state = state;
        this.canvas = canvas;
        this.scoreView = scoreView;
        movement_delay = new Timer (100, this);
        kb_delay = new Timer(100, this);
        movement_delay.start();
    }

    public void changeDirection(KeyEvent e) {
        int key = e.getKeyCode();
        if(!state.screen.equals("in-game-screen")) {
            if(key == KeyEvent.VK_ENTER) {
                state.reset();
            }
        }
        if(state.screen.equals("in-game-screen")){
            char direction = state.direction;
            if (key == KeyEvent.VK_RIGHT && (direction == 'u' || direction == 'd')) {
                direction = 'r';
            }
            if (key == KeyEvent.VK_LEFT && (direction == 'u' || direction == 'd')) {
                direction = 'l';
            }
            if (key == KeyEvent.VK_UP && (direction == 'r' || direction == 'l')) {
                direction = 'u';
            }
            if (key == KeyEvent.VK_DOWN && (direction == 'r' || direction == 'l')) {
                direction = 'd';
            }
            state.direction = direction;

            if (!kb_delay.isRunning()) {
                move();
                kb_delay.restart();
            }
        }
    }

    public void move() {
        int nextPX = 0;
        int nextPY = 0;
        Dot head = state.dots.get(0);

        for(Dot dot: state.dots) {
            int currentPX;
            int currentPY;
            if(dot.equals(head)) {
                nextPX = dot.px;
                nextPY = dot.py;
                if(state.direction == 'r') { dot.px += 20; }
                if(state.direction == 'l') { dot.px -= 20; }
                if(state.direction == 'u') { dot.py -= 20; }
                if(state.direction == 'd') { dot.py += 20; }
            }
            else {
                currentPX = dot.px;
                currentPY = dot.py;
                dot.px = nextPX;
                dot.py = nextPY;
                nextPX = currentPX;
                nextPY = currentPY;
            }
        }

        // checa interseccao com o ponto
        if(head.px == state.blackDot.px && head.py == state.blackDot.py) {
            state.score ++;
            boolean different = false;
            int randomPX = (int) (Math.random() * canvas.getWidth()/22) * 20;
            int randomPY = (int) (Math.random() * canvas.getHeight()/22) * 20;
            while(!different) {
                for (Dot d : state.dots) {
                    if (randomPX == d.px && randomPY == d.py) {
                        break;
                    }
                    different = true;
                    break;
                }
                randomPX = (int) (Math.random() * canvas.getWidth()/22) * 20;
                randomPY = (int) (Math.random() * canvas.getHeight()/22) * 20;
            }

            state.blackDot.px = randomPX;
            state.blackDot.py = randomPY;
            state.dots.add(new Dot(nextPX, nextPY));
        }

        if(head.px == canvas.getWidth() - 20 || head.px < 0 || head.py == canvas.getHeight() || head.py < 0) {
            state.screen = "game-over-screen";
        }

        // checa colisao com cauda
        for(int i=1; i<state.dots.size(); i++) {
            Dot dot = state.dots.get(i);
            if (dot.px == head.px && dot.py == head.py) {
                state.screen = "game-over-screen";
                break;
            }
        }
        canvas.repaint();
        scoreView.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(state.screen.equals("in-game-screen")) {
            if (e.getSource() == movement_delay) {
                move();
            }
            if (e.getSource() == kb_delay) {
                kb_delay.stop();
            }
        }
    }
}
