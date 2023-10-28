package command;

public interface Command {
    void execute();

    GameStateBinding getGameStateBinding();
}
