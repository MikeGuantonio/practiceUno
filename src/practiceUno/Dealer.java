package practiceUno; 

import java.util.*;

public class Dealer extends Player
{
    
    public Deck Shuffle(Deck d)
    {
        
	long seed = System.nanoTime(); 
	Collections.shuffle(d.GetDeck());
	return d; 
    }

    public void ShowDeck(Deck d)
    {
	for(Card c : d.deck)
	    c.Print(); 
    }

    public Card Deal(Deck d)
    {
	return d.DrawNext(); 
    }
}