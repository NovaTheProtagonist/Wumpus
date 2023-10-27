package command.menu;

import command.Command;
import command.GameStateBinding;
import service.menu.MenuService;

public class ExitCommand implements Command {
    private final MenuService menuService;

    public ExitCommand(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public void execute() {
        menuService.exit();
    }

    @Override
    public GameStateBinding getGameStateBinding() {
        return GameStateBinding.MENU_STATE;
    }
}
