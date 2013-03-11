/*Source of how many cards in the deck: http://www.squidoo.com/how-many-cards-in-a-deck */

import java.util.*;

public class Deck
{
    private final int MAX_CARD = 108;
    private int discard = 0; 
    
    public int totalCardNotPlayed = MAX_CARD;
    public Stack newDeck = new Stack();  
    
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
                                newDeck.push(tmpCard);
                               
			    }
			else
			    {
				tmpCard = new Card(color, value, " ");
				newDeck.push(tmpCard);
				tmpCard = new Card(color, value, " "); 
				newDeck.push(tmpCard); 
			    }
		    }
		}
	    }
	

	    for(Card.cardSpecial special : Card.cardSpecial.values())
	    {
		if(special != Card.cardSpecial.BLANK)
		    {
			tmpCard = new Card(color, special, " ");
			newDeck.push(tmpCard); 
			tmpCard = new Card(color, special, " ");
			newDeck.push(tmpCard);
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
			    newDeck.push(tmpCard);
			}
		}
	}
	System.out.println("Deck Built"); 
	 
    }

    public Card DrawNext()
    {
	Card c = newDeck.pop();  
	totalCardNotPlayed--; 
	return c; 
    }
    
   
}