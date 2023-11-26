package command.menu;

import command.Command;
import app.GameState;
import service.menu.MenuService;
import view.View;

public class SavePlayerNameCommand implements Command {
    private final MenuService menuService;

    private final View view;

    public SavePlayerNameCommand(MenuService menuService, View view) {
        this.menuService = menuService;
        this.view = view;
    }

    @Override
    public void execute() {
        String playerName = view.requestPlayerName();
        menuService.cachePlayerName(playerName);
    }
}
