package MemoryGame.MainWindow.MainWindowClasses;

import java.util.Random;

//klasa zarządzajaca punktami graczy, ich kolejnością kto w danej chwili gra
public class PlayersGameManager {

    private int playersTurn;
    private boolean randFirstPlayerTurn = false;

    public int getPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(int playerTurn) {
        this.playersTurn = playerTurn;
    }
    // zmiana kolejki gracza
    public void changePlayersTurn(){
        playersTurn++;
        if(playersTurn==2){
            setPlayersTurn(0);
        }
    }

    //zarzadzanie czyja aktualnie jest kolej i komu przyznac punkt
    public int whosTurn(String argument){
        //losowanie przy pierwszym uruchomieniu czyja kolej
        if(randFirstPlayerTurn==false)
        {
            Random random = new Random();
            setPlayersTurn(random.nextInt(2));
            randFirstPlayerTurn=true;
            return getPlayersTurn();
        }
        if(argument=="notTheSame"){
            changePlayersTurn();
            return getPlayersTurn();
        }
        return getPlayersTurn();
    }

    //method check if the game is over
    public String checkerGameIsOverOrNot(PlayerInfoAndStats playerInfoAndStats1, PlayerInfoAndStats playerInfoAndStats2)
    {
        if(playerInfoAndStats1.getPlayerPoints()+playerInfoAndStats2.getPlayerPoints()==6)
        {
            return "gameIsOver";
        }
        return "gameIsNotOver";
    }

    //method check who's win
    public String comparePlayersPoints(PlayerInfoAndStats playerInfoAndStats1, PlayerInfoAndStats playerInfoAndStats2)
    {
        // - player 1 win
        if(playerInfoAndStats1.getPlayerPoints()>playerInfoAndStats2.getPlayerPoints())
        {
            return "WinPlayer1";
        }

        // - remis
        if(playerInfoAndStats1.getPlayerPoints()==playerInfoAndStats2.getPlayerPoints())
        {
            return "Remis";
        }

        // - player 2 win
        if(playerInfoAndStats1.getPlayerPoints()<playerInfoAndStats2.getPlayerPoints())
        {
            return "WinPlayer2";
        }

        return "error";

    }



}

