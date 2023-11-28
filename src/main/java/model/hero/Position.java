package model.hero;

import model.coordinate.Coordinate;

public class Position {
    private int column;

    private int row;

    public Position(Coordinate coordinate) {
        row = coordinate.getRow() - 1;
        column = (int) coordinate.getColumn() - (int) 'a';
    }

    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
