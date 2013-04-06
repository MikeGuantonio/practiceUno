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
public class NumberCardTest {
    
    /**
     *
     */
    public NumberCardTest() {
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
     *
     */
    
    @Test
    public void testConstructor()
    {
        System.out.println("Constructor");
        NumberCard instance = new NumberCard(10, Card.cardColor.YELLOW);
        instance.Print();
        assertNotNull("This object should not be created.", instance); 
    }

    /**
     * Test of GetNumber method, of class NumberCard.
     */
    @Test
    public void testGetNumber() 
    {
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
        NumberCard instance = new NumberCard(8, Card.cardColor.GREEN);
        instance.Print();
    }
    private static final Logger LOG = Logger.getLogger(NumberCardTest.class.getName());
    
    @Test 
    public void testMatch()
    {
        boolean works = false; 
        
        NumberCard n = new NumberCard(8, Card.cardColor.BLUE);
        Deck d = new Deck(); 
        d.puppetSetupDiscard(new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.BLUE));
        
        Card c = d.TopDiscard();
        works = n.match(c);
        
        assertEquals(true, works);
    }
}