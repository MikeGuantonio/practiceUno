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
    
    public void Shuffle()
    { 
        try
        {
            Collections.shuffle(deck);
        }
        catch(Exception ex)
        {
        }
       
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
    public boolean AddDiscard(Card c, Player play, Scanner in)
    {
       boolean canPlace = false; 
       Card discard = discardDeck.peek();
       
       if(c.getClass().equals(NumberCard.class))
       {
           canPlace = CheckNumber(discard, c); 
       }
       else if(c.getClass().equals(SpecialCard.class))
       {
           canPlace = CheckSpecial(discard, c);  
       }
       else if(c.getClass().equals(WildCard.class))
       {
           canPlace = CheckWild(discard, c, play, in); 
       }
       else
       {
           System.out.println("No matching cards available");
       }
       if(canPlace)
        discardDeck.push(c);
       return canPlace; 

    }
    
    private boolean CheckNumber(Card discard, Card c)
    {
        boolean canPlace = false; 
        if(discard.getClass().equals(NumberCard.class))
        {
            NumberCard pushNum = (NumberCard) c; 
            NumberCard topCard = (NumberCard) discard; 
               
            if(pushNum.GetColor().equals(topCard.GetColor()) )
            {
                System.out.println("Match color");
                canPlace = true;
            }
            else if(pushNum.GetNumber() == topCard.GetNumber())
            {
                System.out.println("Match number");
                canPlace = true; 
            }
            else
            {
                System.out.print("Cannot play the card");
                c.Print();
                System.out.println("");
            }
         }
         else if(discard.getClass().equals(SpecialCard.class))
         {
             SpecialCard sp = (SpecialCard)discard; 
             NumberCard n = (NumberCard)c;
             
             if(c.GetColor().equals(discard.GetColor()))
                 canPlace = true; 
             else
                 System.out.println("Cannot Play "+ n.GetColor());
         }
         else if(discard.getClass().equals(WildCard.class))
         {
             WildCard w = (WildCard)discard;
             NumberCard n = (NumberCard)c; 
             if(w.GetColor().equals(n.GetColor()))
                 canPlace = true; 
             else 
                 System.out.println("Cannot Place " + n.GetColor());
         }
         return canPlace; 
    }
    
    private boolean CheckSpecial(Card discard, Card c)
    {
        boolean canPlace = false; 
        
        if(discard.getClass().equals(SpecialCard.class))
        {
            System.out.println("Discard is a special card");
            discardDeck.peek().Print();
            System.out.println("");
            
            SpecialCard inPlay = (SpecialCard)c; 
            SpecialCard top = (SpecialCard)discard;
            
            if(c.GetColor().equals(discard.GetColor()))
            {
                System.out.println("Match Color");
                canPlace = true; 
            }
            else if(inPlay.GetSpecial().equals(top.GetSpecial()))
            {
                System.out.println("Match Special");
                canPlace = true; 
            }
        }
        else if(discard.getClass().equals(NumberCard.class))
        {
            if(c.GetColor().equals(discard.GetColor()))
            {
                System.out.println("Can Place on color");
                canPlace = true; 
            }
            else
                System.out.println("Cannot place" + c.GetColor());
        }
        else if(discard.getClass().equals(WildCard.class))
        {
            if(c.GetColor().equals(discard.GetColor()))
                canPlace = true; 
            else
                System.out.println("Cannot place" + c.GetColor());
        }
        else
            System.out.println("No match");
        return canPlace; 
    }
    
    private boolean CheckWild(Card discard, Card c, Player thisPlayer, Scanner in)
    {
        boolean canPlace = true; 
        
        WildCard wild = (WildCard)c;
        
        if(wild.GetWild().equals(WildCard.cardWild.WILD))
        {
            wild.Wild(new Scanner(System.in));
        }
        else if(wild.GetWild().equals(WildCard.cardWild.WILDDRFOUR))
        {
          wild.DrawFour(thisPlayer, this, in);
        }
        
        
        return canPlace; 
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
    public void ShowDiscard()
    {
        discardDeck.peek().Print();
        System.out.println("");
    }
    
    /**
     *
     */
    public void SetUpDiscard(Scanner in)
    {
        discardDeck.add(DrawNext());
        if(discardDeck.peek().getClass().equals(WildCard.class))
        {
            WildCard w = (WildCard)discardDeck.peek();
            w.Wild(in);
        }
    }

    public void testSetupDiscard(Card c)
    {
        discardDeck.add(c);
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
   
    
    /**
     *
     * @param special
     * @param p
     */
    public void SideEffect(SpecialCard special, ArrayList<Player> players, int pos)
  {
      //TODO: cShould check for the Wild Side effect as well
      switch(special.GetSpecial())
      {
          case SKIP: special.Skip(pos, players.size());
              break; 
          case REVERSE: special.Reverse(pos, players.size());
              break;
          case DRTWO: special.DrawTwo(players.get(pos + 1 ), this);
              break;
      }
  }
   
}