package app;

import java.util.Map;

import command.Command;
import service.menu.MenuService;
import view.View;



public class WumpusGame {

    private GameState gameState;

    private View view;

    private Map<String, Command> commandMap;

    public WumpusGame() {
        this.gameState = GameState.MENU_STATE;
    }


    public void start() {
        view.printStartMessage();
        Command playerNameCommand = commandMap.get("change name");
        playerNameCommand.execute();
        startGameCycle();
    }

    private void startGameCycle() {
        while (gameState != GameState.EXIT_STATE) {
            switch (gameState) {
                case MENU_STATE -> {
                    String input = view.requestMenuCommand(commandMap.keySet().stream().toList());
                    runCommand(input, commandMap);
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
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setCommandMap(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }
}

