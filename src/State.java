import java.util.ArrayList;

public class State {

    ArrayList<Dot> dots;
    Dot blackDot;
    char direction;
    int record;
    int score;
    String screen;

    public State() {
        ArrayList<Dot> dots = new ArrayList<>();
        dots.add(new Dot(160,120));
        dots.add(new Dot(140,120));
        dots.add(new Dot(120,120));

        this.dots = dots;
        blackDot = new Dot(100, 240);
        direction = 'd';
        record = 0;
        score = 0;
        screen = "initial-screen";
    }

    public void reset() {
        if(score > record) { record = score; }

        ArrayList<Dot> dots = new ArrayList<>();
        dots.add(new Dot(160,120));
        dots.add(new Dot(140,120));
        dots.add(new Dot(120,120));

        this.dots = dots;
        blackDot = new Dot(100, 240);
        screen = "in-game-screen";
        score = 0;
        direction = 'd';
    }

}
