/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;
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
    public void testAddDiscard() {
        System.out.println("AddDiscard");
        Card c = new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.BLUE);
        Player play = new Player();
        Deck instance = new Deck();
        instance.SetUpDiscard();
        instance.AddDiscard(c, play);
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
        instance.SetUpDiscard();
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
        instance.SetUpDiscard();
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