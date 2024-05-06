package simpleBlock;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameServer {
    private final Game game;
    private ServerSocket serverSocket;

    public GameServer(Game game, int port){
        this.game = game;

        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Error while trying to create serverSocket");
            e.printStackTrace();
        }

    }

    public void listenAndHandle(){
        try{
            while(true){
                System.out.println("Listening on " + serverSocket.getLocalPort());
                Socket clientSocket = serverSocket.accept();

                Thread clientThread = new Thread(new ClientHandler(clientSocket, game));
                clientThread.start();
            }
        }catch(IOException e){
            System.err.println("Error while listening");
            e.printStackTrace();
        }
    }

    public static class ClientHandler implements Runnable{
        private Socket clientSocket;
        private final Game game;
        private final Board board;

        private ObjectInputStream inObject;
        private ObjectOutputStream outObject;

        private DataOutputStream outData;
        private DataInputStream inData;

        // constructer of ClientHandler
        public ClientHandler(Socket clientSocket, Game game){
            this.clientSocket = clientSocket;
            this.game = game;
            this.board = game.getBoard();

            try {
                // this.inObject = new ObjectInputStream(clientSocket.getInputStream());
                // this.outObject = new ObjectOutputStream(clientSocket.getOutputStream());

                this.inData = new DataInputStream(clientSocket.getInputStream());
                this.outData = new DataOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                System.err.println("Error while trying to init Object..Stream");
                e.printStackTrace();
            }
            
        }

        private Player getPlayerObjectFromClient(){
            try {
                Player player = (Player) inObject.readObject();
                return player;
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            return null;
            }
        }

        private void sentBoardToClient(){
            try {
                outObject.writeUnshared(board);
            } catch (IOException e) {
                System.err.println("Error while trying to send Board Object to client");
                e.printStackTrace();
            }
        }

        private void sentMessageToClient(String message){
            try {
                outData.writeUTF(message);
            } catch (IOException e) {
                System.err.println("Error while trying to send message to client");
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("Connected");
            sentMessageToClient("Hello Client");
            try {
                clientSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            // get Player object
            // Player player = getPlayerObjectFromClient();

            // sent Board object
            // sentBoardToClient();
            

            // loop (get player > update pos > sent players)
        }
    }

    public static void main(String[] args) {
        Game game = new Game(new Board(10, 10));
        GameServer gameServer = new GameServer(game, 12345);

        gameServer.listenAndHandle();
    }
}