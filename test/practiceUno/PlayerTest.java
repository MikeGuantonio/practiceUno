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
    
    public PlayerTest() {
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
     * Test of ShowHand method, of class Player.
     */
    @Test
    public void testShowHand() {
        System.out.println("ShowHand");
        Deck d = new Deck(); 
        Player instance = new Player();
        for (int i = 0; i < 7; i++) 
            instance.GetCard(d.DrawNext());
        instance.ShowHand();
        
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
        int dex = 0;
        Player instance = new Player();
        Card expResult = null;
        Card result = instance.Discard(dex);
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

    /**
     * Test of SetPlayerPos method, of class Player.
     */
    @Test
    public void testSetPlayerPos() {
        System.out.println("SetPlayerPos");
        int newPos = 0;
        Player instance = new Player();
        instance.SetPlayerPos(newPos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetPlayerPos method, of class Player.
     */
    @Test
    public void testGetPlayerPos() {
        System.out.println("GetPlayerPos");
        Player instance = new Player();
        int expResult = 0;
        int result = instance.GetPlayerPos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of NextAction method, of class Player.
     */
    @Test
    public void testNextAction() {
        System.out.println("NextAction");
        Player instance = new Player();
        instance.NextAction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SetName method, of class Player.
     */
    @Test
    public void testSetName() {
        System.out.println("SetName");
        String newName = "";
        Player instance = new Player();
        instance.SetName(newName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("GetName");
        Player instance = new Player();
        String expResult = "";
        String result = instance.GetName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}