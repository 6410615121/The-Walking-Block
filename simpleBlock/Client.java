package simpleBlock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Client {
    private Player player;

    private List<Player> players;
    private Board board;

    private Socket socket;
    private ObjectInputStream inObject;
    private ObjectOutputStream outObject;

    private DataOutputStream outData;
    private DataInputStream inData;

    public Client(Player player, Socket socket) {
        this.player = player;
        this.socket = socket;

            // this.inData = new DataInputStream(socket.getInputStream());
            // this.outData = new DataOutputStream(socket.getOutputStream());

            // this.inObject = new ObjectInputStream(socket.getInputStream());
            // this.outObject = new ObjectOutputStream(socket.getOutputStream());

    }

    protected void sentMessageToServer(String message){
        try {
            outData.writeUTF(message);
        } catch (IOException e) {
            System.err.println("Error while trying to send message to client");
            e.printStackTrace();
        }
    }

    protected String getMessageFromServer(){
        try {
            String message = inData.readUTF();
            return message;
        } catch (IOException e) {
            System.err.println("Error while trying to send message to client");
            e.printStackTrace();
            return null;
        }
    }

    public void sentPlayerObjToServer() throws IOException {
        if (this.outObject == null){
            this.outObject = new ObjectOutputStream(socket.getOutputStream());
        }

        outObject.writeUnshared(player);
    }

    public void getBoardFromServer() {
        if( this.inObject == null){
            try {
                this.inObject = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            this.board = (Board) this.inObject.readUnshared();
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error while trying to get Board Object");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void getPlayersFromServer() {
        try {
            List<Player> newPlayers = (List<Player>) inObject.readObject();
            this.players = newPlayers;
            // System.out.println("players from getplayerfromserver(): " + newPlayers);
            // System.out.println("newplayers hash: " + newPlayers.hashCode());
            // System.out.println(this.players);
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error while trying to get players list");
            e.printStackTrace();
        }
    }

    public void playerMove(String direction){
        if(direction.equalsIgnoreCase("down")){
            Cell current_pos_cell = player.getPositionCell();
            int current_row = current_pos_cell.getRow();
            int current_col = current_pos_cell.getCol();
            
            if(++current_row > board.ROW_SIZE){
                current_row = 0;
            }

            player.setPositionCell(new Cell(current_row, current_col));
        }else if (direction.equalsIgnoreCase("up")){
            Cell current_pos_cell = player.getPositionCell();
            int current_row = current_pos_cell.getRow();
            int current_col = current_pos_cell.getCol();
            
            if(--current_row < 0){
                current_row = board.ROW_SIZE;
            }

            player.setPositionCell(new Cell(current_row, current_col));
        }else if (direction.equalsIgnoreCase("left")){
            Cell current_pos_cell = player.getPositionCell();
            int current_row = current_pos_cell.getRow();
            int current_col = current_pos_cell.getCol();
            
            if(--current_col < 0){
                current_col = board.COL_SIZE;
            }

            player.setPositionCell(new Cell(current_row, current_col));
        }else if (direction.equalsIgnoreCase("right")){
            Cell current_pos_cell = player.getPositionCell();
            int current_row = current_pos_cell.getRow();
            int current_col = current_pos_cell.getCol();
            
            if(++current_col > board.COL_SIZE){
                current_col = 0;
            }

            player.setPositionCell(new Cell(current_row, current_col));
        }
    }

    public Player getPlayer() {
        return player;
    }


    public List<Player> getPlayers() {
        return players;
    }

    
    public Board getBoard() {
        return board;
    }

}