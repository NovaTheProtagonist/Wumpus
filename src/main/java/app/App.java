package app;

import java.util.Map;

import command.Command;
import command.menu.ExitCommand;
import command.menu.ReadFileCommand;
import service.menu.MenuService;
import service.menu.MenuServiceImpl;
import view.View;
import view.ViewImpl;

public class App {
    private static final MenuService menuService = new MenuServiceImpl();
    private static final View view = new ViewImpl();

    public static void main(String[] args) {
        Map<String, Command> commandMap = assembleCommandMap();
        WumpusGame game = new WumpusGame(view, commandMap);
        game.start();
    }

    private static Map<String, Command> assembleCommandMap() {
        return Map.of(
                "exit", new ExitCommand(menuService),
                "read from file", new ReadFileCommand(menuService, view)
        );
    }


}
