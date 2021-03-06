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
import java.util.Iterator;
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
        
    }
    
    @After
    public void tearDown() 
    {
       
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

    private int Wrap(int maxSize, int newNum)
    {
        int index = 0;  
        if(newNum-2 == (maxSize-2)) 
            index = 0;
        else if(newNum-2 == (maxSize -1))
            index = 1;
        else
            index = newNum;
        return index; 
    }
    
    /**
     * Test of Skip method, of class SpecialCard.
     */
    @Test
    public void testSkip() 
    {
        System.out.println("Skip");
        int maxPlayerSize = 5; //Assume that there are 5 max players.
        int expResult = 0; 
        
        for(Card.cardColor c : Card.cardColor.values())
        {
            for (int playerIndex = 0; playerIndex < maxPlayerSize-1; playerIndex++)
            {    
                SpecialCard instance = new SpecialCard(SpecialCard.cardValues.SKIP, c);
                expResult = Wrap(maxPlayerSize-1, playerIndex+2);
                
                int result = instance.Skip(playerIndex, maxPlayerSize);
                
                System.out.println("Current Player: " + playerIndex);
                System.out.println("Next Player: " + result);
                assertEquals(expResult, result);
                
            }
        }
   
    }

     private int RevWrap(int maxSize, int newNum) //New num is the next player after math applied
    {
        int index = 0;  
        
        if(newNum < 0) 
            index = maxSize;
        else if(newNum > maxSize)
            index = newNum;
        else
            index -= 1;
        return index; 
    }
    /**
     * Test of Reverse method, of class SpecialCard.
     * Need to test upperbound players and lower bound players for completeness.
     * 2 and 10 with 5 thrown in for good measure. 
     */
    @Test
    public void testReverse() 
    {
        System.out.println("Reverse");
          
        int maxPlayers = 3; 
        SpecialCard instance = new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.YELLOW);
        for(int playerIndex = 0; playerIndex < maxPlayers-1; playerIndex++)
        {
            int expResult = RevWrap(maxPlayers-1, playerIndex-1);
            int result = instance.Reverse(playerIndex-1, maxPlayers);
            System.out.println("Current Player: " + playerIndex);
            System.out.println("Next Player: " + result);
            assertEquals(expResult, result);
        }
    }

    /**
     * Test of DrawTwo method, of class SpecialCard.
     */
    
    public void testDrawTwo() 
    {

        System.out.println("DrawTwo");
        int handSize = 0; 
        int expected = 2; 
          
        Deck theDeck = new Deck();
        Player affectedPlayer = new Human("steve", 1); 
       
        
        for(SpecialCard.cardColor c : SpecialCard.cardColor.values())
        {
            SpecialCard instance = new SpecialCard(SpecialCard.cardValues.DRTWO, c);
            //instance.DrawTwo(theDeck, affectedPlayer);
            handSize = affectedPlayer.TotalCards();
            assertEquals(expected, handSize);
            expected += 2; 
        }

        
    }
    
    @Test
    public void testMatch()
    {
        boolean works = false; 
        SpecialCard s = new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.BLUE);
        SpecialCard f = new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.RED);
        works = s.match(f);
        assertEquals(true, works);
    }
    
    @Test 
    public void testSkipWithTwo()
    {
        System.out.println("Test skip with two.");
        boolean canPlace = false;
        ArrayList<Player> p = new ArrayList<>(); 
        Deck d = new Deck(); 
        d.puppetSetupDiscard(new SpecialCard(SpecialCard.cardValues.SKIP, Card.cardColor.RED));
        
        for (int i = 0; i < 2; i++) {
            p.add(new Robot(("Steve"+i), i));
        }
        
        for (int k = 0; k < 2; k++) {
            p.get(k).GetCard(new SpecialCard(SpecialCard.cardValues.SKIP, Card.cardColor.BLUE));
        }
        
        int pos = 0; 
        Card c = null;
        
        for(int i = 0; i < 3; i++)
        {
           System.out.println("Card " + p.get(pos).TotalCards());
           System.out.println("Start pos " + pos);
           c = p.get(pos).Discard(0); 
           canPlace =  d.AddDiscard(c);
           System.out.println("End Pos "+ pos);
           System.out.println("Card Played " + p.get(pos).GetName() + " " + c.toString());
        }
        assertEquals(canPlace, true);
        
    }
    
    
}