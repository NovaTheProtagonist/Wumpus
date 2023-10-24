package model.board;

public class Board {
    private final BoardTile[][] boardTiles;

    public Board(int mapSize) {
        boardTiles = new BoardTile[mapSize][mapSize];
    }

    public BoardTile getBoardTile(int firstDimension, int secondDimension) {
        return boardTiles[firstDimension][secondDimension];
    }
}
