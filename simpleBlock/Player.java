package simpleBlock;

import java.io.Serializable;

public class Player implements Serializable{
    private String playerID;
    private String color;
    private Cell positionCell;

    public Player(String playerID, String color, Cell positionCell) {
        this.playerID = playerID;
        this.color = color;
        this.positionCell = positionCell;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public Cell getPositionCell() {
        return positionCell;
    }

    public void setPositionCell(Cell positionCell) {
        this.positionCell = positionCell;
    }

    @Override
    public String toString() {
        String message = String.format("PlayerID: %s, color: %s", playerID, color);
        return message;
    }

}
