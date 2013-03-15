/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;
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
public class WildCardTest {
    
    ArrayList<Player> players = new ArrayList<Player>(); 
    
    public WildCardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        WildCardTest w = new WildCardTest(); 
        Player ourPlayer = new Player();
        w.players.add(ourPlayer);
        ourPlayer = new Player();
        w.players.add(ourPlayer);
        System.out.println("Size: " + w.players.size() );
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
     * Test of Print method, of class WildCard.
     */
    @Test
    public void testPrint()
    {
        System.out.println("Print");
        WildCard instance = new WildCard(WildCard.cardWild.WILD);
        instance.Print();    
    }

    /**
     * Test of GetColor method, of class WildCard.
     */
    @Test
    public void testGetColor() 
    {
        System.out.println("GetColor");
        WildCard instance = new WildCard(WildCard.cardWild.WILDDRFOUR);
        Card.cardColor expResult = null;
        Card.cardColor result = instance.GetColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of GetWild method, of class WildCard.
     */
    @Test
    public void testGetWild()
    {
        System.out.println("GetWild");
        WildCard instance = new WildCard(WildCard.cardWild.WILD);
        WildCard.cardWild expResult = WildCard.cardWild.WILD;
        WildCard.cardWild result = instance.GetWild();
        assertEquals(expResult, result);
     }

    /**
     * Test of Wild method, of class WildCard.
     */
    @Test
    public void testWild()
    {
        System.out.println("Wild");
        WildCard instance = new WildCard(WildCard.cardWild.WILD);
        Card.cardColor expResult = Card.cardColor.GREEN;
        Card.cardColor result = instance.Wild();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of DrawFour method, of class WildCard.
     */
    @Test
    public void testDrawFour() {
        System.out.println("DrawFour");
        Player newPlayer = players.get(0);
        Deck copyDeck = new Deck();
        WildCard instance = new WildCard(WildCard.cardWild.WILDDRFOUR);
        Card.cardColor expResult = Card.cardColor.RED;
        Card.cardColor result = instance.DrawFour(newPlayer, copyDeck);
        assertEquals(expResult, result);
        newPlayer.ShowHand();
        
    }
}