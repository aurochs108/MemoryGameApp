package MemoryGame.MainWindow.MainWindowClasses;

import java.util.Random;

public class CardFactor {
    private int[] tabOfCards = new int[12];
    private int[] tabOfRandChecker = new int[12];

    public int[] GetTabOfCards()
    {
        return tabOfCards;
    }

    public void RandCards()
    {
        //powolanie klasy do losowania
        Random random = new Random();

        //ustawianie tablicy sprawdzajacej powtorzenia na zero
        for (int i=0; i<12; i++)
        {
            tabOfRandChecker[i] = 0;
        }

        //losowanie pierwszej liczby bez sprawdzania powtorzen, wpisanie liczby do tablicy powtorzen
        tabOfCards[0] = random.nextInt(6);
        tabOfRandChecker[tabOfCards[0]]=1;

        //losowanie liczb ze sprawdzaniem czy powtorzyly sie mniej niz dwa razy
        for (int j=1; j<12; j++)
        {
            int RandomNumberToCards = random.nextInt(6);
            if (tabOfRandChecker[RandomNumberToCards]<2)
            {
                tabOfCards[j] = RandomNumberToCards;
                tabOfRandChecker[RandomNumberToCards]++;
            }
            else
            {
                j--;
            }
        }
    }
}
