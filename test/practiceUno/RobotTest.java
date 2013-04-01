/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.io.ByteArrayInputStream;
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
    public void testDecide()
    {
        
    }
    
    @Test
    public void testDiscard()
    {
        Robot r = new Robot("Steve", 2);
        Card tmp = new NumberCard(5, Card.cardColor.BLUE);
        
        Card v = r.Discard(r.FindCard(tmp));
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
    
    @Test
    public void testPlayNoShuffle()
    {
        Robot r = new Robot("Steve", 0);
        Deck d = new Deck(); 
        d.SetUpDiscard(null);
        
        System.out.println("Showing hand...");
        r.ShowHand();
        for(int i = 0; i < 7; i++)
        {
            r.GetCard(d.DrawNext());
        }
        r.PlayAHand(d);
    }
    
    @Test
    public void testPlayKnownHand()
    {
        Robot r = new Robot("steve", 0);
        Deck d = new Deck(); 
        d.SetUpDiscard(null);
        
        r.GetCard(new NumberCard(5, Card.cardColor.BLUE));
        r.GetCard(new NumberCard(5, Card.cardColor.GREEN));
        r.GetCard(new NumberCard(5, Card.cardColor.YELLOW));
        r.GetCard(new NumberCard(5, Card.cardColor.RED));
        r.GetCard(new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.BLUE));
        r.GetCard(new SpecialCard(SpecialCard.cardValues.SKIP, Card.cardColor.RED));
        r.GetCard(new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.GREEN));

        d.testSetupDiscard(new NumberCard(5, Card.cardColor.BLUE));
       
        boolean done = false; 
        while(!done)
        {
            done = r.PlayAHand(d);
        }
        
    }
    
    @Test
    public void testWildonDiscard()
    {
        Deck d = new Deck(); 
        Robot r = new Robot("Steve", 0);
        
        d.testSetupDiscard(new WildCard(WildCard.cardWild.WILD));
        WildCard w = (WildCard)d.TopCard();
        ByteArrayInputStream in = new ByteArrayInputStream("Red".getBytes());
        System.setIn(in);
        w.Wild(new Scanner(System.in));
        r.GetCard(new NumberCard(5, Card.cardColor.BLUE));
        r.GetCard(new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.RED));
        
        r.PlayAHand(d);
        
    }
    
    @Test
    public void testNumberonDiscard()
    {
        
    }
    
    @Test
    public void testSpecialonDiscard()
    {
        
    }
}