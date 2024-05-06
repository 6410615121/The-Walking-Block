package simpleBlock;

public class Cell {
    private int rol;
    private int col;
    private String color;

    public Cell(int rol, int col, String color) {
        this.rol = rol;
        this.col = col;
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
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
