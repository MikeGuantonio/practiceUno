/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mike
 */
public class HumanTest {
    
    /**
     *
     */
    public HumanTest() {
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
    public void testSomeMethod() {
        Human h = new Human("bob", 5); 
    }
    
    @Test
    public void testPlayAHand()
    {
        Human h = new Human("bob", 5);
        Deck d = new Deck(); 
        
        d.SetUpDiscard(null);
        h.PlayAHand(d, null);
    }
    
    private static final Logger LOG = Logger.getLogger(HumanTest.class.getName());
}