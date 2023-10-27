package app;

import command.Command;
import command.menu.ExitCommand;
import command.menu.ReadFileCommand;
import service.menu.MenuService;
import service.menu.MenuServiceImpl;
import view.View;
import view.ViewImpl;

import java.util.Map;

public class App {
    private final static MenuService menuService = new MenuServiceImpl();
    private final static View view = new ViewImpl();

    public static void main(String[] args) {
        Map<String, Command> commandMap = assembleCommandMap();
        WumpusGame game = new WumpusGame(view, commandMap);
        game.start();
    }

    private static Map<String, Command> assembleCommandMap() {
        return Map.of(
                "exit", new ExitCommand(menuService),
                "read from file", new ReadFileCommand(menuService,view)
        );
    }


}
