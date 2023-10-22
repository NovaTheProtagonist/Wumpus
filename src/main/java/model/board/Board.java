package model.board;

public class Board {
    private final BoardTile[][] boardTiles;

    public Board(int mapSize) {
        boardTiles = new BoardTile[mapSize][mapSize];
        
    }


}
