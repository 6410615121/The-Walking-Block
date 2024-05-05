// import java.util.LinkedList;
// import java.util.Queue;

public class Snake {
    public int length; // Length of a snake
    // Queue<int[][]> passed;
    public int[] current; // [x,y] coordinate of head of a snake 

    public Snake(int[] current){
        this.current = current;
        length = 1;
        // passed = new LinkedList<>();
    }

    public void eat(Apple a){
        length += 1;
        System.out.printf("length : %s", length);
    }

    public void move(String direction){
        int current_x = current[0];
        int current_y = current[1];
        if (direction.equalsIgnoreCase("up")){
            current = new int[]{current_x, ++current_y};
            
            System.out.printf("x,y : %s,%s\n", current[0], current[1]);
        }
    }
    
}