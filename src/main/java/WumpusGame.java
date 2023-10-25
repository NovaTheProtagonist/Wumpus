import view.View;

public class WumpusGame {
    private String playerName;

    private final View view;

    public WumpusGame(View view) {
        this.view = view;
    }

    public void start() {
        view.printStartMessage();
        playerName = view.requestPlayerName();

    }

}
