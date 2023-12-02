package model.hero;

import java.util.Objects;

import model.coordinate.Coordinate;

public class Position {
    private int column;

    private int row;

    public Position(Coordinate coordinate) {
        row = coordinate.getRow() - 1;
        column = (int) coordinate.getColumn() - (int) 'A';
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

    public Position nextInDirection(FacingDirection facingDirection) {
        switch (facingDirection) {
            case NORTH -> {
                return new Position(column, row - 1);
            }

            case EAST -> {
                return new Position(column + 1, row);
            }
            case WEST -> {
                return new Position(column - 1, row);
            }
            case SOUTH -> {
                return new Position(column, row + 1);
            }
            default -> {
                return this;
            }
        }
    }

    @Override
    public String toString() {
        return "Position{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
