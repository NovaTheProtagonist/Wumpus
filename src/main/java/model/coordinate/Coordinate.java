package model.coordinate;

import java.util.Objects;


public class Coordinate {
    private final char column;
    private final int row;

    public Coordinate(char column, int row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
