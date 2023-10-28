package service.menu;

import model.board.Board;

import java.util.Optional;
import java.util.OptionalInt;

public interface MenuService {
    void startGame();

    void exit();

    Optional<Board> readFile(String fileName);

    void loadFromDatabase(String playerName);

    void saveToDatabase(String playerName, Integer winCount);

    String cachePlayerName(String playerName);

}
