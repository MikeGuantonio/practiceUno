/*
 * Card Testing suite. Will check basic functions of 
 * the card class. 
 */
package practiceUno;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * @author mike
 */
public class CardTest {
    
    public CardTest() {
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
     * Test of Print method, of class Card.
     */
    @Test
    public void testPrint() 
    {
        System.out.println("Print");
        Card instance = new CardImpl();
        instance.Print();
    }

    /**
     * Test of GetColor method, of class Card.
     */
    @Test
    public void testGetColor() {
        System.out.println("GetColor");
        Card instance = new CardImpl(); 
        
        Card.cardColor expResult = Card.cardColor.BLUE;
        Card.cardColor result = instance.GetColor();
        assertEquals("Checks to see if the color of the card matches", expResult, result);
    }

    public class CardImpl extends Card {

        public void Print() {
            System.out.println(GetColor());
        }

        public cardColor GetColor() {
            return cardColor.BLUE;
        }
        
        public String toString()
        {
            String faceValue = String.format("%s %s", 5, color.toString()); 
            return faceValue; 
        }
    }
}