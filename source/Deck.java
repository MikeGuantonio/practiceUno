/*Source of how many cards in the deck: http://www.squidoo.com/how-many-cards-in-a-deck */

import java.util.*; 

public class Deck
{
    private final int MAX_CARD = 108; 
    private Stack<Card> deck = new Stack();  
    private Stack<Card> discardDeck = new Stack();
    
    public void CreateDeck()
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
			    {
				tmpCard = new Card(color, value, " ");
                                deck.push(tmpCard);
                               
			    }
			else
			    {
				tmpCard = new Card(color, value, " ");
				deck.push(tmpCard);
				tmpCard = new Card(color, value, " "); 
				deck.push(tmpCard); 
			    }
		    }
		}
	    }
	

	    for(Card.cardSpecial special : Card.cardSpecial.values())
	    {
		if(special != Card.cardSpecial.BLANK)
		    {
			tmpCard = new Card(color, special, " ");
			deck.push(tmpCard); 
			tmpCard = new Card(color, special, " ");
			deck.push(tmpCard);
		    }
	    }
	}	
	for(Card.cardWild sp : Card.cardWild.values())
	{
	    for(int k  = 0; k < 4; k++)
		{
		    if(sp != Card.cardWild.BLANK)
			{
			    tmpCard = new Card(sp, " ");
			    deck.push(tmpCard);
			}
		}
	}
	System.out.println("Deck Built"); 
	 
    }

    //TODO: Check for an empty deck. 
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
    
    public void AddDiscard(Card c)
    {
       discardDeck.push(c);
    }
    
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
    
    
    public void PrintDeck()
    {
        for(Card c : deck)
        {
            c.Print();
            System.out.println("");
        }
    }
    
}