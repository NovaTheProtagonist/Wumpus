package service.menu;

import model.board.Board;

public interface MenuService {
    void startGame();

    void exit();

    Board readFile(String fileName);

    void loadFromDatabase(String playerName);

    void saveToDatabase(String playerName, Integer winCount);

    void cachePlayerName(String playerName);

}
