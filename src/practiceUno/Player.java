package practiceUno; 

import java.util.*;

/**
 *
 * @author mike
 */
public class Player
{
    private ArrayList<Card> hand = new ArrayList<Card>(); 
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
	hand.add(c);
    }

    /**
     *
     * @param card
     * @return
     */
    public Card Discard(int dex)
    {
        System.out.print("Removing " );
        hand.get(dex).Print();
        System.out.println("");
	return hand.remove(dex);
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

