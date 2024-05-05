import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class GridPanel{
    public JFrame jframe;
    public JPanel[][] cellPanels;
    public final int gridSize = 10;

    public GridPanel(){
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(400, 400);
        // jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        // jframe.addKeyListener(this);
        
        GridLayout grid = new GridLayout(gridSize, gridSize, 1, 1);
        jframe.setLayout(grid);
        
        // create cells with gridSize
        cellPanels = new JPanel[gridSize][gridSize]; // 
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                JPanel cellPanel = createCellPanel();
                jframe.add(cellPanel);
                cellPanels[i][j] = cellPanel;
            }
        }
        jframe.setVisible(true);
    }

    private JPanel createCellPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }

    public JPanel[][] getCellPanels(){
        return cellPanels;
    }

    // @Override
    // public void keyPressed(KeyEvent e) {
    //     int keyCode = e.getKeyCode();
    //     // System.out.printf("keycode: %d", keyCode);
    //     if (keyCode == KeyEvent.VK_W) {
    //         System.out.println("W key pressed");
    //     } else if (keyCode == KeyEvent.VK_A) {
    //         System.out.println("A key pressed");
    //     } else if (keyCode == KeyEvent.VK_S) {
    //         System.out.println("S key pressed");
    //     } else if (keyCode == KeyEvent.VK_D) {
    //         System.out.println("D key pressed");
    //     }
    // }

    // @Override
    // public void keyReleased(KeyEvent e) {
    //     // Not needed
    // }

    // @Override
    // public void keyTyped(KeyEvent e) {
    //     // Not needed
    // }

    public static void main(String[] args) {
        GridPanel test = new GridPanel();

    }

}
