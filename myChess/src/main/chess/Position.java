package chess;

public class Position implements ChessPosition {

    private Integer row, column;

    public Position(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public Integer getRow() {
        return row;
    }

    @Override
    public Integer getColumn() {
        return column;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var position = (Position) o;
        return (row == position.row && column == position.column);
    }

    @Override
    public int hashCode() {
        return 31 * row * column;
    }


    @Override
    public String toString() {
        return String.format("%d%d", row, column);
    }
}