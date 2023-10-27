package model.board;

public class BoardTile {
    private TileType type = TileType.EMPTY;

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    @Override
    public String toString() {

        return type.toString();
    }
}

