// import java.util.LinkedList;
// import java.util.Queue;

public class Snake {
    public int length; // Length of a snake
    // Queue<int[][]> passed;
    public int current_x;
    public int current_y;

    public Snake(int current_x, int current_y){
        this.current_x = current_x;
        this.current_y = current_y;
        length = 1;
    }

    public void eat(Apple a){
        length += 1;
        System.out.printf("length : %s", length);
    }

    public void moveUp(){
        current_y -= 1;
        System.out.printf("x,y : %s,%s\n", current_x, current_y);
    }

    public void moveDown(){
        current_y += 1;
        System.out.printf("x,y : %s,%s\n", current_x, current_y);
    }

    public void moveLeft(){
        current_x -= 1;
        System.out.printf("x,y : %s,%s\n", current_x, current_y);
    }

    public void moveRight(){
        current_x += 1;
        System.out.printf("x,y : %s,%s\n", current_x, current_y);
    }
    
}