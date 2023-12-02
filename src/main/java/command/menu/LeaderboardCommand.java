package command.menu;

import command.Command;
import persistence.PlayerResult;
import service.menu.MenuService;
import view.View;

import java.util.List;

public class LeaderboardCommand implements Command {

    private final View view;

    private final MenuService menuService;

    public LeaderboardCommand(View view, MenuService menuService) {
        this.view = view;
        this.menuService = menuService;
    }

    @Override
    public void execute() {
        List<PlayerResult> playerResults = menuService.getTopList();
        view.printTopList(playerResults);
    }
}
