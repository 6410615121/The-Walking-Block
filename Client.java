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

public class Client implements KeyListener{
    public JFrame jframe;
    public JPanel[][] cellPanels;

    public Client(){
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.setSize(400, 400);
        // jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.addKeyListener(this);
        
        GridLayout grid = new GridLayout(10, 10, 1, 1);
        jframe.setLayout(grid);
        
        // create cell 10 x 10
        JPanel[][] cellPanels = new JPanel[10][10]; // 
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
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

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // System.out.printf("keycode: %d", keyCode);
        if (keyCode == KeyEvent.VK_W) {
            System.out.println("W key pressed");
        } else if (keyCode == KeyEvent.VK_A) {
            System.out.println("A key pressed");
        } else if (keyCode == KeyEvent.VK_S) {
            System.out.println("S key pressed");
        } else if (keyCode == KeyEvent.VK_D) {
            System.out.println("D key pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not needed
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed
    }

    public static void main(String[] args) {
        Client client = new Client();

    }

}
