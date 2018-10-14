package MemoryGame.MainWindow.MainWindowClasses;

public class PlayerInfoAndStats {
    private int playerPoints = 0;
    private String playerName;

    public PlayerInfoAndStats() {
        playerName = "";
    }
    public PlayerInfoAndStats(String name) {
        playerName = name;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void addPlayerPoint() {
        playerPoints++;
    }

    public void setPlayerPoints(int playerPoints)
    {
        this.playerPoints=playerPoints;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
