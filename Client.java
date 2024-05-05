import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Client extends JFrame implements KeyListener{
    public Client(){
        JFrame jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.setVisible(true);
        jframe.setSize(400, 400);
        jframe.addKeyListener(this);
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
        new Client();
    }

}
