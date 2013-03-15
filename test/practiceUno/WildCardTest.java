/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;
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
public class WildCardTest {
    
    ArrayList<Player> players = new ArrayList<Player>(); 
    
    public WildCardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
       
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
     * Test of Print method, of class WildCard.
     */
    @Test
    public void testPrint()
    {
        System.out.println("Print");
        for(WildCard.cardWild c : WildCard.cardWild.values())
        {
            WildCard instance = new WildCard(c);
            instance.Print();
            System.out.println("");
        }
       
    }

    /**
     * Test of GetColor method, of class WildCard.
     */
    @Test
    public void testGetColor() 
    {
        System.out.println("GetColor");
        
        for(WildCard.cardWild c : WildCard.cardWild.values())
        {
            WildCard instance = new WildCard(c);
            Card.cardColor expResult = null;
            Card.cardColor result = instance.GetColor();
            assertEquals(expResult, result);
            
            
            for(Card.cardColor cc : Card.cardColor.values())
            {
                ByteArrayInputStream in = new ByteArrayInputStream(cc.toString().getBytes());
                System.setIn(in);
                expResult = cc;
                instance.Wild(new Scanner(System.in));
                result = instance.GetColor();
                assertEquals(expResult, result);
            }
        }
    }

    /**
     * Test of GetWild method, of class WildCard.
     */
    @Test
    public void testGetWild()
    {
        System.out.println("GetWild");
        for(WildCard.cardWild c : WildCard.cardWild.values())
        {
            WildCard instance = new WildCard(c);
            WildCard.cardWild expResult = c;
            WildCard.cardWild result = instance.GetWild();
            assertEquals(expResult, result);
        }
     }

    /**
     * Test of Wild method, of class WildCard.
     */
    @Test
    public void testWild()
    {
        System.out.println("Wild");
        for(Card.cardColor c : Card.cardColor.values())
        {
            ByteArrayInputStream in = new ByteArrayInputStream(c.toString().getBytes());
            System.setIn(in);
            WildCard instance = new WildCard(WildCard.cardWild.WILD);
            Card.cardColor expResult = c;
            Card.cardColor result = instance.Wild(new Scanner(System.in));
            assertEquals(expResult, result);
        }
        
    }

    /**
     * Test of DrawFour method, of class WildCard.
     */
    @Test
    public void testDrawFour() 
    {
        System.out.println("DrawFour");
        Player newPlayer = new Player(); 
        Deck copyDeck = new Deck();
        copyDeck.Shuffle();
        
        for(Card.cardColor c : Card.cardColor.values())
        {
            ByteArrayInputStream in = new ByteArrayInputStream(c.toString().getBytes());
            System.setIn(in);
            WildCard instance = new WildCard(WildCard.cardWild.WILDDRFOUR);
            Card.cardColor expResult = c;
            Card.cardColor result = instance.DrawFour(newPlayer, copyDeck, new Scanner(System.in));
            assertEquals(expResult, result);
        }
        assertEquals(16, newPlayer.TotalCards());
    }
}