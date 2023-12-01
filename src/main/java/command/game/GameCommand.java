package command.game;

import command.Command;
import service.game.GameService;
import view.View;

public abstract class GameCommand implements Command {
    protected final View view;

    protected final GameService gameService;

    public GameCommand(View view, GameService gameService) {
        this.view = view;
        this.gameService = gameService;
    }

    @Override
    public final void execute() {
        runBeforePrint();
        view.printHero(gameService.getHero());
        view.printBoard(gameService.getBoard());
        runAfterPrint();
    }

    protected abstract void runBeforePrint();

    protected void runAfterPrint(){}
}
