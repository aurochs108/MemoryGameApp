package MemoryGame.MainWindow;

import MemoryGame.MainWindow.MainWindowClasses.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;


public class MainWindowController {

    //***************init classes***************//
    StartGame startGame = new StartGame();                                  //class menage to start the game, set cards
    CardFactor cardFactor = new CardFactor();                               //class which menage cards
    PlayerFactor playerFactor = new PlayerFactor();                         //class menage about what players do
    PlayersGameManager playersGameManager = new PlayersGameManager();       //class menage players
    PlayerInfoAndStats playerInfoAndStats1= new PlayerInfoAndStats();       //player number 1
    PlayerInfoAndStats playerInfoAndStats2= new PlayerInfoAndStats();       //player number 2

    //***************variables and tabs***************//
    int counterTimer=0;
    private int numberOfPlayer;                                             //var keep number currently playing gamer
    public int[] memoryCardInt = new int[12];                               //tab that is holding number of memory card
    private PlayerInfoAndStats[] tabOfPlayersInfoAndStats= {playerInfoAndStats1, playerInfoAndStats2};  //tab that is holding playersInfoAndStats objects
    private int counterTurn = 0;                                            //variable of the first move (0=first move)

    //***************FXML contolers***************//
@FXML
    Button StartButton, oneMoreTimeButton;          //button to start game, button to play one more time
@FXML
    TextField Player1TextField, Player2TextField;   //textfield where player write his name
@FXML
    Label Player1Label,Player2Label;                //labels in start menu
@FXML
    Label  Warning3LessThanLettersLabel;            //label where you can see warning about
@FXML
    ImageView image0, image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11;     //images - back of cards
@FXML
    ImageView MemoryCard0, MemoryCard1, MemoryCard2, MemoryCard3, MemoryCard4, MemoryCard5, MemoryCard6, MemoryCard7, MemoryCard8, MemoryCard9, MemoryCard10, MemoryCard11; //hidden cards
@FXML
    Label player1Name, player2Name, endGameLabel;   //labels where you can see player name

    //*START WINDOW*//

    //***************init method***************//
    @FXML
    public void initialize()
    {
        hideShowBackCards(false);
        showMemoryCard(false);
        setPlayerNrName(false);
        endGameLabel.setVisible(false);             //hide label who's win
        oneMoreTimeButton.setVisible(false);        //hide button one more game
    }

    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    //***************hide or show playerName labels***************//
    public void setPlayerNrName(boolean hideValue)
    {
        player1Name.setVisible(hideValue);
        player2Name.setVisible(hideValue);
    }

    //***************set names of player to labels***************//
    public void setPlayerNrName(String namePlayer1, String namePlayer2, boolean showValue)
    {
        player1Name.setVisible(showValue);
        player2Name.setVisible(showValue);
        player1Name.setText(namePlayer1);
        player2Name.setText(namePlayer2);
    }


    public int[] getMemoryCardInt() {
        return memoryCardInt;
    }

    //***************hide or show back cards***************//
    public void hideShowBackCards(int numberOfCard, boolean boolValue)
    {
        ImageView[] tabOfImages = {image0, image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11};
        tabOfImages[numberOfCard].setVisible(boolValue);
    }

    //***************hide or show all back cards***************//
    public void hideShowBackCards(boolean boolValue){
        ImageView[] tabOfImages = {image0, image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11};
        for(int i=0; i<12; i++)
        {
            tabOfImages[i].setVisible(boolValue);
        }
    }

    //***************hide or show  memory card***************//
    public void showMemoryCard(int numberOfCard, boolean hideOrShowValue)
    {
        ImageView[] tabOfImages = {MemoryCard0, MemoryCard1, MemoryCard2, MemoryCard3, MemoryCard4, MemoryCard5, MemoryCard6, MemoryCard7, MemoryCard8, MemoryCard9, MemoryCard10, MemoryCard11};
        tabOfImages[numberOfCard].setVisible(hideOrShowValue);
    }
    //***************hide or show all memory card***************//
    public void showMemoryCard( boolean hideOrShowValue)
    {
        ImageView[] tabOfImages = {MemoryCard0, MemoryCard1, MemoryCard2, MemoryCard3, MemoryCard4, MemoryCard5, MemoryCard6, MemoryCard7, MemoryCard8, MemoryCard9, MemoryCard10, MemoryCard11};
        for(int i=0; i<12; i++) {
            tabOfImages[i].setVisible(hideOrShowValue);
        }
    }

