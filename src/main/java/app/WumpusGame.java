package app;

import java.util.Map;

import command.Command;
import command.CommandType;
import view.View;

public class WumpusGame {

    private GameState gameState;

    private View view;

    private Map<CommandType, Map<String, Command>> commandMap;

    public WumpusGame() {
        this.gameState = GameState.MENU_STATE;
    }


    public void start() {
        view.printStartMessage();
        Command playerNameCommand = getMenuCommands().get("change name");
        playerNameCommand.execute();
        startGameCycle();
    }


    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setCommandMap(Map<CommandType, Map<String, Command>> commandMap) {
        this.commandMap = commandMap;
    }

    private void startGameCycle() {
        while (gameState != GameState.EXIT_STATE) {
            switch (gameState) {
                case MENU_STATE -> {
                    String input = view.requestMenuCommand(getMenuCommands().keySet().stream().toList());
                    runCommand(input, getMenuCommands());
                }
                case GAME_STATE -> {
                    String input = view.requestGameCommand(getGameCommands().keySet().stream().toList());
                    runCommand(input, getGameCommands());
                }
                default -> {

                }
            }
        }
    }

    private void runCommand(String input, Map<String, Command> commandMap) {
        if (!commandMap.containsKey(input)) {
            view.printCommandNotFound();
        } else {
            Command command = commandMap.get(input);
            command.execute();
        }
    }

    private Map<String, Command> getMenuCommands() {
        return commandMap.get(CommandType.MENU);
    }

    private Map<String, Command> getGameCommands() {
        return commandMap.get(CommandType.GAME);
    }
}

