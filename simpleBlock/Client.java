package simpleBlock;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        try {
            socket = new Socket("localhost", 12345);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String message = in.readUTF();
            System.out.println("message from server: " + message);

            // send Player
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Player player = new Player("paipai323", "red", new Cell(0, 0));
            out.writeObject(player);
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

                // recieve players
                inObj.readObject();
            }

            // socket.close();
        } catch (IOException | ClassNotFoundException e) {
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
