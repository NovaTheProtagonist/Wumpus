package command.game;

import service.game.GameService;
import view.View;

public class ShootCommand extends GameCommand {
    public ShootCommand(View view, GameService gameService) {
        super(view, gameService);
    }

    @Override
    protected void runBeforePrint() {
        gameService.shootArrow();

    }
}
