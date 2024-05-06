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
import java.util.concurrent.TimeUnit;

public class GameServer {
    private final Game game;

    public GameServer(Game game){
        this.game = game;
    }

    public static class ClientHandler implements Runnable{
        private Socket clientSocket;
        private final Game game;
        private final Board board;

        private ObjectInputStream inObject;
        private ObjectOutputStream outObject;

        // constructer of ClientHandler
        public ClientHandler(Socket clientSocket, Game game){
            this.clientSocket = clientSocket;
            this.game = game;
            this.board = game.getBoard();

            try {
                this.inObject = new ObjectInputStream(clientSocket.getInputStream());
                this.outObject = new ObjectOutputStream(clientSocket.getOutputStream());
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

        @Override
        public void run() {
            // get Player object
            Player player = getPlayerObjectFromClient();

            // sent Board object
            sentBoardToClient();
            

            // loop (get player > update pos > sent players)
        }
    }
}