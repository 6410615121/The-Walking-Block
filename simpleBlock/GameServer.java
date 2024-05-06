package simpleBlock;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static void main(String[] args) {
        try {
            Player player = new Player("paipai323", "red", new Cell(0, 0));
            ServerSocket serverSocket = new ServerSocket(12345);

            System.out.println("waiting on 12345");
            Socket clientSocket = serverSocket.accept();

            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(player);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
