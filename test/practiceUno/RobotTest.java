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
public class RobotTest {
    
    public RobotTest() {
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
 
    
    @Test
    public void testDiscard()
    {
        Robot r = new Robot("Steve", 2);
        
        Card v = r.Discard(0);
        assertNull("This card should be null", v);
    }
    
    @Test
    public void testPlayAHand()
    {
        Robot r = new Robot("Steve", 0); 
        Deck d = new Deck();
        d.Shuffle();
        d.testSetupDiscard(d.DrawNext());
        
        for (int i = 0; i < 7; i++) {
            r.GetCard(d.DrawNext());
        }
        
        r.PlayAHand(d);
        
        
    }
}