package app;

import java.util.Map;

import command.Command;
import service.menu.MenuService;
import view.View;



public class WumpusGame {

    private final View view;

    private final MenuService menuService;

    private final Map<String, Command> commandMap;

    public WumpusGame(View view, MenuService menuService, Map<String, Command> commandMap) {
        this.view = view;
        this.menuService = menuService;
        this.commandMap = commandMap;
    }

    public void start() {
        view.printStartMessage();
        Command playerNameCommand = commandMap.get("change name");
        playerNameCommand.execute();
        startGameCycle();
    }

    private void startGameCycle() {
        while (menuService.shouldRun()) {
            String input = view.requestMenuCommand(commandMap.keySet().stream().toList());
            if (!commandMap.containsKey(input)) {
                view.printCommandNotFound();
            } else {
                Command command = commandMap.get(input);
                command.execute();
            }
        }
    }

}
