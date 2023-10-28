package model.board;

import model.coordinate.Coordinate;

public class Board {

    private final int size;

    private final BoardTile[][] boardTiles;

    private final Coordinate playerStartCoordinate;

    public Board(int size, Coordinate playerStartCoordinate) {
        this.size = size;
        boardTiles = new BoardTile[size][size];
        this.playerStartCoordinate = playerStartCoordinate;
    }

    public int getSize() {
        return size;
    }

    public BoardTile getBoardTile(int firstDimension, int secondDimension) {
        return boardTiles[firstDimension][secondDimension];
    }

    public void setBoardTile(int firstDimension, int secondDimension, char typeID) {
        boardTiles[firstDimension][secondDimension] = new BoardTile(typeID);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                stringBuilder.append(boardTiles[i][j]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
