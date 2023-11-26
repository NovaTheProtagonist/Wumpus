package service.menu;

import java.util.Optional;

import model.board.Board;

public interface MenuService {
    void startGame();

    Optional<Board> readFile(String fileName);

    void loadFromDatabase(String playerName);

    void saveToDatabase(String playerName, Integer winCount);

    String cachePlayerName(String playerName);

}
