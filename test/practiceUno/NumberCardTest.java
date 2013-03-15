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
public class NumberCardTest {
    
    public NumberCardTest() {
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
    
    /* Test of the constructor */
    @Test
    public void testConstructor()
    {
        System.out.println("Constructor");
        NumberCard instance = new NumberCard(10, Card.cardColor.YELLOW);
        instance.Print();
        System.out.println("");
        assertNotNull("This object should not be created.", instance); 
    }

    /**
     * Test of GetNumber method, of class NumberCard.
     */
    @Test
    public void testGetNumber() 
    {
        System.out.println("GetNumber");
        NumberCard instance = new NumberCard(5, Card.cardColor.RED);
        int expResult = 5;
        int result = instance.GetNumber();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of GetColor method, of class NumberCard.
     */
    @Test
    public void testGetColor() {
        System.out.println("GetColor");
        NumberCard instance = new NumberCard(7, Card.cardColor.BLUE);
        Card.cardColor expResult = Card.cardColor.BLUE;
        Card.cardColor result = instance.GetColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of Print method, of class NumberCard.
     */
    @Test
    public void testPrint()
    {
        System.out.println("Print");
        NumberCard instance = new NumberCard(8, Card.cardColor.GREEN);
        instance.Print();
    }
}