    //***************click on start button action***************//
    public void StartButtonClick(ActionEvent event) {

        //get the player names from textfields
        startGame.setNamePlayer1(Player1TextField.getText());
        startGame.setNamePlayer2(Player2TextField.getText());


        if(startGame.StartGameButton()=="Ok")
        {
            StartButton.setVisible(false);
            Player1TextField.setVisible(false);
            Player2TextField.setVisible(false);
            Player1Label.setVisible(false);
            Player2Label.setVisible(false);
            Warning3LessThanLettersLabel.setVisible(false);
            hideShowBackCards(true);
            setHiddenCards();
            setPlayerInfoAndStatsNames(startGame.getNamePlayer1(), startGame.getNamePlayer2());
            setPlayerNrName(startGame.getNamePlayer1(), startGame.getNamePlayer2(), true);

            //change first player name to BOLD inscription
            int numberOfPlayer = playersGameManager.whosTurn("abstract");
            setPlayerInfoAndsStatsNamesBold(numberOfPlayer);
        }
        if(startGame.StartGameButton()=="Player1TextProblem")
        {
            Player1TextField.setStyle("-fx-text-fill: red");
            Player2TextField.setStyle("-fx-text-fill: black");
            Warning3LessThanLettersLabel.setAlignment(Pos.CENTER);
            Warning3LessThanLettersLabel.setText("Name of the user must contains at least 3 signs");
        }
        if(startGame.StartGameButton()=="Player2TextProblem")
        {
            Player2TextField.setStyle("-fx-text-fill: red");
            Player1TextField.setStyle("-fx-text-fill: black");
            Warning3LessThanLettersLabel.setAlignment(Pos.CENTER);
            Warning3LessThanLettersLabel.setText("Name of the user must contains at least 3 signs");
        }

        if(startGame.StartGameButton()=="Player1&2TextProblems")
        {
            Player1TextField.setStyle("-fx-text-fill: red");
            Player2TextField.setStyle("-fx-text-fill: red");
            Warning3LessThanLettersLabel.setAlignment(Pos.CENTER);
            Warning3LessThanLettersLabel.setText("Names of the users must contains at least 3 signs");
        }

    }

    //*START WINDOW*//

