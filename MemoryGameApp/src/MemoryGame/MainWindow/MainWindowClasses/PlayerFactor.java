package MemoryGame.MainWindow.MainWindowClasses;

public class PlayerFactor {

    private int round = 0;
    private int move = 0;
    private int[] tabOfShowedCards = new int[2];
    private int[] tabOfCards = new int[12];

    //*********************************************gettery settery
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

    //dodawanie ruchów
    public void addMove() {
        move++;
        if (move > 4) {
            addRound();
        }
    }

    //dodawanie rundy
    public void addRound() {
        round++;
    }

    //*******************************************zamiana wartosci w tablicy ukazanych kart na wartosci abstrakcyjne
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

    //*******************************************wczytywanie numeru odkrytej karty
    public void showedCardsSaver(int numberOfCard) {
        addMove();
        //*****************************************usuwanie danych z tablicy
        if (move > 4) {
            setTabOfShowedCardsToAbstract();
            move = 1;
        }
        if (move == 1 || move == 3) {
            tabOfShowedCards[0] = numberOfCard;
        }
        if (move == 2 || move == 4) {
            tabOfShowedCards[1] = numberOfCard;
        }
    }
    //sprawdzanie czy karty sa takie same
    public String cardChecker()
    {
        int[] tabOfCards = getTabOfCards();                             //przekazanie tablicy z wylosowanymi numerami kart
        int[] showedCardsTab = getTabOfShowedCards();                   //tablica wybranych przez uzytkownika dwoch kart

        if(tabOfCards[showedCardsTab[0]]==tabOfCards[showedCardsTab[1]])
        {
            return "theSame";
        }
        return "notTheSame";
    }
}
