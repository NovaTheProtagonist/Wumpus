package model.board;

import model.coordinate.Coordinate;
import model.hero.Hero;
import model.hero.Position;

public class Board {

    private final int size;

    private final BoardTile[][] boardTiles;



    public Board(int size) {
        this.size = size;
        boardTiles = new BoardTile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardTiles[i][j] = new BoardTile();
            }
        }

    }

    public void spawnHero(Hero hero) {
        Position heroPosition = hero.getPosition();
        BoardTile boardTile = getBoardTile(heroPosition);
        boardTile.setOccupied(true);
    }

    public int getSize() {
        return size;
    }

    public BoardTile getBoardTile(int firstDimension, int secondDimension) {
        return boardTiles[firstDimension][secondDimension];
    }

    public BoardTile getBoardTile(Position position) {
        return boardTiles[position.getRow()][position.getColumn()];
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