    //***************set images to hidden images***************//
    public void setHiddenCards()
    {
        ImageView[] tabOfImages = {MemoryCard0, MemoryCard1, MemoryCard2, MemoryCard3, MemoryCard4, MemoryCard5, MemoryCard6, MemoryCard7, MemoryCard8, MemoryCard9, MemoryCard10, MemoryCard11};
        cardFactor.RandCards();
        memoryCardInt = cardFactor.GetTabOfCards();
        playerFactor.setTabOfCards(memoryCardInt);

        String CardAddress;
        FileInputStream input = null;

        for (int i=0; i<12; i++)
        {
            CardAddress = "C:\\Users\\dawid\\IdeaProjects\\MemoryGameApp\\src/MemoryGame\\MainWindow\\MemoryImages\\" + memoryCardInt[i] + ".jpg";
            try {
                input = new FileInputStream(CardAddress);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imageWeatherCondition = new Image(input);
            tabOfImages[i].setImage(imageWeatherCondition);
        }
    }

    //***************click on backcards action***************//
    public void image0Click(MouseEvent event)
    {
        int numberOfImage = 0;
        imageClickAction(numberOfImage);
    }
    public void image1Click(MouseEvent event)
    {
        int numberOfImage = 1;
        imageClickAction(numberOfImage);
    }
    public void image2Click(MouseEvent event)
    {
        int numberOfImage = 2;
        imageClickAction(numberOfImage);
    }
    public void image3Click(MouseEvent event)
    {
        int numberOfImage = 3;
        imageClickAction(numberOfImage);
    }
    public void image4Click(MouseEvent event)
    {
        int numberOfImage = 4;
        imageClickAction(numberOfImage);
    }
    public void image5Click(MouseEvent event)
    {
        int numberOfImage = 5;
        imageClickAction(numberOfImage);
    }
    public void image6Click(MouseEvent event)
    {
        int numberOfImage = 6;
        imageClickAction(numberOfImage);
    }
    public void image7Click(MouseEvent event)
    {
        int numberOfImage = 7;
        imageClickAction(numberOfImage);
    }
    public void image8Click(MouseEvent event)
    {
        int numberOfImage = 8;
        imageClickAction(numberOfImage);
    }
    public void image9Click(MouseEvent event)
    {
        int numberOfImage = 9;
        imageClickAction(numberOfImage);
    }
    public void image10Click(MouseEvent event)
    {   int numberOfImage = 10;
        imageClickAction(numberOfImage);
    }
    public void image11Click(MouseEvent event)
    {
        int numberOfImage = 11;
        imageClickAction(numberOfImage);
    }

    //***************action after clicking on backcard image***************//
    public void imageClickAction(int numberOfImage)
    {
        hideShowBackCards(numberOfImage, false);
        showMemoryCard(numberOfImage, true);
        playerFactor.showedCardsSaver(numberOfImage);
        gameMaster();
    }

    //***************card hide or not to hide control method, check when game is over***************//
    public void gameMaster()
    {

        //check whos turn is currently
        if((playerFactor.getMove()==2 || playerFactor.getMove()==4) && (playerFactor.cardChecker().equals("notTheSame")))
        {
            String decision = playerFactor.cardChecker();
            setNumberOfPlayer(playersGameManager.whosTurn(decision));
            setPlayerInfoAndsStatsNamesBold(getNumberOfPlayer());

            Timer timer = new Timer();                                               //timer class initialization
            int[] tabOfCardsClickedCards = playerFactor.getTabOfShowedCards();       //tab where the numbers of clicked cards are

            //turn off "Click-Throughs" of backcards
            setDisableHiddenImages(true);
            for(int i=0; i<2; i++) {

                //Function delayed to 2 seconds, it's beacuse player must see chosen cards
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        hideShowBackCards(tabOfCardsClickedCards[counterTimer], true);
                        showMemoryCard(tabOfCardsClickedCards[counterTimer], false);
                        counterTimer++;
                        if(counterTimer>1){
                            counterTimer=0;
                        }
                        //turn on "Click-Throughs" of backcards
                        setDisableHiddenImages(false);
                    }
                }
                , 2000);
            }
        }

        //if cards are the same add points to player
        else {
            if ((playerFactor.getMove() == 2 || playerFactor.getMove() == 4)) {
                tabOfPlayersInfoAndStats[getNumberOfPlayer()].addPlayerPoint();
            }
        }

        //call the function which check if the game is over
        endGame();
    }

    //***************method checked point of the player and says when is the end***************//
    public void endGame()
    {
        //check if the game is over and run end action
        if(playersGameManager.checkerGameIsOverOrNot(playerInfoAndStats1, playerInfoAndStats2)=="gameIsOver")
        {
            //show wining player label and one more time button
            endGameLabel.setVisible(true);
            oneMoreTimeButton.setVisible(true);

            String result="";      //variable to load result of whos win or is it remis/ load "" String to avoid problems
            String resultWithoutEdit=playersGameManager.comparePlayersPoints(playerInfoAndStats1, playerInfoAndStats2); //load String result from compare points players method

            //check who's win and set result variable
            if(resultWithoutEdit=="WinPlayer1")
            {
                result=playerInfoAndStats1.getPlayerName()+" win!";
            }

            if(resultWithoutEdit=="WinPlayer2")
            {
                result=playerInfoAndStats2.getPlayerName()+" win!";
            }

            if(resultWithoutEdit=="Remis")
            {
                result="Remis!";
            }

            endGameLabel.setText(result);
            showMemoryCard(false);
            setPlayerNrName(false);
        }

    }

    //***************button start a new game after win***************//
    //!WORK IN PROGRESS!//
    public void oneMoreTimeButtonClick(ActionEvent event)
    {
        //!set 0 to player points and tab of points!//
        initialize();
        StartButton.setVisible(true);
        Player1TextField.setVisible(true);
        Player2TextField.setVisible(true);
        Player1Label.setVisible(true);
        Player2Label.setVisible(true);
        Warning3LessThanLettersLabel.setVisible(true);

        //restart players stats and names
        for(int i=0; i<2; i++)
        {
            tabOfPlayersInfoAndStats[i].setPlayerPoints(0);
            tabOfPlayersInfoAndStats[i].setPlayerName("");
        }
    }

    //***************set to "frozen" images after click two memory images and not find convergence***************//
    public void setDisableHiddenImages(boolean boolValue){
        ImageView[] tabOfImages = {image0, image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11};
        for(int i=0; i<12; i++)
        {
            tabOfImages[i].setDisable(boolValue);
        }
    }

    //***************give player names to playerInfoAndStats objects***************//
    public void setPlayerInfoAndStatsNames(String name1, String name2){
        playerInfoAndStats1.setPlayerName(name1);
        playerInfoAndStats2.setPlayerName(name2);
    }

    //***************method shows who play now***************//
    public void setPlayerInfoAndsStatsNamesBold(int numberOfPlayerPlayed){

        Label[] tabOfLabels = {player1Name, player2Name};                                   //tab of labels with names players
        String tempLabelText = tabOfLabels[numberOfPlayerPlayed].getText();                 //get player name
        tabOfLabels[numberOfPlayerPlayed].setText("Your Turn " + tempLabelText);
        tabOfLabels[numberOfPlayerPlayed].setFont(Font.font("Verdana", 20));

        //check if the turn is not the first
        if(counterTurn!=0)
        {
            int notYourTurn=9;                  //variable of player who play in next turn

            //switch notYourTurn variable to number of player who play in the next turn
            switch (numberOfPlayerPlayed){
                case 1:
                    notYourTurn=0;
                    break;
                case 0:
                    notYourTurn=1;
                    break;
            }

            tempLabelText = tabOfLabels[notYourTurn].getText();
            tabOfLabels[notYourTurn].setFont(Font.font(10));
            tabOfLabels[notYourTurn].setText(tempLabelText.substring(10));
        }
        if(counterTurn==0){
        counterTurn++;
        }
    }

}