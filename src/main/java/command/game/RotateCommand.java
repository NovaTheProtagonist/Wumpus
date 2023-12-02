package command.game;

import java.util.Optional;

import model.hero.Rotation;
import service.game.GameService;
import view.View;

public class RotateCommand extends GameCommand {

    public RotateCommand(View view, GameService gameService) {
        super(view, gameService);
    }

    @Override
    protected void runBeforePrint() {
        Optional<Rotation> rotation = view.requestRotation();
        if (rotation.isEmpty()) {
            view.printError("Invalid rotation");
            return;
        }
        gameService.rotatePlayer(rotation.get());
    }
}
