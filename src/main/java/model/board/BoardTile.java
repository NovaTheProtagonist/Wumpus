package model.board;

public class BoardTile {
    private TileType type = TileType.EMPTY;
    private boolean visited;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if (!visited)
            return TileType.UNKNOWN.toString();

        return type.toString();
    }
}

