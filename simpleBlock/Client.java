package simpleBlock;

import java.io.DataInputStream;
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

    public Client(Player player, Socket socket) {
        this.player = player;
        this.socket = socket;

        try {
            inObject = new ObjectInputStream(socket.getInputStream());
            outObject = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Error while trying to create Object...Stream");
            e.printStackTrace();
        }
    }

    public void sentPlayerObj() {
        try {
            outObject.writeUnshared(player);
        } catch (IOException e) {
            System.err.println("Error while trying to sent Player Object");
            e.printStackTrace();
        }
    }

    public void getBoard(ObjectInputStream in) {
        try {
            this.board = (Board) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error while trying to get Board Object");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void getPlayers() {
        try {
            this.players = (List<Player>) inObject.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error while trying to get players list");
            e.printStackTrace();
        }
    }

}