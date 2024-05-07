package simpleBlock;

import java.io.Serializable;

public class Cell implements Serializable{
    private int row;
    private int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cell otherCell = (Cell) obj;
        return row == otherCell.row && col == otherCell.col;
    }

    @Override
    public String toString() {
        return String.format("row: %d, col: %d", row, col);
    }
    
}
