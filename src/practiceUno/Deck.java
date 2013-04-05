
/*Source of how many cards in the deck: http://www.squidoo.com/how-many-cards-in-a-deck */
package practiceUno;

//~--- JDK imports ------------------------------------------------------------

import java.io.ByteArrayInputStream;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class Deck {
    private static final Logger log         = Logger.getLogger(Deck.class.getName());
    private Stack<Card>         deck        = new Stack<>();
    private Stack<Card>         discardDeck = new Stack<>();

    /**
     *
     */
    public Deck()
    {
        CreateNumbers();
        CreateSpecial();
        CreateWild();
        log.setLevel(Level.SEVERE);
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
        catch (Exception ex)
        {
            log.severe("There is no valid deck to shuffle!");
        }
    }

    private void AddToDeck(Card c, int numberOfCards)
    {
        for (int i = 0; i < numberOfCards; i++)
        {
            deck.push(c);
        }
    }

    private void CreateNumbers()
    {
        for (Card.cardColor color : Card.cardColor.values())
        {
            AddToDeck(new NumberCard(0, color), 1);

            for (int i = 1; i < 10; i++)
            {
                AddToDeck(new NumberCard(i, color), 2);
            }
        }
    }

    private void CreateSpecial()
    {
        for (SpecialCard.cardValues sp : SpecialCard.cardValues.values()) 
        {
            for (Card.cardColor color : Card.cardColor.values()) 
            {
                AddToDeck(new SpecialCard(sp, color), 2);
            }
        }
    }

    private void CreateWild() 
    {
        for (WildCard.cardWild wild : WildCard.cardWild.values()) 
        {
            AddToDeck(new WildCard(wild), 4);
        }
    }

    /**
     *
     * @return
     */
    public Card DrawNext()
    {
        Card c = null;

        if (!deck.isEmpty())
        {
            c = (Card) deck.pop();
        } 
        else 
        {
            log.info("Need to shuffle deck");    
            deck.clear();
            deck = (Stack) discardDeck.clone();
            Shuffle();
            discardDeck.clear();
            discardDeck.add(deck.pop());
            log.info(String.format("New top Card is : %s", discardDeck.peek().toString()));
            log.info(String.format("New deck size is %s", deck.size()));
        }
        return c;
    }

    public class thisthing
    {
        int foo; 
        
        public void Print()
        {
            System.out.println("foo");
        }
    }
    /**
     *
     * @param c
     * @param play
     * @param in
     * @return
     */
    public int AddDiscard(Card c, ArrayList<Player> p, Scanner in, int pos)
    {
        thisthing t = new thisthing(); 
        t.Print();
        System.out.println("At function add discard");
        boolean canPlace = false;
        int newpos = pos; 
        Card    discard  = discardDeck.peek();
        String cardName = c.getClass().getSimpleName();
        
        System.out.println("Card name is " + cardName); 
        switch(cardName)
        {
            case "NumberCard" : System.out.println("Enter a number card");
                                newpos = CheckNumber(discard, c, pos); 
                                break;
                
            case "SpecialCard": System.out.println("Entering a special card");
                                 newpos = CheckSpecial(discard, c, p, pos);
                                 System.out.println("New pos " + newpos);
                                 break;
                
            case "WildCard": System.out.println("Entering a wild card");
                             canPlace = CheckWild(c, p.get(pos), in);
                             break;
            
            default: System.out.println("Cannot place.");
                     log.fine("The card cannot be placed. ");
                     break;
        }

        if (canPlace)
        {
            discardDeck.push(c);
        }
        System.out.println("Exiting function Add discard");
        return newpos;
    }

    private int CheckNumber(Card discard, Card c, int pos)
    {
        boolean    canPlace   = false;
        NumberCard cardToPlay = (NumberCard) c;
        String cardName = discard.getClass().getSimpleName();
        
        System.out.println("Entering a number check");
        switch(cardName)
        {
            case "NumberCard":  NumberCard topCard = (NumberCard) discard;

                                if (cardToPlay.GetColor().equals(topCard.GetColor())) 
                                {
                                    canPlace = true;
                                }
                                else if (cardToPlay.GetNumber() == topCard.GetNumber()) 
                                {
                                    canPlace = true;
                                } 
                                else 
                                {
                                    log.info("Tried to place a number on a number");
                                    log.info(discard.toString());
                                }
                                break;
            
            case "SpecialCard": if (cardToPlay.GetColor().equals(discard.GetColor())) 
                                {
                                    canPlace = true;
                                }
                                else
                                {
                                    log.info("Tried to place a number card on special");
                                    log.info(discard.toString());
                                } 
                                break;
            
            case "WildCard":    if (cardToPlay.GetColor().equals(discard.GetColor())) 
                                {
                                    canPlace = true;
                                }
                                else
                                {
                                    log.info("Tried to place a number card on wild.");
                                    log.info(discard.toString());
                                }
                                break;
            
            default: log.fine("No number can be played. ");
                     break;
        }
        
        if(canPlace)
            discardDeck.push(c);
        
        return pos;
    }

    private int CheckSpecial(Card discard, Card c, ArrayList<Player> players, int pos)
    {
        System.out.println("Checking special");
        boolean canPlace = false;
        String cardName = discard.getClass().getSimpleName();
        int newpos = pos; 
        
        switch(cardName)
        {
            case "SpecialCard" : if (c.GetColor().equals(discard.GetColor()))
                                {
                                    canPlace = true;
                                    newpos = this.SideEffect(c, players, pos);
                                }
                                else if (c.getClass().equals(SpecialCard.class)) 
                                {
                                    SpecialCard cardToPlay = (SpecialCard) c;
                                    SpecialCard cardShown  = (SpecialCard) discard;

                                    if (cardToPlay.GetSpecial().equals(cardShown.GetSpecial())) 
                                    {
                                        canPlace = true;
                                        newpos = this.SideEffect(c, players, pos);
                                    }
                                }
                                break;
                
            case "NumberCard": if (c.GetColor().equals(discard.GetColor())) 
                                {
                                    canPlace = true;
                                    newpos = this.SideEffect(c, players, pos);
                                    System.out.println("Number card");
                                } 
                                break;
                
            case "WildCard":    if (c.GetColor().equals(discard.GetColor())) 
                                {
                                    canPlace = true;
                                    newpos = this.SideEffect(c, players, pos);
                                }
                                break;
                
            default: log.fine("No cards are available to be played.");
                     break;
        }
        System.out.println("End check special");
        System.out.println(newpos);
        if(canPlace)
            discardDeck.push(c);
        
        return newpos;
    }

    private boolean CheckWild(Card c, Player thisPlayer, Scanner in) 
    {
        boolean  canPlace = true;
        WildCard wild     = (WildCard) c;
        
        switch(wild.GetWild())
        {
            case WILD : wild.Wild(new Scanner(System.in));
                        break;
            
            case WILDDRFOUR: wild.DrawFour(thisPlayer, this, in); 
                          break;
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
        switch (deckName)
        {
            case "discard": size = discardDeck.size();
                            break;
            case "regular": size = deck.size();
                            break;
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
    public Card TopDiscard()
    {
        return discardDeck.peek();
    }

    /**
     *
     * @param in
     */
    public void SetUpDiscard(Scanner in) 
    {
        log.info("Setting up discard.");
        discardDeck.add(DrawNext());

        if (discardDeck.peek().getClass().equals(WildCard.class))
        {
            ByteArrayInputStream choose = new ByteArrayInputStream("RED".getBytes());
            System.setIn(choose);
            WildCard w = (WildCard) discardDeck.peek();
            w.Wild(new Scanner(System.in));
        }

        log.info(String.format("Deck shows: %s", TopDiscard()));
    }

    /**
     *
     * @param c
     */
    public void puppetSetupDiscard(Card c) 
    {
        System.out.println(String.format("Adding: %s", c.toString()));
        discardDeck.add(c);
    }

    /**
     *
     * @param deckName
     */
    public void PrintDeck(String deckName)
    {
        Stack<Card> tmpDeck = new Stack<>();
        switch (deckName)
        {
            case "discard": tmpDeck = discardDeck;
                            break;
            case "regular": tmpDeck = deck;
                            break;
        }

        for (Card c : tmpDeck)
        {
            c.Print();
        }
    }


    /**
     *
     * @param c
     * @param players
     * @param pos
     */
    public int SideEffect(Card c, ArrayList<Player> players, int pos)
    {
        System.out.println("Entering Side effect " + c.toString());
        int newPos = 0; 
        if (c.getClass().equals(SpecialCard.class))
        {
            SpecialCard special = (SpecialCard) c;

            switch (special.GetSpecial())
            {
                case SKIP : newPos = special.Skip(pos, players.size());
                            break;

                case REVERSE: newPos = special.Reverse(pos, players.size());
                              break;

                case DRTWO : newPos = special.DrawTwo(this, players.get(pos + 1));
                             break;

                default : log.severe("This is not a card with a side effect");
                          break;
            }
        }
        return newPos; 
    }

}
