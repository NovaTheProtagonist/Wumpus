package model.board;

public enum TileType {
    WUMPUS('U'),
    WALL('W'),
    GOLD('G'),
    PIT('P'),
    EMPTY('_');

    private final char shortName;

    public char getShortName() {
        return shortName;
    }

    TileType(char shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return String.valueOf(shortName);
    }
}
