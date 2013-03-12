/*Source of how many cards in the deck: http://www.squidoo.com/how-many-cards-in-a-deck */

package practiceUno; 

import java.util.*; 

/**
 *
 * @author mike
 */
public class Deck
{
    private final int MAX_CARD = 108; 
    private Stack<Card> deck = new Stack<Card>();  
    private Stack<Card> discardDeck = new Stack<Card>();
    
    
    /**
     *
     */
    public Deck()
    {   
        CreateDeck(); 
    }
    
    private void AddToDeck(Card c, int numberOfCards)
    {
        for(int i = 0; i < numberOfCards; i++)
            deck.push(c);
    }
     
    private void CreateDeck()
    {
	Card tmpCard; 

	for(Card.cardColor color : Card.cardColor.values())
	{
	    for(Card.cardFace value : Card.cardFace.values())
	    {
		if(color != Card.cardColor.BLANK)
		{
		    if(value != Card.cardFace.BLANK)
		    {
			if(value == Card.cardFace.ZERO)
                            AddToDeck(new Card(color, value, ""), 1);
			else
                            AddToDeck(new Card(color, value, ""), 2);
		    }
		}
	    }
	

	    for(Card.cardSpecial special : Card.cardSpecial.values())
	    {
		if(special != Card.cardSpecial.BLANK)
			AddToDeck(new Card(color, special, ""), 2);
	    }
	}
        
	for(Card.cardWild sp : Card.cardWild.values())
        {
		    if(sp != Card.cardWild.BLANK)
                        AddToDeck(new Card(sp, ""), 4);	
        }
        if(deck.size()== MAX_CARD)
            System.out.println("Deck is correct");
        else
            System.out.println("Size: " + deck.size());
	 
    }

    /**
     *
     * @return
     */
    public Card DrawNext()
    {
        Card c = new Card(Card.cardColor.BLANK, Card.cardFace.BLANK, "" ); 
        if(!deck.isEmpty())
        {
            c = (Card)deck.pop();
        }
        else
        {
            System.out.println("This deck is done. Need to shuffle and replace deck.");
        }
	return c; 
    }
    
    /**
     *
     * @param c
     */
    public void AddDiscard(Card c)
    {
       discardDeck.push(c);
    }
    
    /**
     *
     * @param deckName
     * @return
     */
    public int getSize(String deckName)
    {
       int size = 0; 
       if(deckName.equals("discard"))
       {
           size = discardDeck.size(); 
       }
       else if(deckName.equals("regular"))
       {
           size = deck.size(); 
       }
        return size; 
    }
    
    /**
     *
     */
    public void PrintDeck(String deckName)
    {  
        Stack<Card> tmpDeck = new Stack<Card>(); 
        if(deckName.equals("discard"))
            tmpDeck = discardDeck; 
        else if(deckName.equals("regular"))
            tmpDeck = deck; 
        
        for(Card c : tmpDeck)
        {
            c.Print();
            System.out.println("");
        }
    }
    
    /**
     *
     * @return
     */
    public Stack<Card> GetDeck() 
   {
       return deck; 
   }
   
}