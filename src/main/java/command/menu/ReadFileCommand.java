package command.menu;

import command.Command;
import command.GameStateBinding;
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
    }

    @Override
    public GameStateBinding getGameStateBinding() {
        return GameStateBinding.MENU_STATE;
    }
}
