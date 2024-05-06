package simpleBlock;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;;

public class ClientUI extends JFrame implements KeyListener{
    private Client client;
    private int characterRow;
    private int characterCol;

    public ClientUI(Client client){
        this.client = client;

        initFrame();
    }

    private void initFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
        setSize(400, 400);
        // setLayout(new GridLayout(40, 40));
        setVisible(true);
    }

    public void initBoard(){
        // layout
        int row_size = client.getBoard().ROW_SIZE;
        int col_size = client.getBoard().COL_SIZE;
        setLayout(new GridLayout(row_size, col_size));

        // cell panel
        for(int i = 0; i < row_size; i++){
            for(int j = 0; j < col_size; j++){
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(panel);
            }
        }
        setVisible(true);
    }

    // public void renderOtherPlayer(){

    //     }
    // }

    public void renderClientCharacter(){
        List<Player> players = client.getPlayers();

        for (Player player : players) {
            
        }
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
        try {
            Player player = new Player("paipai323", "Black", new Cell(0, 0));
            Socket socket = new Socket("localhost", 12345);
            System.out.println("connected");

            Client client = new Client(player, socket);
            System.out.println("created client");

            // send player
            client.sentPlayerObjToServer();
            System.out.println("sent player");

            // UI thread
            Thread UIThread = new Thread(() -> {
                ClientUI clientUI = new ClientUI(client);
                System.out.println("get UI");

                // get board
                client.getBoardFromServer();
                System.out.println("got board");
                clientUI.initBoard();
                System.out.println("set board");

                // loop render screen

            });
            UIThread.start();

            // loop thread


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
