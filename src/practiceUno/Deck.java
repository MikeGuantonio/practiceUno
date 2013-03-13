/*Source of how many cards in the deck: http://www.squidoo.com/how-many-cards-in-a-deck */

package practiceUno; 

import java.util.*; 

/**
 *
 * @author mike
 */
public class Deck
{
    enum deckType {DEAL, DISCARD}; 
    
    private final int MAX_CARD = 108; 
    private Stack<Card> deck = new Stack<Card>();  
    private Stack<Card> discardDeck = new Stack<Card>();
    private deckType whichDeck;
    
    /**
     *
     */
    public Deck()
    {   
       CreateNumbers(); 
       CreateSpecial(); 
       CreateWild(); 
    }
    
    private void AddToDeck(Card c, int numberOfCards)
    {
        for(int i = 0; i < numberOfCards; i++)
            deck.push(c);
    }
     
    private void CreateNumbers()
    {
        for(Card.cardColor color : Card.cardColor.values())
        {
                AddToDeck( new NumberCard (0, color), 1);
                for (int i = 1; i < 10; i++) 
                    AddToDeck( new NumberCard (i, color), 2);
        }      
    }
    
    private void CreateSpecial()
    {
        for(SpecialCard.cardValues sp : SpecialCard.cardValues.values())
        {
                for(Card.cardColor color : Card.cardColor.values())
                {
                    AddToDeck(new SpecialCard(sp, color), 2);
                }
        }
    }
    
    private void CreateWild()
    {
        for(WildCard.cardWild wild : WildCard.cardWild.values())
            AddToDeck(new WildCard(wild), 4);
    }

    /**
     *
     * @return
     */
    public Card DrawNext()
    {
        Card c; 
        if(!deck.isEmpty())
            return (Card)deck.pop();
        else
            System.out.println("This deck is done. Need to shuffle and replace deck.");
        return null;

    }
    
    /**
     *
     * @param c
     */
    public void AddDiscard(Card c)
    {
       System.out.println("Looking at the card that was added.");
       c.Print();
        System.out.println("");
       
       if(c.getClass().equals(NumberCard.class))
       {
           System.out.println("Number Card Played");
       }
       else if(c.getClass().equals(SpecialCard.class))
       {
           System.out.println("Special Card Played");
       }
       else if(c.getClass().equals(WildCard.class))
       {
           System.out.println("Wild Card Played");
       }
       else
       {
           System.out.println("No matching cards available");
       }
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
    
    public void ShowDiscard()
    {
        System.out.print("Top Card: ");
        discardDeck.peek().Print();
    }
    
    public void SetUpDiscard()
    {
        discardDeck.add(DrawNext());
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