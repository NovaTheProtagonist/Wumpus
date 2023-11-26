package app;

import java.util.Map;

import command.Command;
import command.CommandType;
import command.menu.ExitCommand;
import command.menu.ReadFileCommand;
import command.menu.SavePlayerNameCommand;
import service.menu.MenuService;
import service.menu.MenuServiceImpl;
import view.View;
import view.ViewImpl;

public class App {
    private static final MenuService menuService = new MenuServiceImpl();
    private static final View view = new ViewImpl();

    private static final WumpusGame wumpusGame = new WumpusGame();

    public static void main(String[] args) {
        Map<CommandType, Map<String, Command>> commandMap = assembleCommandMap();
        wumpusGame.setView(view);
        wumpusGame.setCommandMap(commandMap);
        wumpusGame.start();
    }

    private static Map<CommandType, Map<String, Command>> assembleCommandMap() {
        Map<String, Command> menuCommands = Map.of(
                "change name", new SavePlayerNameCommand(menuService, view),
                "read from file", new ReadFileCommand(menuService, view),
                "exit", new ExitCommand(wumpusGame)
        );

        Map<String, Command> gameCommands = Map.of();
        return Map.of(
                CommandType.MENU, menuCommands,
                CommandType.GAME, gameCommands
        );
    }

}
