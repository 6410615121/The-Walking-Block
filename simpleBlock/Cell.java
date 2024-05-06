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
    
}
