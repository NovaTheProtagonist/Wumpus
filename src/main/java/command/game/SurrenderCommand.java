package command.game;

import app.GameState;
import app.WumpusGame;
import command.Command;
import service.game.GameService;
import view.View;

public class SurrenderCommand implements Command {

    private final View view;

    private final WumpusGame wumpusGame;

    private final GameService gameService;

    public SurrenderCommand(View view, WumpusGame wumpusGame, GameService gameService) {
        this.view = view;
        this.wumpusGame = wumpusGame;
        this.gameService = gameService;
    }

    @Override
    public void execute() {
        gameService.surrender();
        view.printLose();
        wumpusGame.setGameState(GameState.MENU_STATE);
    }
}
