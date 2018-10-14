package MemoryGame.MainWindow.MainWindowClasses;

public class StartGame {
    private String namePlayer1;
    private String namePlayer2;

// - player 1
    public String getNamePlayer1() {
        return namePlayer1;
    }

    public void setNamePlayer1(String namePlayer1) {
        this.namePlayer1 = namePlayer1;
    }

// - player 2
    public String getNamePlayer2() {
        return namePlayer2;
    }

    public void setNamePlayer2(String namePlayer2) {
        this.namePlayer2 = namePlayer2;
    }

    //start button - check if the players names contains of minimum 3 sings
    public String StartGameButton()
    {
        if(namePlayer1.length()<3 && namePlayer2.length()<3)
        {
            return "Player1&2TextProblems";
        }

        if(namePlayer1.length()<3)
        {
            return "Player1TextProblem";
        }

        if(namePlayer2.length()<3)
        {
            return "Player2TextProblem";
        }
        return "Ok";
    }

}
