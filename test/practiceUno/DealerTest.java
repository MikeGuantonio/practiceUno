/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

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
public class DealerTest {
    
    public DealerTest() {
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
     * Test of Shuffle method, of class Dealer.
     */
    @Test
    public void testShuffle() {
        System.out.println("Shuffle");
        Deck d = null;
        Dealer instance = new Dealer();
        Deck expResult = null;
        Deck result = instance.Shuffle(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ShowDeck method, of class Dealer.
     */
    @Test
    public void testShowDeck() {
        System.out.println("ShowDeck");
        Deck d = null;
        Dealer instance = new Dealer();
        instance.ShowDeck(d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Deal method, of class Dealer.
     */
    @Test
    public void testDeal() {
        System.out.println("Deal");
        Deck d = null;
        Dealer instance = new Dealer();
        Card expResult = null;
        Card result = instance.Deal(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}