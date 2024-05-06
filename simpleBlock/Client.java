package simpleBlock;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Client {
    static void movePlayerRight(Player player,Board board){
        Cell position = player.getPositionCell();
        int current_col = position.getCol();
        int current_row = position.getRol();

        current_col += 1;
        if (current_col > board.COL_SIZE){
            current_col = 0;
        }
        // System.out.printf("col: %d\n", current_col);

        player.setPositionCell(new Cell(current_row, current_col));
        System.out.println(player.getPositionCell());
        System.out.println("updated position");
        // System.out.println(player);
    }
    public static void main(String[] args) {
        Socket socket;
        try {
            socket = new Socket("localhost", 12345);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String message = in.readUTF();
            System.out.println("message from server: " + message);

            // send Player
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Player player = new Player("paipai323", "red", new Cell(5, 5));
            out.writeObject(player);
            out.flush();
            // out.close();

            // get board
            ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream());
            Object obj = inObj.readObject();
            Board board;
            if (obj instanceof Board) {
                board = (Board) obj;
            } else {
                socket.close();
                throw new IOException("can't get Board");
            }

            while (true) {
                // sent player
                out.writeObject(player);
                System.out.println(player.hashCode());
                System.out.println("sent player: " + player);

                // recieve players
                obj = inObj.readObject();
                List<Player> players = null;
                if(obj instanceof ArrayList){
                    players = (ArrayList<Player>) obj;
                }
                System.out.println("got players");

                movePlayerRight(player, board);

                TimeUnit.MILLISECONDS.sleep(500);
            }

            // socket.close();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    // public static void main(String[] args) {
    // try{
    // Socket socket = new Socket("localhost", 12345);
    // ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    // Object obj = in.readObject();

    // if(obj instanceof Player){
    // Player player = (Player) obj;
    // System.out.println("recieved player color: " + player.getColor());
    // }

    // socket.close();

    // }catch(IOException | ClassNotFoundException e){
    // e.printStackTrace();
    // }
    // }

}
