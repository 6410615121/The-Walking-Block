package simpleBlock;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;;

public class ClientUI extends JFrame implements KeyListener{
    private Client client;

    public ClientUI(Client client){
        this.client = client;
        initFrame();
    }

    private void initFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
        setSize(400, 400);
        setLayout(new GridLayout(40, 40));
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                // Handle up arrow key press
                break;
            case KeyEvent.VK_DOWN:
                // Handle down arrow key press
                break;
            case KeyEvent.VK_LEFT:
                // Handle left arrow key press
                break;
            case KeyEvent.VK_RIGHT:
                // Handle right arrow key press
                break;
            default:
                // Handle other key presses if needed
                System.out.println("Press!");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        Player player = new Player("paipai323", "Black", new Cell(0, 0));
        try {
            Socket socket = new Socket("localhost", 12345);
            ClientUI clientUI = new ClientUI(new Client(player, socket));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
