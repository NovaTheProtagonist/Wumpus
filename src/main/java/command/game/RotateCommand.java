package command.game;

import command.Command;
import model.hero.Rotation;
import service.game.GameService;
import view.View;

import java.util.Optional;

public class RotateCommand extends GameCommand {

    public RotateCommand(View view, GameService gameService) {
        super(view, gameService);
    }

    @Override
    protected void runBeforePrint() {
        Optional<Rotation> rotation = view.requestRotation();
        if (rotation.isEmpty()){
            view.printError("Invalid rotation");
            return;
        }
        gameService.rotatePlayer(rotation.get());
    }
}
