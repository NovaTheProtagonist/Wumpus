import view.View;
import view.ViewImpl;

public class App {
    public static void main(String[] args) {
        View view = new ViewImpl();
        WumpusGame game = new WumpusGame(view);
        game.start();
    }
}
