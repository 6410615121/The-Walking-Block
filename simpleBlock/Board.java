package simpleBlock;

public class Board {
    public final int ROW_SIZE;
    public final int COL_SIZE;

    private Cell[][] cells;

    public Board(int row_size, int col_size) {
        ROW_SIZE = row_size;
        COL_SIZE = col_size;

        cells = new Cell[row_size][col_size];
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int col = 0; col < COL_SIZE; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }

    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

}
