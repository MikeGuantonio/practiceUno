/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;
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
public class UnoCloneTest {
    
    public UnoCloneTest() {
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
     * Test of main method, of class UnoClone.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        UnoClone.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SetUpPlayers method, of class UnoClone.
     */
    @Test
    public void testSetUpPlayers() {
        System.out.println("SetUpPlayers");
        ArrayList<Player> players = null;
        Deck deck = null;
        UnoClone.SetUpPlayers(players, deck);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}