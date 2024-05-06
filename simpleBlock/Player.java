package simpleBlock;

public class Player {
    private String playerID;
    private String color;

    public Player(String playerID) {
        this.playerID = playerID;
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

}
