package practiceUno; 

import java.util.*;

/**
 *
 * @author mike
 */
public class Player
{
    private ArrayList<Card> hand = new ArrayList<Card>(); 
    private int totalCards = 0; 
    private int isDealer; 
    private String name; 

    /**
     *
     */
    public void ShowHand()
    {
	System.out.println("I have " + hand.size() + " cards.");
	for(Card c : hand)
	{
	    c.Print();
	    System.out.println(""); 
	}
    }

    /**
     *
     * @param c
     */
    public void GetCard(Card c)
    {
	System.out.print("I recieved " );
	c.Print(); 
	hand.add(c);
	totalCards = hand.size(); 
	System.out.println(" My Total is: " + totalCards);
        
         
    }

    /**
     *
     * @param card
     * @return
     */
    public boolean Discard(Card card)
    {
        totalCards--; 
	return hand.remove(card);
        
    }

    /**
     *
     * @return
     */
    public int TotalCards()
    {
	return hand.size(); 
    }
}

