import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import java.util.List;

import javax.swing.JPanel;

public class Game implements KeyListener {
    public GridPanel gridPanel;
    private JPanel[][] cellPanels;
    public Snake[] snakes = new Snake[2];

    public Game() {
        gridPanel = new GridPanel();
        gridPanel.jframe.addKeyListener(this);
        cellPanels = gridPanel.getCellPanels();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // System.out.printf("keycode: %d", keyCode);
        if (keyCode == KeyEvent.VK_W) {
            System.out.println("W key pressed");
            snakes[0].direction = "up";
        } else if (keyCode == KeyEvent.VK_A) {
            System.out.println("A key pressed");
            snakes[0].direction = "left";
        } else if (keyCode == KeyEvent.VK_S) {
            System.out.println("S key pressed");
            snakes[0].direction = "down";
        } else if (keyCode == KeyEvent.VK_D) {
            System.out.println("D key pressed");
            snakes[0].direction = "right";
        }

        if (keyCode == KeyEvent.VK_UP) {
            System.out.println("W key pressed");
            snakes[1].direction = "up";
        } else if (keyCode == KeyEvent.VK_LEFT) {
            System.out.println("A key pressed");
            snakes[1].direction = "left";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            System.out.println("S key pressed");
            snakes[1].direction = "right";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            System.out.println("D key pressed");
            snakes[1].direction = "down";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void snakeRoll(){
        int gridsize = this.gridPanel.gridSize;
        
        // < 0
        if(this.snakes[0].current_y < 0){
            this.snakes[0].current_y = gridsize -1;
        }

        if(this.snakes[0].current_x < 0){
            this.snakes[0].current_x = gridsize -1;
        }

        // > gridsize
        if(this.snakes[0].current_y > gridsize - 1){
            this.snakes[0].current_y = 0;
        }

        if(this.snakes[0].current_x > gridsize - 1){
            this.snakes[0].current_x = 0;
        }

        //

        // < 0
        // < 0
        if(this.snakes[1].current_y < 0){
            this.snakes[1].current_y = gridsize -1;
        }

        if(this.snakes[1].current_x < 0){
            this.snakes[1].current_x = gridsize -1;
        }

        // > gridsize
        if(this.snakes[1].current_y > gridsize - 1){
            this.snakes[1].current_y = 0;
        }

        if(this.snakes[1].current_x > gridsize - 1){
            this.snakes[1].current_x = 0;
        }
    }

    public void paintSnake(){
        List<TrailPoint> trailSnake0 = this.snakes[0].trail;
        List<TrailPoint> trailSnake1 = this.snakes[1].trail;

        cellPanels[this.snakes[0].current_y][this.snakes[0].current_x].setBackground(Color.BLUE);
        for (TrailPoint point : trailSnake0) {
            cellPanels[point.y][point.x].setBackground(Color.BLUE);
        }

        cellPanels[this.snakes[1].current_y][this.snakes[1].current_x].setBackground(Color.GREEN);
        for (TrailPoint point : trailSnake1) {
            cellPanels[point.y][point.x].setBackground(Color.GREEN);
        }
    }

    public void clearBoard(){
        JPanel[][] cellPanels = this.cellPanels;
        int gridsize = gridPanel.gridSize;

        for(int i=0; i< gridsize; i++){
            for(int j=0; j< gridsize; j++){
                cellPanels[i][j].setBackground(Color.WHITE);
            }
        }
    }

    public void waitHalfsec(){
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("interrupted: " + e);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        JPanel[][] cellPanels = game.gridPanel.cellPanels;
        GridPanel gridpanel = game.gridPanel;

        int gridsize = gridpanel.gridSize;

        game.snakes[0] = new Snake(0, 0);
        game.snakes[1] = new Snake(gridsize - 1, gridsize - 1);

        game.snakes[0].direction = "right";
        game.snakes[1].direction = "left";


        // Create and start threads for each snake
        Thread snake1Thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    game.snakes[0].move();
                } catch (InterruptedException e) {
                    System.out.println("Thread for Snake 1 interrupted: " + e);
                }
            }
        });

        Thread snake2Thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    game.snakes[1].move();
                } catch (InterruptedException e) {
                    System.out.println("Thread for Snake 2 interrupted: " + e);
                }
            }
        });

        Thread appleThread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(5000);
                    game.snakes[1].move();
                } catch (InterruptedException e) {
                    System.out.println("Thread for Snake 2 interrupted: " + e);
                }
            }
        });



        // Start the threads
        snake1Thread.start();
        snake2Thread.start();

        while (true) {
                // TimeUnit.MILLISECONDS.sleep(500);
                game.snakeRoll();
                game.clearBoard();
                game.paintSnake();
                game.waitHalfsec();
         } 
    }

}
