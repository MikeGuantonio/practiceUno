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
        
    }

    /**
     * Test of PlayerTurn method, of class UnoClone.
     */
    @Test
    public void testPlayerTurn() {
        System.out.println("PlayerTurn");
        Player p = new Player();
        Deck d = new Deck();
        UnoClone instance = new UnoClone();
        boolean expResult = false;
        boolean result = instance.PlayerTurn(p, d);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of SetUpPlayers method, of class UnoClone.
     */
    @Test
    public void testSetUpPlayers() {
        int maxPlayers = 5;   
        System.out.println("SetUpPlayers");
        ArrayList<Player> players = new ArrayList<Player>();
        Deck deck = new Deck();
        UnoClone.SetUpPlayers(players, deck, maxPlayers);
        assertEquals(players.size(), maxPlayers); 
    }

    /**
     * Test of GetInput method, of class UnoClone.
     */
    @Test
    public void testGetInput() {
        System.out.println("GetInput");
        String prompt = "";
        UnoClone instance = new UnoClone();
        String expResult = "";
        String result = instance.GetInput(prompt);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of Menu method, of class UnoClone.
     */
    @Test
    public void testMenu() {
        System.out.println("Menu");
        Player p = new Player();
        Deck d = new Deck();
        UnoClone instance = new UnoClone();
        int expResult = 0;
        int result = instance.Menu(p, d);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of Play method, of class UnoClone.
     */
    @Test
    public void testPlay() {
        System.out.println("Play");
        Player p = new Player();
        Deck d = new Deck();
        UnoClone instance = new UnoClone();
        instance.Play(p, d);
        
    }

    /**
     * Test of DrawAndPlay method, of class UnoClone.
     */
    @Test
    public void testDrawAndPlay() {
        System.out.println("DrawAndPlay");
        Player p = new Player();
        Deck d = new Deck();
        UnoClone instance = new UnoClone();
        instance.DrawAndPlay(p, d);
        
    }

    /**
     * Test of Skip method, of class UnoClone.
     */
    @Test
    public void testSkip() {
        System.out.println("Skip");
        Player p = new Player();
        Deck d = new Deck();
        UnoClone instance = new UnoClone();
        instance.Skip(p, d);
        
    }
}