package simpleBlock;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String message = in.readUTF();
            System.out.println("message from server: " + message);

            // send Player
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Player player = new Player("paipai323", "red", new Cell(0, 0));
            out.writeObject(player);
            // out.close();


            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static void main(String[] args) {
    //     try{
    //         Socket socket = new Socket("localhost", 12345);
    //         ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    //         Object obj = in.readObject();
            
    //         if(obj instanceof Player){
    //             Player player = (Player) obj;
    //             System.out.println("recieved player color: " + player.getColor());
    //         }

    //         socket.close();
            
    //     }catch(IOException | ClassNotFoundException e){
    //         e.printStackTrace();
    //     }
    // }
    
}
