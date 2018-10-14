package MemoryGame.MainWindow.MainWindowClasses;

import java.util.Random;

public class CardFactor {
    private int[] tabOfCards = new int[12];         //tab of rand memory cards numbers
    private int[] tabOfRandChecker = new int[12];   //tab to compare if memory card number is not double

    public int[] GetTabOfCards()
    {
        return tabOfCards;
    }


    //***************rand memory card method***************//
    public void RandCards()
    {

        Random random = new Random();

        //set the 0 values to tab
        for (int i=0; i<12; i++)
        {
            tabOfRandChecker[i] = 0;
        }

        //first card rand without checking
        tabOfCards[0] = random.nextInt(6);
        tabOfRandChecker[tabOfCards[0]]=1;

        //rand with checking
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
