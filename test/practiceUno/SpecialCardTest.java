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
import java.util.ArrayList; 
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
        SpecialCard instance = new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.RED);
        instance.Print();
        
    }

    /**
     * Test of GetColor method, of class SpecialCard.
     */
    @Test
    public void testGetColor() {
        System.out.println("GetColor");
        SpecialCard instance = new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.BLUE);
        Card.cardColor expResult = Card.cardColor.BLUE;
        Card.cardColor result = instance.GetColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of GetSpecial method, of class SpecialCard.
     */
    @Test
    public void testGetSpecial() {
        System.out.println("GetSpecial");
        SpecialCard instance = new SpecialCard(SpecialCard.cardValues.SKIP, Card.cardColor.RED);
        SpecialCard.cardValues expResult = SpecialCard.cardValues.SKIP;
        SpecialCard.cardValues result = instance.GetSpecial();
        assertEquals(expResult, result);
    }

    /**
     * Test of Skip method, of class SpecialCard.
     */
    @Test
    public void testSkip() 
    {
        System.out.println("Skip");
        int currentPlayerIndex = 0;
        SpecialCard instance = new SpecialCard(SpecialCard.cardValues.SKIP, Card.cardColor.RED);
        int expResult = 1;
        int result = instance.Skip(currentPlayerIndex);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of Reverse method, of class SpecialCard.
     */
    @Test
    public void testReverse() {
        System.out.println("Reverse");
        int currentPlayerIndex = 0;
        SpecialCard instance = new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.YELLOW);
        int expResult = 0;
        int result = instance.Reverse(currentPlayerIndex);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of DrawTwo method, of class SpecialCard.
     */
    @Test
    public void testDrawTwo() {
        System.out.println("DrawTwo");
        ArrayList<Player> players = new ArrayList<Player>();
        
        Player ourPlayer = new Player();
        players.add(ourPlayer);
        ourPlayer = new Player();
        players.add(ourPlayer);
        Deck theDeck = new Deck();
        
        for (int i = 0; i < 7; i++) 
        {
            for (int j = 0; j < 2; j++) 
                players.get(j).GetCard(theDeck.DrawNext());
        }
        SpecialCard instance = new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.BLUE);
        
        System.out.println("Player 1 Hand");
        players.get(0).ShowHand();
        System.out.println("");
        
        System.out.println("Player 2 Hand");
        players.get(1).ShowHand();
        System.out.println("");
        
        System.out.println("Player 1 played Draw Two");
        instance.DrawTwo(players, theDeck, players.get(0).GetPlayerPos());
        System.out.println("Player 1 Hand");
        players.get(0).ShowHand();
        System.out.println("");
        
        System.out.println("Player 2 Hand");
        players.get(1).ShowHand();
        System.out.println("");
    }
}