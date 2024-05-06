package simpleBlock;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private List<Client> client;
    private Game game;
    public GameServer(){
        client = new ArrayList<Client>();
        game = new Game(new Board(10, 10));
    }

    static class ClientHandler implements Runnable{
        private final Socket clientSocket;
        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
        @Override
        public void run() {
            try {
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                out.writeUTF("hey Client");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    clientSocket.close();
                    System.out.println("Client disconnected: " + clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(12345);

            while(true){
                System.out.println("waiting for connection");
                Socket clientSocket = serverSocket.accept();

                //handle client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // public static void main(String[] args) {
    //     try {
    //         Player player = new Player("paipai323", "red", new Cell(0, 0));
    //         ServerSocket serverSocket = new ServerSocket(12345);

    //         System.out.println("waiting on 12345");
    //         Socket clientSocket = serverSocket.accept();

    //         ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
    //         out.writeObject(player);
    //         out.close();

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}
