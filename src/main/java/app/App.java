package app;

import java.util.Map;

import command.Command;
import command.CommandType;
import command.game.MoveCommand;
import command.game.RotateCommand;
import command.game.ShootCommand;
import command.game.SurrenderCommand;
import command.menu.ExitCommand;
import command.menu.PlayCommand;
import command.menu.ReadFileCommand;
import command.menu.SavePlayerNameCommand;
import persistance.PlayerRepository;
import persistance.PlayerRepositoryImpl;
import service.game.GameService;
import service.game.GameServiceImpl;
import service.menu.MenuService;
import service.menu.MenuServiceImpl;
import view.View;
import view.ViewImpl;

public class App {

    private static final PlayerRepository playerRepository = new PlayerRepositoryImpl("jdbc:sqlite:C://database/kukac.db");

    private static final MenuService menuService = new MenuServiceImpl(playerRepository);

    private static final GameService gameService = new GameServiceImpl(playerRepository);

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
                "exit", new ExitCommand(wumpusGame),
                "play", new PlayCommand(view, wumpusGame, menuService, gameService)
        );

        Map<String, Command> gameCommands = Map.of(
                "surrender", new SurrenderCommand(view, wumpusGame, gameService),
                "move", new MoveCommand(view, gameService, wumpusGame),
                "rotate", new RotateCommand(view, gameService),
                "shoot", new ShootCommand(view, gameService)

        );
        return Map.of(
                CommandType.MENU, menuCommands,
                CommandType.GAME, gameCommands
        );
    }

}
