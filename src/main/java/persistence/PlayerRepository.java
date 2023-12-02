package persistence;

import java.util.List;

public interface PlayerRepository {
    void addNewPlayer(String playerName);

    void updatePlayerScore(String playerName);

    List<PlayerResult> selectAll();
}
