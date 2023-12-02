package command.menu;

import app.GameState;
import app.WumpusGame;
import command.Command;

public class ExitCommand implements Command {
    private final WumpusGame wumpusGame;

    public ExitCommand(WumpusGame wumpusGame) {
        this.wumpusGame = wumpusGame;
    }

    @Override
    public void execute() {
        wumpusGame.setGameState(GameState.EXIT_STATE);
    }
}
