// import java.util.LinkedList;
// import java.util.Queue;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    public int length; // Length of a snake
    // Queue<int[][]> passed;
    public int current_x;
    public int current_y;
    public String direction;

    public List<TrailPoint> trail;

    public Snake(int current_x, int current_y){
        this.current_x = current_x;
        this.current_y = current_y;
        this.direction = "right";
        length = 1;
        this.trail = new ArrayList<TrailPoint>();
    }

    public void eat(Apple a){
        length += 1;
        System.out.printf("length : %s", length);
    }

    public void moveUp(){
        trail.add(new TrailPoint(current_x, current_y));

        current_y -= 1;
        System.out.printf("x,y : %s,%s\n", current_x, current_y);
    }

    public void moveDown(){
        trail.add(new TrailPoint(current_x, current_y));

        current_y += 1;
        System.out.printf("x,y : %s,%s\n", current_x, current_y);
    }

    public void moveLeft(){
        trail.add(new TrailPoint(current_x, current_y));

        current_x -= 1;
        System.out.printf("x,y : %s,%s\n", current_x, current_y);
    }

    public void moveRight(){
        trail.add(new TrailPoint(current_x, current_y));

        current_x += 1;
        System.out.printf("x,y : %s,%s\n", current_x, current_y);
    }

    public void move(){
        if(direction.equals("up")){
            moveUp();
        }else if(direction.equals("down")){
            moveDown();
        }else if(direction.equals("left")){
            moveLeft();
        }else if(direction.equals("right")){
            moveRight();
        }
    }
    
}