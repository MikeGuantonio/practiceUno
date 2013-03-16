package practiceUno; 

import java.util.*;

/**
 *
 * @author mike
 */
public class Player
{
    private ArrayList<Card> hand = new ArrayList<Card>(); 
    private String name; 
    private int playerPos; 
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
    
    public void SetPlayerPos(int newPos)
    {
        playerPos = newPos; 
    }
    
    public int GetPlayerPos()
    {
        return playerPos; 
    }
    
    public void NextAction()
    {
        System.out.println("What do you want to do next?");
    }
    
    public void SetName(String newName)
    {
        name = newName; 
    }
    
    public String GetName()
    {
        return name; 
    }
}

