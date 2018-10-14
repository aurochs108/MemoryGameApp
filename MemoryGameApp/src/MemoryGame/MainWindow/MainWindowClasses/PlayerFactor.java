package MemoryGame.MainWindow.MainWindowClasses;

public class PlayerFactor {

    private int round = 0;                           //round counter
    private int move = 0;                            //move counter
    private int[] tabOfShowedCards = new int[2];     //numbers of showed cards
    private int[] tabOfCards = new int[12];          //numbers of memory cards


    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int[] getTabOfShowedCards() {
        return tabOfShowedCards;
    }

    public void addMove() {
        move++;

        if (move > 4) {
            addRound();
        }
    }

    public void addRound() {
        round++;
    }

    //***************change values in tab to abstract***************//
    public void setTabOfShowedCardsToAbstract() {
        tabOfShowedCards[0] = 99;
        tabOfShowedCards[1] = 88;
    }

    public void setTabOfCards(int[] tabOfCards) {
        this.tabOfCards = tabOfCards;
    }

    public int[] getTabOfCards() {
        return tabOfCards;
    }

    //***************load number show memory card***************//
    public void showedCardsSaver(int numberOfCard) {
        addMove();

        //set to abstract value of show memory card in tab
        if (move > 4) {
            setTabOfShowedCardsToAbstract();
            move = 1;
        }

        //save the number of showed backcard
        if (move == 1 || move == 3) {
            tabOfShowedCards[0] = numberOfCard;
        }
        if (move == 2 || move == 4) {
            tabOfShowedCards[1] = numberOfCard;
        }
    }

    //***************check if the cards are the same***************//
    public String cardChecker()
    {
        int[] tabOfCards = getTabOfCards();
        int[] showedCardsTab = getTabOfShowedCards();                   //tablica wybranych przez uzytkownika dwoch kart

        //check if the cards are the same or not
        if(tabOfCards[showedCardsTab[0]]==tabOfCards[showedCardsTab[1]])
        {
            return "theSame";
        }
        return "notTheSame";
    }
}
