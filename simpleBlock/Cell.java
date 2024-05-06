package simpleBlock;

import java.io.Serializable;

public class Cell implements Serializable{
    private int rol;
    private int col;

    public Cell(int rol, int col) {
        this.rol = rol;
        this.col = col;
    }
    
    public int getRol() {
        return rol;
    }
    public void setRol(int rol) {
        this.rol = rol;
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
        System.out.printf("cell.rol: %d\n", cell.rol);
        System.out.printf("cell.col: %d\n", cell.col);
        if(rol == cell.rol && col == cell.col){
            System.out.println("cell equal");
            return true;
            
        }else{
            System.out.println("cell not equal");
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("row: %d, col: %d", rol, col);
    }
    
}
