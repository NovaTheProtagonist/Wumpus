package persistence;

public class PlayerResult {
    private final String playerName;
    private final int winCount;

    public PlayerResult(String playerName, int winCount) {
        this.playerName = playerName;
        this.winCount = winCount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getWinCount() {
        return winCount;
    }
}
