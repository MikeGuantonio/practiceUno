
/*Source of how many cards in the deck: http://www.squidoo.com/how-many-cards-in-a-deck */
package practiceUno;

//~--- JDK imports ------------------------------------------------------------

import java.io.ByteArrayInputStream;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static practiceUno.WildCard.cardWild.WILD;

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

    
    public int AddDiscard(Card c, ArrayList<Player> p, Scanner in, int pos)
    {  
        boolean canPlace = false;
        int newpos = pos; 
        Card    discard  = discardDeck.peek();
        String cardName = c.getClass().getSimpleName();
        
        canPlace = c.match(discard);
        if(canPlace)
        {    
            switch(cardName)
            {
                case "SpecialCard": SpecialCard sp = (SpecialCard)c;
                                    newpos = sp.PlaySpecial(p, this, pos);
                                    break;
                    
                case "WildCard":    WildCard wild     = (WildCard) c;
                                    wild.PlayWild(in, p, this, pos);
                                    break;
            }
            discardDeck.push(c);
        }
        else
        {
            log.info("No card can be played.");
        }
        return newpos;
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


}
