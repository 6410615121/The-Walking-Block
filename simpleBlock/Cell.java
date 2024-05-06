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
        Cell cell = (Cell) obj;
        // System.out.printf("cell.rol: %d\n", cell.rol);
        // System.out.printf("cell.col: %d\n", cell.col);
        if(row == cell.row && col == cell.col){
            // System.out.println("cell equal");
            return true;
            
        }else{
            // System.out.println("cell not equal");
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("row: %d, col: %d", row, col);
    }
    
}
