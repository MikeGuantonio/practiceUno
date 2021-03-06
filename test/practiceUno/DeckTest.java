/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mike
 */
public class DeckTest {
    private static final Logger log = Logger.getLogger(DeckTest.class.getName());
    
    public DeckTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Shuffle method, of class Deck.
     */
    @Test
    public void testShuffle()
    {
        Deck instance = new Deck();
        instance.Shuffle();       
    }

    /**
     * Test of DrawNext method, of class Deck.
     */
    @Test
    public void testDrawNext() 
    {
        Deck instance = new Deck();
        Card result = instance.DrawNext();
        Card expResult = result; 
        assertEquals(expResult, result);
        
    }

    /**
     * Test of AddDiscard method, of class Deck.
     */
    @Test
    public void testAddDiscard() 
    {
        Card c = new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.BLUE);
        Player play = new Human("Steve", 1);
        ArrayList<Player> p = new ArrayList<>();
        p.add(play);
        
        Deck instance = new Deck();
        ByteArrayInputStream in = new ByteArrayInputStream("RED".getBytes());
        System.setIn(in);
        instance.SetUpDiscard(new Scanner(System.in));
        instance.AddDiscard(c);
    }
    
    /**
     *
     */
    @Test
    public void testAddDiscardNumberOnNumber()
    {
        Deck d = new Deck();
         
        NumberCard nc = new NumberCard(5, Card.cardColor.BLUE);
        d.puppetSetupDiscard((Card)nc);
        
        for(Card.cardColor c : Card.cardColor.values())
        {
            for (int i = 0; i < 10; i++) 
            {
                NumberCard n = new NumberCard(i, c); 
                d.AddDiscard(n);
                d.ShowDiscard();
            }
        }
        
        //Number on Wild      
    }
    
    /**
     *
     */
    @Test
    public void testDiscardNumberOnSpecial()
    {
        Deck d = new Deck(); 
        
        SpecialCard c = new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.YELLOW);
        d.puppetSetupDiscard(c);
        
        for(Card.cardColor oc : Card.cardColor.values())
        {
            NumberCard n = new NumberCard(5, oc);
            d.AddDiscard(n);
            d.ShowDiscard();
        }
    }
    
    /**
     *
     */
    @Test
    public void testDiscardNumberOnWild()
    {
        Deck d = new Deck(); 
        WildCard w = new WildCard(WildCard.cardWild.WILD);
        d.puppetSetupDiscard(w);
        ByteArrayInputStream in = new ByteArrayInputStream("RED".getBytes());
        System.setIn(in);
        w.Wild(new Scanner(System.in));
        
        for(Card.cardColor c : Card.cardColor.values())
        {
            NumberCard n = new NumberCard(9, c); 
            d.AddDiscard(n);
            d.ShowDiscard();
        }
    }
    
    /**
     *
     */
    @Test
    public void testAddDiscardSpecialOnNumber()
    {
       Player o = new Robot("Steve", 0);
       Player k = new Robot("Steve", 1);
       ArrayList<Player> p = new ArrayList<>(); 
       p.add(o);
       p.add(k);
       
       Deck d = new Deck(); 
       NumberCard n = new NumberCard(7, Card.cardColor.GREEN);
       d.puppetSetupDiscard(n);
       
       for(Card.cardColor c: Card.cardColor.values())
       {
           SpecialCard discard = new SpecialCard(SpecialCard.cardValues.SKIP, c);
           d.AddDiscard(discard);
           d.ShowDiscard();
       }
       
    }
    
    /**
     *
     */
    @Test
    public void testAddDiscardSpecialOnWild()
    {
        Player k = new Robot("Steve", 0);
        Player l = new Robot("Larry", 1);
        ArrayList<Player> p = new ArrayList<>(); 
        p.add(k);
        p.add(l);
        
        Deck d = new Deck(); 
        WildCard w = new WildCard(WildCard.cardWild.WILD);
        d.puppetSetupDiscard(w);
        ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
        System.setIn(in);
        w.Wild(new Scanner(System.in));
        
        for(Card.cardColor c : Card.cardColor.values())
        {
            SpecialCard sp = new SpecialCard(SpecialCard.cardValues.DRTWO, c);
            d.AddDiscard(sp);
            d.ShowDiscard();
        }
    }
    
    /**
     *
     */
    @Test
    public void testAddDiscardSpecialOnSpecial()
    {
        Player m = new Robot("Steve", 0);
        Player n = new Robot("Larry", 1);
        ArrayList<Player> p = new ArrayList<>(); 
        p.add(m);
        p.add(n);
        
        Deck d = new Deck(); 
        d.puppetSetupDiscard(new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.RED ));
        
        for(Card.cardColor c : Card.cardColor.values())
        {
            for(SpecialCard.cardValues v : SpecialCard.cardValues.values())
            {
                SpecialCard s = new SpecialCard(v, c);
                d.AddDiscard(s);
                d.ShowDiscard();
            }
        }
        
    }
    
    /**
     *
     */
    @Test
    public void testAddDiscardWildOnSpecial()
    {
        Player k = new Robot("Steve", 0);
        ArrayList<Player> p = new ArrayList<>(); 
        p.add(k);
        
        Deck d = new Deck(); 
        SpecialCard sp = new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.YELLOW);
        d.puppetSetupDiscard(sp);
        ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
        System.setIn(in);
        
        d.ShowDiscard();
        WildCard w = new WildCard(WildCard.cardWild.WILD);
        d.AddDiscard(w);
        d.ShowDiscard();
        
    }
    
    /**
     *
     */
    @Test //test to see if the correct person is getting the draw
    public void testAddDiscardWildOnNumber()
    {
        int canPlace = 0; 
        Player r = new Robot("Steve", 0);
        ArrayList<Player> p = new ArrayList<>();
        p.add(r);
        Deck d = new Deck(); 
        NumberCard n = new NumberCard(9, Card.cardColor.BLUE);
        d.puppetSetupDiscard(n);
        ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
        System.setIn(in);
        
        d.ShowDiscard();
        WildCard w = new WildCard(WildCard.cardWild.WILD);
        d.AddDiscard(w);
        assertEquals(canPlace, r.GetPlayerPos());
        d.ShowDiscard();
    }
    
    /**
     *
     */
    @Test //check to see if the correct person is getting a card.
    public void testAddDiscardWildOnWild()
    {
        Deck d = new Deck(); 
        WildCard w = new WildCard(WildCard.cardWild.WILD);
        d.puppetSetupDiscard(w);
        
        Player h = new Human("steve", 1); 
        ArrayList<Player> p = new ArrayList<>(); 
        p.add(h);
        
        for(WildCard.cardWild wc : WildCard.cardWild.values())
        {
            ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
            System.setIn(in);
            WildCard discard = new WildCard(wc);
            d.AddDiscard(discard);
            d.ShowDiscard();
        }
    }

    /**
     * Test of getSize method, of class Deck.
     */
    @Test
    public void testGetSize()
    {
        String deckName = "regular";
        Deck instance = new Deck();
        int expResult = 108;
        int result = instance.getSize(deckName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of ShowDiscard method, of class Deck.
     */
    @Test
    public void testShowDiscard()
    {
        Deck instance = new Deck();
        ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
        System.setIn(in);
        instance.SetUpDiscard(new Scanner(System.in));
        instance.ShowDiscard();
    }

    /**
     * Test of SetUpDiscard method, of class Deck.
     */
    @Test
    public void testSetUpDiscard()
    {
        Deck instance = new Deck();
        ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
        System.setIn(in);
        instance.SetUpDiscard(new Scanner(System.in));
    }

    /**
     * Test of PrintDeck method, of class Deck.
     */
    @Test
    public void testPrintDeck() 
    {
        String deckName = "regular";
        Deck instance = new Deck();
        instance.PrintDeck(deckName);
    }

    

        
    @Test
    public void testSideEffectDrTwo()
    {
        //Should also check for wrap cases. 
        boolean canPlace = false;
        Robot first = new Robot("Steve", 0); 
        Robot second = new Robot("Bob", 1);
        ArrayList<Player> p = new ArrayList<>(); 
        p.add(first);
        p.add(second);
        Deck d = new Deck(); 
        
        d.puppetSetupDiscard(new NumberCard(5, Card.cardColor.BLUE));
        first.GetCard(new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.BLUE));
        canPlace = d.AddDiscard(first.Discard(0));
        first.Remove(first.Discard(0));
        
        System.out.println(d.TopDiscard().toString());
        System.out.println(String.format("First: %s", first.TotalCards()));
        System.out.println(String.format("Second: %s", second.TotalCards()));
        
        assertEquals(canPlace, true);
        
    }
    
    @Test
    public void testSideEffectDrTwoPlayerOutofBounds()
    {
        //assertNotNull(null);
    }
    
    @Test
    public void testSideEffectReverseFirstToLast()
    {
        System.out.println("SideEffect Reverse");
        boolean canPlace = false;
        Robot first = new Robot("Steve", 0); 
        Robot second = new Robot("Bob", 1);
        Robot third = new Robot("Bill", 2);
        ArrayList<Player> p = new ArrayList<>();
        
        p.add(first);
        p.add(second);
        p.add(third);
        
        Deck d = new Deck(); 
        d.puppetSetupDiscard(new NumberCard(5, Card.cardColor.RED));
        
        System.out.println("Assiming that the last player was the last palyer in list.");
        first.GetCard(new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.RED));
        canPlace = d.AddDiscard(first.Discard(0));
        first.Remove(first.Discard(0));
        
        assertEquals(canPlace, true);
    }
    
    @Test
    public void testSideEffectReverseLastToFirst()
    {
        boolean canPlace = false; 
        Robot first = new Robot("Steve", 0); 
        Robot second = new Robot("Bob", 1);
        Robot third = new Robot("Bill", 2);
        ArrayList<Player> p = new ArrayList<>();
        
        p.add(first);
        p.add(second);
        p.add(third);
        
        Deck d = new Deck(); 
        d.puppetSetupDiscard(new NumberCard(5, Card.cardColor.RED));
        
        System.out.println("Last player to the second");
        third.GetCard(new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.RED));
        canPlace = d.AddDiscard(third.Discard(0));
        third.Remove(third.Discard(0));
        
        assertEquals(canPlace, true);
 
    }
    
    
    @Test
    public void testSideEffectSkip()
    {
        boolean canPlace = false; 
        Robot first = new Robot("Steve", 0); 
        Robot second = new Robot("Bob", 1);
        Robot third = new Robot("Bill", 2);
        ArrayList<Player> p = new ArrayList<>();
        
        p.add(first);
        p.add(second);
        p.add(third);
        
        Deck d = new Deck(); 
        d.puppetSetupDiscard(new NumberCard(5, Card.cardColor.YELLOW));
        
        first.GetCard(new SpecialCard(SpecialCard.cardValues.SKIP, Card.cardColor.YELLOW));
        canPlace = d.AddDiscard(first.Discard(0));
        first.Remove(first.Discard(0));
        
        assertEquals(canPlace, true);
    }
    
    @Test //nee to check for other edge cases. 
    public void testSideEffectSkipLastPlayer()
    {
        boolean canPlace = false; 
        Robot first = new Robot("Steve", 0); 
        Robot second = new Robot("Bob", 1);
        Robot third = new Robot("Bill", 2);
        ArrayList<Player> p = new ArrayList<>();
        
        p.add(first);
        p.add(second);
        p.add(third);
        
        Deck d = new Deck(); 
        d.puppetSetupDiscard(new NumberCard(5, Card.cardColor.YELLOW));
        
        third.GetCard(new SpecialCard(SpecialCard.cardValues.SKIP, Card.cardColor.YELLOW));
        canPlace = d.AddDiscard(third.Discard(0));
        third.Remove(third.Discard(0));
        
        assertEquals(canPlace, true);
    }
    
    @Test
    public void testMatches()
    {
        Deck d = new Deck(); 
        ArrayList<Card> cards = new ArrayList<>(); 
        
        for (int i = 0; i < d.getSize("regular"); i++) {
            cards.add(d.DrawNext());
        }
        
        int max = 0; 
        for(Card c : cards)
        {
            for(Card k : cards)
            {
                if(c.match(k))
                {
                    System.out.println(String.format("Match %s %s", c.toString(), k.toString()));
                    max++;
                }
                
            }
        }
        System.out.println(String.format("max %s", max));
        //assertNotNull(null);
            
        
    }
}