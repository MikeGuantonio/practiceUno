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
public class SpecialCardTest {
    
    public SpecialCardTest() {
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
     * Test of Print method, of class SpecialCard.
     */
    @Test
    public void testPrint() {
        System.out.println("Print");
        SpecialCard instance = null;
        instance.Print();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetColor method, of class SpecialCard.
     */
    @Test
    public void testGetColor() {
        System.out.println("GetColor");
        SpecialCard instance = null;
        Card.cardColor expResult = null;
        Card.cardColor result = instance.GetColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetSpecial method, of class SpecialCard.
     */
    @Test
    public void testGetSpecial() {
        System.out.println("GetSpecial");
        SpecialCard instance = null;
        SpecialCard.cardValues expResult = null;
        SpecialCard.cardValues result = instance.GetSpecial();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Skip method, of class SpecialCard.
     */
    @Test
    public void testSkip() {
        System.out.println("Skip");
        int currentPlayerIndex = 0;
        SpecialCard instance = null;
        int expResult = 0;
        int result = instance.Skip(currentPlayerIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Reverse method, of class SpecialCard.
     */
    @Test
    public void testReverse() {
        System.out.println("Reverse");
        int currentPlayerIndex = 0;
        SpecialCard instance = null;
        int expResult = 0;
        int result = instance.Reverse(currentPlayerIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DrawTwo method, of class SpecialCard.
     */
    @Test
    public void testDrawTwo() {
        System.out.println("DrawTwo");
        Player ourPlayer = null;
        Deck theDeck = null;
        SpecialCard instance = null;
        instance.DrawTwo(ourPlayer, theDeck);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}