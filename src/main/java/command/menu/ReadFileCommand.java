package command.menu;

import java.util.Optional;

import command.Command;
import command.GameStateBinding;
import model.board.Board;
import service.menu.MenuService;
import view.View;


public class ReadFileCommand implements Command {
    private final MenuService menuService;
    private final View view;

    public ReadFileCommand(MenuService menuService, View view) {
        this.menuService = menuService;
        this.view = view;
    }

    @Override
    public void execute() {
        String fileName = view.requestFileName();
        Optional<Board> board = menuService.readFile(fileName);
        view.printBoard(board);
    }

    @Override
    public GameStateBinding getGameStateBinding() {
        return GameStateBinding.MENU_STATE;
    }
}
