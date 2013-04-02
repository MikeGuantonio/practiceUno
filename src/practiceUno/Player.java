package practiceUno; 

import java.util.*;

/**
 *
 * @author mike
 */
abstract class Player
{
    protected ArrayList<Card> hand = new ArrayList<Card>(); 
    protected String name; 
    protected int playerPos; 
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
     * @param card
     * @return
     */
    abstract Card Discard(int dex);
    

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

