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
        {
	    c.Print(); 
            System.out.println("");
        }
    }
    
    //Dealer's deal method may be able to check to see if the deck needs
    //to be reshuffled first
}
    