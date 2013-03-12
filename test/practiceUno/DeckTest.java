/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

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
     * Test of DrawNext method, of class Deck.
     */
    @Test
    public void testDrawNext() {
        System.out.println("DrawNext");
        Deck instance = new Deck();
        Card expResult = null;
        Card result = instance.DrawNext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddDiscard method, of class Deck.
     */
    @Test
    public void testAddDiscard() {
        System.out.println("AddDiscard");
        Card c = null;
        Deck instance = new Deck();
        instance.AddDiscard(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSize method, of class Deck.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        String deckName = "";
        Deck instance = new Deck();
        int expResult = 0;
        int result = instance.getSize(deckName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PrintDeck method, of class Deck.
     */
    @Test
    public void testPrintDeck() {
        System.out.println("PrintDeck");
        Deck instance = new Deck();
        instance.PrintDeck("regular");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetDeck method, of class Deck.
     */
    @Test
    public void testGetDeck() {
        System.out.println("GetDeck");
        Deck instance = new Deck();
        Stack expResult = null;
        Stack result = instance.GetDeck();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}