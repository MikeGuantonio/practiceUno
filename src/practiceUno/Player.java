package practiceUno; 

import java.util.*;

/**
 *
 * @author mike
 */
abstract class Player //be a good idea to put the play a hand sm in here and inherit.
{
    protected ArrayList<Card> hand = new ArrayList<>(); 
    protected String name; 
    protected int playerPos; 
    
    abstract boolean PlayAHand(Deck d, ArrayList<Player> p); 
    abstract int FindCard(Card c); 
    abstract Card Discard(int dex);
    
    /**
     *
     */
    public void ShowHand()
    {
        
	for(Card c : hand)
	{
	    c.Print();
	}
    }

    public boolean Uno()
    {
        if(hand.size() == 1)
            return true; 
        else
            return false; 
    }
    /**
     *
     * @param c
     */
    public void GetCard(Card c)
    {
	hand.add(c);
    }

    
    /**
     *
     * @return
     */
    public int TotalCards()
    {
	return hand.size(); 
    }
    
    public int GetPlayerPos()
    {
        return playerPos; 
    }
     
    public String GetName()
    {
        return name; 
    }
}

