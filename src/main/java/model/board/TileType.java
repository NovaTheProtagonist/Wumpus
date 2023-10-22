package model.board;

public enum TileType {
    WUMPUS('U'),
    WALL('W'),
    GOLD('G'),
    PIT('P'),
    EMPTY('_'),
    UNKNOWN('?');

    private final char shortName;

    TileType(char shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return String.valueOf(shortName);
    }
}
