package simpleBlock;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    // private List<Client> client;
    private final Game game;

    public GameServer() {
        // client = new ArrayList<Client>();
        game = new Game(new Board(10, 10));
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final Game game;

        public ClientHandler(Socket clientSocket, Game game) {
            this.clientSocket = clientSocket;
            this.game = game;
        }

        @Override
        public void run() {
            try {
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                out.writeUTF("Hey client, Send me your Player obj");
                // out.close();

                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Object obj = in.readObject();
                    // in.close();
                    Player player;
                    if (obj instanceof Player){
                        player = (Player) obj;
                    }else{
                        throw new IOException("Wrong Object");
                    }

                    System.out.println("recieved Player: " + player);
                    game.addPlayer(player);
                    System.out.println("added a player");
                    


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try{
                    clientSocket.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

        }

        public static void main(String[] args) {
            try {
                ServerSocket serverSocket = new ServerSocket(12345);
                Game game = new Game(new Board(10, 10));

                while (true) {
                    System.out.println("waiting for connection");
                    Socket clientSocket = serverSocket.accept();

                    // handle client
                    Thread clientThread = new Thread(new ClientHandler(clientSocket, game));
                    clientThread.start();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // public static void main(String[] args) {
        // try {
        // Player player = new Player("paipai323", "red", new Cell(0, 0));
        // ServerSocket serverSocket = new ServerSocket(12345);

        // System.out.println("waiting on 12345");
        // Socket clientSocket = serverSocket.accept();

        // ObjectOutputStream out = new
        // ObjectOutputStream(clientSocket.getOutputStream());
        // out.writeObject(player);
        // out.close();

        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
    }
}
