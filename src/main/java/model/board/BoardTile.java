package model.board;

import java.util.Arrays;

public class BoardTile {
    private TileType type = TileType.EMPTY;

    public BoardTile(char typeID){
        this.type = Arrays.stream(TileType.values())
                .filter((tileType -> tileType.getShortName() == typeID))
                .findAny()
                .orElseThrow();
    }

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

