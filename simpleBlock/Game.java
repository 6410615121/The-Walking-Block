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
        Cell new_position = player.getPositionCell();
        for (Player p : players) {
            // System.out.printf("p.equals(player): %b\n", p.equals(player));
            // System.out.printf("!p.getPositionCell().equals(new_position): %b\n", !p.getPositionCell().equals(new_position));

            if (p.equals(player) && !p.getPositionCell().equals(new_position)) {
                p.setPositionCell(new_position);
                System.out.println("updated position of player: "+ player);
                return;
            }
        }
        // System.out.println("Player not found: " + player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
