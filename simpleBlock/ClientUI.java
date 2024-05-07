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
import java.util.List;
import java.util.concurrent.TimeUnit;;

public class ClientUI extends JFrame implements KeyListener{
    private Client client;
    private List<Player> prev_players;
    private JPanel[][] cellPanel;
    

    public ClientUI(Client client){
        this.client = client;
        prev_players = client.getPlayers();
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
        cellPanel = new JPanel[row_size][col_size];
        for(int i = 0; i < row_size; i++){
            for(int j = 0; j < col_size; j++){
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(panel);
                
                cellPanel[i][j] = panel;
            }
        }
        setVisible(true);
    }

    private Player getPlayerFromPlayers(Player player, List<Player> players){
        for (Player playerInPlayers : players) {
            if( playerInPlayers.equals(player)){
                System.out.println("player from getPlayerFromPlayers: " +player);
                return player;
            }
        }
        return null;
    }

    public void render(){
        List<Player> new_players = client.getPlayers();
    
        try{
            for (Player newplayer : new_players) {
                Cell new_pos_cell = newplayer.getPositionCell();
                Player prevPlayer = getPlayerFromPlayers(newplayer, prev_players);
                Cell old_pos_cell = prevPlayer.getPositionCell();
    
                System.out.println("old_pos_cell: " + old_pos_cell);
                System.out.println("new_pos_cell: " + new_pos_cell);
    
                if(!old_pos_cell.equals(new_pos_cell)){
                    cellPanel[old_pos_cell.getRow()][old_pos_cell.getCol()].setBackground(Color.WHITE);
                    cellPanel[new_pos_cell.getRow()][new_pos_cell.getCol()].setBackground(Color.BLACK);
                } else {
                    cellPanel[new_pos_cell.getRow()][new_pos_cell.getCol()].setBackground(Color.BLACK);
                }
            }
    
            // Update prev_players only after rendering all new players
            this.prev_players = new_players;
            TimeUnit.MILLISECONDS.sleep(500);
        } catch(InterruptedException e){
            e.printStackTrace();
        } catch(NullPointerException e){
            System.out.println("No new_players yet");
            this.prev_players = new_players;
        }
    }
    

    public void clearBoard(){
        for(int i = 0; i < cellPanel.length; i++){
            for(int j = 0; j < cellPanel[0].length; j++){
                JPanel panel = cellPanel[i][j];
                panel.setBackground(Color.WHITE);
            }
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
                client.playerMove("down");
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

            // get board
            client.getBoardFromServer();
            System.out.println("got board");

            // UI thread
            Thread UIThread = new Thread(() -> {
                ClientUI clientUI = new ClientUI(client);
                System.out.println("get UI");

                clientUI.initBoard();
                System.out.println("set board");

                // loop render screen thread
                while(true){
                    clientUI.render();
                }
                
            });
            UIThread.start();

            // loop thread
            Thread dataExchangeThread = new Thread(() ->{
                try {
                    while(true){

                        // sent player obj to server
                        client.sentPlayerObjToServer();
                        System.out.println("sent player obj");

                        TimeUnit.MILLISECONDS.sleep(500);

                        // recieve players from server
                        client.getPlayersFromServer();
                        // System.out.println("client's players: " + client.getPlayers());
                        System.out.println("got players obj");
                    }
                    
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            dataExchangeThread.start();





        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
