package command.game;

import command.Command;
import service.game.GameService;
import view.View;

public class RotateCommand extends GameCommand {

    public RotateCommand(View view, GameService gameService) {
        super(view, gameService);
    }

    @Override
    protected void runBeforePrint() {

    }
}
