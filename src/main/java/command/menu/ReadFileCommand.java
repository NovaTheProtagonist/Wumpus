package command.menu;

import java.util.Optional;

import command.Command;
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
        menuService.readFile(fileName);
        Optional<Board> board = menuService.getBoard();
        if (board.isPresent()) {
            view.printBoard(board.get());
        } else {
            view.printError("Unable to read file");
        }
    }
}
