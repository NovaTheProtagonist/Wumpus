package command.menu;

import java.util.Optional;

import app.GameState;
import app.WumpusGame;
import command.Command;
import model.board.Board;
import model.hero.Hero;
import service.game.GameService;
import service.menu.MenuService;
import view.View;



public class PlayCommand implements Command {

    private final View view;

    private final WumpusGame wumpusGame;

    private final MenuService menuService;

    private final GameService gameService;

    public PlayCommand(View view, WumpusGame wumpusGame, MenuService menuService, GameService gameService) {
        this.view = view;
        this.wumpusGame = wumpusGame;
        this.menuService = menuService;
        this.gameService = gameService;
    }

    @Override
    public void execute() {
        Optional<Board> board = menuService.getBoard();
        Optional<Hero> hero = menuService.getHero();
        if (board.isEmpty() || hero.isEmpty()) {
            view.printError("You haven't read a game file yet");
            return;
        }
        Board gameBoard = board.get();
        gameService.setBoard(gameBoard);
        Hero gameHero = hero.get();
        gameService.setHero(gameHero);
        gameService.startGame();
        view.printInstructions();
        view.printHero(gameHero);
        view.printBoard(gameBoard);
        wumpusGame.setGameState(GameState.GAME_STATE);
    }
}
