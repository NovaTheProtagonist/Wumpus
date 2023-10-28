package app;

import java.util.Map;

import command.Command;
import command.GameStateBinding;
import command.menu.SavePlayerNameCommand;
import service.menu.MenuService;
import view.View;



public class WumpusGame {

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
        Command playerNameCommand = commandMap.get("change name");
        playerNameCommand.execute();
        startGameCycle();
    }

    private void startGameCycle() {
        while (gameState == GameStateBinding.MENU_STATE) {
            String input = view.requestMenuCommand(commandMap.keySet().stream().toList());
            if(!commandMap.containsKey(input)){
                view.printCommandNotFound();
            }
            else{
                Command command = commandMap.get(input);
                command.execute();
            }
        }
    }

}
