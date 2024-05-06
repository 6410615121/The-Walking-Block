package simpleBlock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable{
    private Board board;
    private List<Player> players;

    public Game(Board board) {
        this.board = board;
        players = new ArrayList<Player>();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void delPlayer(Player player){
        players.remove(player);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void updatePlayerPosition(Player player) {
        Cell position = player.getPositionCell();
        for (Player p : players) {
            if (p.equals(player) && p.getPositionCell() != position) {
                p.setPositionCell(position);
                return;
            }
        }
        System.out.println("Player not found: " + player);
    }
}
