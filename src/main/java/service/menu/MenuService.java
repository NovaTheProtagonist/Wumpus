package service.menu;

public interface MenuService {
    void startGame();

    void exit();

    void readFile(String fileName);

    void loadFromDatabase(String playerName);

    void saveToDatabase(String playerName, Integer winCount);

}
