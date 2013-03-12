package practiceUno; 

import java.util.*;

public class Dealer extends Player
{
    
    public Deck Shuffle(Deck d)
    { 
	Collections.shuffle(d.GetDeck());
	return d; 
    }

    /**
     *
     * @param d
     */
    public void ShowDeck(Deck d)
    {
	for(Card c : d.GetDeck())
	    c.Print();
    }

    /**
     *
     * @param d
     * @return
     */
    public Card Deal(Deck d)
    {
	return d.DrawNext(); 
    }
}