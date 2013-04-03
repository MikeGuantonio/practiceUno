/*
 * Card Testing suite. Will check basic functions of 
 * the card class. 
 */
package practiceUno;

import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * @author mike
 */
public class CardTest {
    
    /**
     *
     */
    public CardTest() {
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
     * Test of Print method, of class Card.
     */
    @Test
    public void testPrint() 
    {
        Card instance = new CardImpl();
        instance.Print();
    }

    /**
     * Test of GetColor method, of class Card.
     */
    @Test
    public void testGetColor() {
        Card instance = new CardImpl(); 
        
        Card.cardColor expResult = Card.cardColor.BLUE;
        Card.cardColor result = instance.GetColor();
        assertEquals("Checks to see if the color of the card matches", expResult, result);
    }

    /**
     *
     */
    public class CardImpl extends Card {

        @Override
        public void Print() {
        }

        @Override
        public cardColor GetColor() {
            return cardColor.BLUE;
        }
        
        @Override
        public String toString()
        {
            String faceValue = String.format("%s %s", 5, color.toString()); 
            return faceValue; 
        }
    }
    private static final Logger LOG = Logger.getLogger(CardTest.class.getName());
}