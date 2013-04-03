/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

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
public class PlayerTest {
    
    /**
     *
     */
    public PlayerTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of ShowHand method, of class Player.
     */
    @Test
    public void testShowHand() {
        Deck d = new Deck(); 
        Player instance = new Human("Steve", 1);
        for (int i = 0; i < 8; i++) 
        {
            if(i != 0 ) {
                instance.GetCard(d.DrawNext());
            }
            instance.ShowHand();
            assertEquals(i, instance.TotalCards());
        }
        
    }

    /**
     * Test of GetCard method, of class Player.
     */
    @Test
    public void testGetCard() {
        Card c = new WildCard(WildCard.cardWild.WILD);
        Player instance = new Human("Steve", 1);
        instance.GetCard(c);
        assertEquals(1, instance.TotalCards());
    }

    /**
     * Test of Discard method, of class Player.
     */
    @Test
    public void testDiscardValid() {
        int dex = 0;
        Player instance = new Human("Bro", 1);
        Deck d = new Deck(); 
        Card expResult = d.DrawNext();
        instance.GetCard(expResult);
        Card result = instance.Discard(dex);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of Invalid Discard, of class Player. 
     */
    @Test
    public void testDiscardInvalid(){
        int dex = 0; 
        Player instance = new Human("Foo", 1); 
        Card expResult = null; 
        Card result = instance.Discard(dex);
        assertEquals(expResult, result);
    }

    /**
     * Test of TotalCards method, of class Player.
     */
    @Test
    public void testTotalCards() {
        Player instance = new Human("Bar", 1);
        int expResult = 0;
        int result = instance.TotalCards();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of SetPlayerPos method, of class Player.
     */
    @Test
    public void testSetPlayerPos() {
        int newPos = 0;
        Player instance = new Human("Bax", 1);
        
    }

    /**
     * Test of GetPlayerPos method, of class Player.
     */
    @Test
    public void testGetPlayerPos() {
        Player instance = new Human("Steve", 0);
        int expResult = 0;
        int result = instance.GetPlayerPos();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of NextAction method, of class Player.
     */
    @Test
    public void testNextAction() {
        Player instance = new Human("Steve", 1);
        
    }

    /**
     * Test of SetName method, of class Player.
     */
    @Test
    public void testSetName() {
        String newName = "Stupid Halvanja";
        Player instance = new Human(newName, 1);
        
        
    }

    /**
     * Test of GetName method, of class Player.
     */
    @Test
    public void testGetName() {
        Player instance = new Human("Halavanja", 1);
        String expResult = "Halavanja";
        
        String result = instance.GetName();
        assertEquals(expResult, result);
     }
    private static final Logger LOG = Logger.getLogger(PlayerTest.class.getName());
}