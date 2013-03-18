/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mike
 */
public class DeckTest {
    
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
        System.out.println("Shuffle");
        Deck instance = new Deck();
        instance.Shuffle();       
    }

    /**
     * Test of DrawNext method, of class Deck.
     */
    @Test
    public void testDrawNext() 
    {
        System.out.println("DrawNext");
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
        System.out.println("AddDiscard");
        Card c = new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.BLUE);
        Player play = new Player();
        Deck instance = new Deck();
        Scanner in = new Scanner(System.in);
        instance.SetUpDiscard(in);
        instance.AddDiscard(c, play, new Scanner(System.in));
    }
    
    @Test
    public void testAddDiscardNumberOnNumber()
    {
        System.out.println("AddDiscard Number and Number");
        Deck d = new Deck();
         
        //Number on Number
        NumberCard nc = new NumberCard(5, Card.cardColor.BLUE);
        d.testSetupDiscard(nc);
        
        for(Card.cardColor c : Card.cardColor.values())
        {
            for (int i = 0; i < 10; i++) 
            {
                NumberCard n = new NumberCard(i, c); 
                d.AddDiscard(n, null, new Scanner(System.in));
                d.ShowDiscard();
            }
        }
        
        //Number on Wild      
    }
    
    @Test
    public void testDiscardNumberOnSpecial()
    {
        System.out.println("Discard Number on Special");
        Deck d = new Deck(); 
        
        SpecialCard c = new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.YELLOW);
        d.testSetupDiscard(c);
        
        for(Card.cardColor oc : Card.cardColor.values())
        {
            NumberCard n = new NumberCard(5, oc);
            d.AddDiscard(n, null, new Scanner(System.in));
            d.ShowDiscard();
        }
    }
    
    @Test
    public void testDiscardNumberOnWild()
    {
        System.out.println("Discard Number on Wild");
        Deck d = new Deck(); 
        WildCard w = new WildCard(WildCard.cardWild.WILD);
        d.testSetupDiscard(w);
        ByteArrayInputStream in = new ByteArrayInputStream("RED".getBytes());
        System.setIn(in);
        w.Wild(new Scanner(System.in));
        
        for(Card.cardColor c : Card.cardColor.values())
        {
            NumberCard n = new NumberCard(9, c); 
            d.AddDiscard(n, null, new Scanner(System.in));
            d.ShowDiscard();
        }
    }
    
    @Test
    public void testAddDiscardSpecialOnNumber()
    {
        System.out.println("Add Discard Special on Number");
       Deck d = new Deck(); 
       NumberCard n = new NumberCard(7, Card.cardColor.GREEN);
       d.testSetupDiscard(n);
       
       for(Card.cardColor c: Card.cardColor.values())
       {
           SpecialCard discard = new SpecialCard(SpecialCard.cardValues.SKIP, c);
           d.AddDiscard(discard, null, new Scanner(System.in));
           d.ShowDiscard();
       }
       
    }
    
    @Test
    public void testAddDiscardSpecialOnWild()
    {
        System.out.println("Add Discard on Special on Wild");
        Deck d = new Deck(); 
        WildCard w = new WildCard(WildCard.cardWild.WILD);
        d.testSetupDiscard(w);
        ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
        System.setIn(in);
        w.Wild(new Scanner(System.in));
        
        for(Card.cardColor c : Card.cardColor.values())
        {
            SpecialCard sp = new SpecialCard(SpecialCard.cardValues.DRTWO, c);
            d.AddDiscard(sp, null, new Scanner(System.in));
            d.ShowDiscard();
        }
    }
    
    @Test
    public void testAddDiscardSpecialOnSpecial()
    {
        System.out.println("Add Discard Special On Special");
        Deck d = new Deck(); 
        d.testSetupDiscard(new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.RED ));
        
        for(Card.cardColor c : Card.cardColor.values())
        {
            for(SpecialCard.cardValues v : SpecialCard.cardValues.values())
            {
                SpecialCard s = new SpecialCard(v, c);
                d.AddDiscard(s, null, new Scanner(System.in));
                d.ShowDiscard();
            }
        }
        
    }
    
    @Test
    public void testAddDiscardWildOnSpecial()
    {
        System.out.println("Add Discard Wild on Special");
        Deck d = new Deck(); 
        SpecialCard sp = new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.YELLOW);
        d.testSetupDiscard(sp);
        ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
        System.setIn(in);
        
        d.ShowDiscard();
        WildCard w = new WildCard(WildCard.cardWild.WILD);
        d.AddDiscard(w, null, new Scanner(System.in));
        d.ShowDiscard();
        
    }
    
    @Test
    public void testAddDiscardWildOnNumber()
    {
        System.out.println("Add Discard Wild on Number");
        Deck d = new Deck(); 
        NumberCard n = new NumberCard(9, Card.cardColor.BLUE);
        d.testSetupDiscard(n);
        ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
        System.setIn(in);
        
        d.ShowDiscard();
        WildCard w = new WildCard(WildCard.cardWild.WILD);
        d.AddDiscard(w, null, new Scanner(System.in));
        d.ShowDiscard();
    }
    
    @Test
    public void testAddDiscardWildOnWild()
    {
        System.out.println("Add Discard Wild on Wild");
        Deck d = new Deck(); 
        WildCard w = new WildCard(WildCard.cardWild.WILD);
        d.testSetupDiscard(w);
        
        Player p = new Player(); 
        
        for(WildCard.cardWild wc : WildCard.cardWild.values())
        {
            ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
            System.setIn(in);
            WildCard discard = new WildCard(wc);
            d.AddDiscard(discard, p, new Scanner(System.in));
            d.ShowDiscard();
        }
    }

    /**
     * Test of getSize method, of class Deck.
     */
    @Test
    public void testGetSize()
    {
        System.out.println("getSize");
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
        System.out.println("ShowDiscard");
        Deck instance = new Deck();
        Scanner in = new Scanner(System.in);
        instance.SetUpDiscard(in);
        instance.ShowDiscard();
    }

    /**
     * Test of SetUpDiscard method, of class Deck.
     */
    @Test
    public void testSetUpDiscard()
    {
        System.out.println("SetUpDiscard");
        Deck instance = new Deck();
        Scanner in = new Scanner(System.in);
        instance.SetUpDiscard(in);
    }

    /**
     * Test of PrintDeck method, of class Deck.
     */
    @Test
    public void testPrintDeck() 
    {
        System.out.println("PrintDeck");
        String deckName = "regular";
        Deck instance = new Deck();
        instance.PrintDeck(deckName);
    }

    /**
     * Test of GetDeck method, of class Deck.
     */
    @Test
    public void testGetDeck()
    {
        System.out.println("GetDeck");
        Deck instance = new Deck();
        Stack result = instance.GetDeck();
        Stack expResult = result; 
        assertEquals(expResult, result);
    }

    /**
     * Test of SideEffect method, of class Deck.
     */
    @Test
    public void testSideEffect()
    {
        System.out.println("SideEffect");
        SpecialCard special = new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.BLUE);
        ArrayList<Player> players = new ArrayList<Player>() ;
        players.add(new Player());
        players.add(new Player());
        int pos = 0;
        Deck instance = new Deck();
        instance.SideEffect(special, players, pos);       
    }
}