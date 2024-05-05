import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Game implements KeyListener{
    public GridPanel gridPanel;
    private JPanel[][] cellPanels;
    public Snake snake;

    public Game(){
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
            snake.moveUp();
        } else if (keyCode == KeyEvent.VK_A) {
            System.out.println("A key pressed");
            snake.moveLeft();
        } else if (keyCode == KeyEvent.VK_S) {
            System.out.println("S key pressed");
            snake.moveDown();
        } else if (keyCode == KeyEvent.VK_D) {
            System.out.println("D key pressed");
            snake.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        Game game = new Game();
        JPanel[][] cellPanels = game.cellPanels;

        game.snake = new Snake(0, 0);
        

        while(true){
            cellPanels[game.snake.current_y][game.snake.current_x].setBackground(Color.BLUE);
        }
    }


}
