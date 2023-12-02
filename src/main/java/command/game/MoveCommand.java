package command.game;

import app.GameState;
import app.WumpusGame;
import service.game.GameService;
import view.View;

public class MoveCommand extends GameCommand {

    private final WumpusGame wumpusGame;

    public MoveCommand(View view, GameService gameService, WumpusGame wumpusGame) {
        super(view, gameService);

        this.wumpusGame = wumpusGame;
    }

    @Override
    protected void runBeforePrint() {
        gameService.movePlayer();
    }

    @Override
    protected void runAfterPrint() {
        switch (gameService.getGameStatus()) {
            case LOSE -> {
                view.printLose();
                switchToMenu();
            }
            case WIN -> {
                view.printWin();
                switchToMenu();
            }
            default -> {

            }
        }
    }

    private void switchToMenu() {
        gameService.clearBoard();
        wumpusGame.setGameState(GameState.MENU_STATE);
    }

}
