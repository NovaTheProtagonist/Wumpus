package service.menu;

import java.util.Optional;

import model.board.Board;
import model.hero.Hero;

public interface MenuService {

    void readFile(String fileName);

    void loadFromDatabase(String playerName);

    void saveToDatabase(String playerName, Integer winCount);

    String cachePlayerName(String playerName);

    Optional<Board> getBoard();

    Optional<Hero> getHero();

}
