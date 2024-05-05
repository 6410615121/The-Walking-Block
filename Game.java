import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            snakes[0].moveUp();
        } else if (keyCode == KeyEvent.VK_A) {
            System.out.println("A key pressed");
            snakes[0].moveLeft();
        } else if (keyCode == KeyEvent.VK_S) {
            System.out.println("S key pressed");
            snakes[0].moveDown();
        } else if (keyCode == KeyEvent.VK_D) {
            System.out.println("D key pressed");
            snakes[0].moveRight();
        }

        if (keyCode == KeyEvent.VK_UP) {
            System.out.println("W key pressed");
            snakes[1].moveUp();
        } else if (keyCode == KeyEvent.VK_LEFT) {
            System.out.println("A key pressed");
            snakes[1].moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            System.out.println("S key pressed");
            snakes[1].moveRight();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            System.out.println("D key pressed");
            snakes[1].moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        Game game = new Game();
        JPanel[][] cellPanels = game.cellPanels;
        GridPanel gridpanel = game.gridPanel;
        int gridsize = gridpanel.gridSize;

        game.snakes[0] = new Snake(0, 0);
        game.snakes[1] = new Snake(0, 0);

        while (true) {
            // < 0
            if(game.snakes[0].current_y < 0){
                game.snakes[0].current_y = gridsize -1;
            }

            if(game.snakes[0].current_x < 0){
                game.snakes[0].current_x = gridsize -1;
            }

            // > gridsize
            if(game.snakes[0].current_y > gridsize - 1){
                game.snakes[0].current_y = 0;
            }

            if(game.snakes[0].current_x > gridsize - 1){
                game.snakes[0].current_x = 0;
            }

            //

            // < 0
            // < 0
            if(game.snakes[1].current_y < 0){
                game.snakes[1].current_y = gridsize -1;
            }

            if(game.snakes[1].current_x < 0){
                game.snakes[1].current_x = gridsize -1;
            }

            // > gridsize
            if(game.snakes[1].current_y > gridsize - 1){
                game.snakes[1].current_y = 0;
            }

            if(game.snakes[1].current_x > gridsize - 1){
                game.snakes[1].current_x = 0;
            }


            

            try{
                cellPanels[game.snakes[0].current_y][game.snakes[0].current_x].setBackground(Color.BLUE);
                cellPanels[game.snakes[1].current_y][game.snakes[1].current_x].setBackground(Color.GREEN);
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("End");
                return;
            }
        }
    }

}
