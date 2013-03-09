/*Source of how many cards in the deck: http://www.squidoo.com/how-many-cards-in-a-deck */

import java.util.*;

public class Deck
{
    private final int MAX_CARD = 108;
    public int totalCardNotPlayed = MAX_CARD; 
    private int discard = 0; 
    public ArrayList<Card> deck = new ArrayList<Card>(); 

    public void ValidateDeck()
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
				deck.add(tmpCard);  
			    }
			else
			    {
				tmpCard = new Card(color, value, " ");
				deck.add(tmpCard); 
				tmpCard = new Card(color, value, " "); 
				deck.add(tmpCard); 
			    }
		    }
		}
	    }
	

	    for(Card.cardSpecial special : Card.cardSpecial.values())
	    {
		if(special != Card.cardSpecial.BLANK)
		    {
			tmpCard = new Card(color, special, " ");
			deck.add(tmpCard); 
			tmpCard = new Card(color, special, " ");
			deck.add(tmpCard);
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
			    deck.add(tmpCard);
			}
		}
	}
	System.out.println("Deck Built"); 
	 
    }

    public Card DrawNext()
    {
	Card c = deck.get(0);
	deck.remove(0); 
	totalCardNotPlayed--; 
	return c; 
    }
    
   
}