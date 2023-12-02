package service.menu;

import java.util.List;
import java.util.Optional;

import model.board.Board;
import model.hero.Hero;
import persistence.PlayerResult;

public interface MenuService {

    void readFile(String fileName);

    void loadFromDatabase(String playerName);

    void saveToDatabase(String playerName, Integer winCount);

    String cachePlayerName(String playerName);

    Optional<Board> getBoard();

    Optional<Hero> getHero();

    List<PlayerResult> getTopList();

}
