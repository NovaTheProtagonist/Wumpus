package command.game;

import service.game.GameService;
import view.View;

public class MoveCommand extends GameCommand{
    public MoveCommand(View view, GameService gameService) {
        super(view, gameService);
    }

    @Override
    protected void runBeforePrint() {

    }
}
