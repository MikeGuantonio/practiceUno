/*Source of how many cards in the deck: http://www.squidoo.com/how-many-cards-in-a-deck */

package practiceUno; 

import java.util.*; 
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class Deck
{
    private static final Logger log = Logger.getLogger(Deck.class.getName()); 
    
    private Stack<Card> deck = new Stack<Card>();  
    private Stack<Card> discardDeck = new Stack<Card>();
    
    /**
     *
     */
    public Deck()
    {   
       CreateNumbers(); 
       CreateSpecial(); 
       CreateWild(); 
      
    }
    
    /**
     *
     */
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
        {
            log.info("Need to shuffle deck");
            deck.clear();
            deck = (Stack)discardDeck.clone(); 
            Shuffle();
            discardDeck.clear();
            discardDeck.add(deck.pop());
            log.info(discardDeck.peek().toString());
            log.info(deck.size()+"");
        }
        return null;

    }
    
    /**
     *
     * @param c
     * @param play 
     * @param in 
     * @return  
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
                canPlace = true;
            }
            else if(pushNum.GetNumber() == topCard.GetNumber())
            {
                canPlace = true; 
            }
            else
            {
                c.Print();
            }
         }
         else if(discard.getClass().equals(SpecialCard.class))
         {
             SpecialCard sp = (SpecialCard)discard; 
             NumberCard n = (NumberCard)c;
         }
         else if(discard.getClass().equals(WildCard.class))
         {
             WildCard w = (WildCard)discard;
             NumberCard n = (NumberCard)c; 
         }
         return canPlace; 
    }
    
    private boolean CheckSpecial(Card discard, Card c)
    {
        boolean canPlace = false; 
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
    }
    
    /**
     *
     * @return
     */
    public Card TopCard()
    {
        return discardDeck.peek();
    }
    /**
     *
     * @param in 
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

    /**
     *
     * @param c
     */
    public void testSetupDiscard(Card c)
    {
        System.out.println("Adding: " + c.toString());
        discardDeck.add(c);
    }
    
    /**
     *
     * @param deckName 
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
     * @param c 
     * @param players
     * @param pos  
     */
    public void SideEffect(Card c, ArrayList<Player> players, int pos)
  {
      if(c.getClass().equals(SpecialCard.class))
      {
        SpecialCard special = (SpecialCard)c; 
        switch(special.GetSpecial())
        {
          case SKIP: special.Skip(pos, players.size());
              break; 
          case REVERSE: special.Reverse(pos, players.size());
              break;
          case DRTWO: special.DrawTwo(this, players.get(pos + 1));
              break;
        }
      }
  }
    enum deckType {DEAL, DISCARD};
   
}