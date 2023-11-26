package command.menu;

import app.WumpusGame;
import command.Command;
import app.GameState;
import service.menu.MenuService;

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
