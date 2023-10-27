package app;

import command.Command;
import command.GameStateBinding;
import view.View;

import java.util.Map;
import java.util.Scanner;

public class WumpusGame {
    private String playerName;
    private GameStateBinding gameState;

    private final View view;

    private final Map<String, Command> commandMap;

    public WumpusGame(View view, Map<String, Command> commandMap) {
        this.view = view;
        this.commandMap = commandMap;
        gameState = GameStateBinding.MENU_STATE;
    }

    public void start() {
        view.printStartMessage();
        playerName = view.requestPlayerName();
        startGameCycle();
    }

    private void startGameCycle() {
        while (gameState == GameStateBinding.MENU_STATE) {
            String input = view.requestMenuCommand(commandMap.keySet().stream().toList());
            Command command = commandMap.get(input);
            command.execute();
        }
    }

}
