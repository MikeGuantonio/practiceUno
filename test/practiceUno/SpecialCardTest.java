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
    
    ArrayList<Player> players = new ArrayList<Player>(); 
    
    public SpecialCardTest() {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void  setUp() 
    {
        
        Player ourPlayer = new Player();
        players.add(ourPlayer);
        ourPlayer = new Player();
        players.add(ourPlayer);
        
    }
    
    @After
    public void tearDown() 
    {
        players.clear();
    }

    /**
     * Test of Print method, of class SpecialCard.
     */
    @Test
    public void testPrint() {
        System.out.println("Print");
        for(SpecialCard.cardValues v : SpecialCard.cardValues.values())
        {
            for(Card.cardColor c : Card.cardColor.values())
            {
                SpecialCard instance = new SpecialCard(v, c);
                instance.Print();
                System.out.println("");
            }
        }
        
    }

    /**
     * Test of GetColor method, of class SpecialCard.
     */
    @Test
    public void testGetColor() {
        System.out.println("GetColor");
        for(SpecialCard.cardColor c : SpecialCard.cardColor.values())
        {
            for(SpecialCard.cardValues v: SpecialCard.cardValues.values())
            {
                SpecialCard instance = new SpecialCard(v, c);
                Card.cardColor expResult = c;
                Card.cardColor result = instance.GetColor();
                assertEquals(expResult, result);
            }
        }
        
    }

    /**
     * Test of GetSpecial method, of class SpecialCard.
     */
    @Test
    public void testGetSpecial() {
        System.out.println("GetSpecial");
        for(SpecialCard.cardValues v : SpecialCard.cardValues.values())
        {
            for(SpecialCard.cardColor c : SpecialCard.cardColor.values())
            {
                SpecialCard instance = new SpecialCard(v, c);
                SpecialCard.cardValues expResult = v;
                SpecialCard.cardValues result = instance.GetSpecial();
                assertEquals(expResult, result);
            }
        }
    }

    /**
     * Test of Skip method, of class SpecialCard.
     */
    @Test
    public void testSkip() 
    {
        System.out.println("Skip");
        int currentPlayerIndex = players.size()-1;
        SpecialCard instance = new SpecialCard(SpecialCard.cardValues.SKIP, Card.cardColor.RED);
        int expResult = 1;
        int result = instance.Skip(currentPlayerIndex, players);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of Reverse method, of class SpecialCard.
     */
    @Test
    public void testReverse() 
    {
        System.out.println("Reverse");
          
        int currentPlayerIndex = 0;
        SpecialCard instance = new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.YELLOW);
        
        int expResult = players.size() -1;
        int result = instance.Reverse(currentPlayerIndex, players);
        tearDown(); 
        assertEquals(expResult, result);
       
        
    }

    /**
     * Test of DrawTwo method, of class SpecialCard.
     */
    @Test
    public void testDrawTwo() 
    {
        setUp(); 
        System.out.println("DrawTwo");
         
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
        tearDown(); 
    }
}