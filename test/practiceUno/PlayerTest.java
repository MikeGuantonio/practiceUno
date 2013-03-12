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
        System.out.println("ShowHand");
        Player instance = new Player();
        instance.ShowHand();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetCard method, of class Player.
     */
    @Test
    public void testGetCard() {
        System.out.println("GetCard");
        Card c = null;
        Player instance = new Player();
        instance.GetCard(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Discard method, of class Player.
     */
    @Test
    public void testDiscard() {
        System.out.println("Discard");
        Card card = null;
        Player instance = new Player();
        boolean expResult = false;
        Card result = instance.Discard(0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of TotalCards method, of class Player.
     */
    @Test
    public void testTotalCards() {
        System.out.println("TotalCards");
        Player instance = new Player();
        int expResult = 0;
        int result = instance.TotalCards();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}