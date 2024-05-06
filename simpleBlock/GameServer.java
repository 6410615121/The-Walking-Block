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
    private Game game;
    private List<Player> players;

    public static class ClientHandler implements Runnable{
        private Socket clientSocket;
        private Game game;
        private List<Player> players;
        private Board board;

        public ClientHandler(Socket clientSocket, Game game){
            this.clientSocket = clientSocket;
            this.game = game;
            this.board = game.getBoard();
            this.players = game.getPlayers();
        }

        @Override
        public void run() {
            // get Player object
            // sent Board object
            
            // loop (get player > update pos > sent players)
        }
    }
}