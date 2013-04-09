/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        Card tmp = new NumberCard(5, Card.cardColor.BLUE);
        
        Card v = r.Discard(r.FindCard(tmp));
        assertNull("This card should be null", v);
    }
    
    @Test
    public void testPlayAHand()
    {
        Robot r = new Robot("Steve", 0);
        ArrayList<Player> p = new ArrayList<>(); 
        p.add(r);
        Deck d = new Deck();
        d.Shuffle();
        d.puppetSetupDiscard(d.DrawNext());
        
        for (int i = 0; i < 7; i++) {
            r.GetCard(d.DrawNext());
        }
        
        r.PlayAHand(d.TopDiscard(), d );
        
    }
    
    /**
     *
     */
    @Test
    public void testPlayNoShuffle()
    {
        ArrayList<Player> p = new ArrayList<>(); 
        Robot r = new Robot("Steve", 0);
        p.add(r);
        Deck d = new Deck(); 
        d.SetUpDiscard(null);
        r.ShowHand();
        for(int i = 0; i < 7; i++)
        {
            r.GetCard(d.DrawNext());
        }
        r.PlayAHand(d.TopDiscard(), d);
    }
    
    /**
     *
     * @throws IOException
     */
    @Test
    public void testPlayKnownHand() throws IOException
    {
        ArrayList<Player> p = new ArrayList<>();
        Robot r = new Robot("steve", 0);
        Robot o = new Robot("Larry", 1);
        p.add(r);
        p.add(o); 
        Deck d = new Deck(); 
        
        
        r.GetCard(new NumberCard(5, Card.cardColor.BLUE));
        r.GetCard(new NumberCard(5, Card.cardColor.GREEN));
        r.GetCard(new NumberCard(5, Card.cardColor.YELLOW));
        r.GetCard(new NumberCard(5, Card.cardColor.RED));
        r.GetCard(new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.BLUE));
        r.GetCard(new SpecialCard(SpecialCard.cardValues.SKIP, Card.cardColor.RED));
        r.GetCard(new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.GREEN));

        d.puppetSetupDiscard(new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.BLUE));
       
        boolean done = false; 
        while(!done)
        {
            r.PlayAHand(d.TopDiscard(), d);
            if(r.TotalCards() == 0)
            {
                done = true; 
            }
            else if(r.TotalCards() == 1 )
            {
            }
        }
        
    }
    
    /**
     *
     */
    @Test
    public void testWildonDiscard()
    {
        ArrayList<Player> p = new ArrayList<>(); 
        Deck d = new Deck(); 
        Robot r = new Robot("Steve", 0);
        Robot k = new Robot("Larry", 1);
        p.add(r);
        p.add(k);
        
        d.puppetSetupDiscard(new WildCard(WildCard.cardWild.WILD));
        WildCard w = (WildCard)d.TopDiscard();
        ByteArrayInputStream in = new ByteArrayInputStream("Red".getBytes());
        System.setIn(in);
        w.Wild(new Scanner(System.in));
        r.GetCard(new NumberCard(5, Card.cardColor.BLUE));
        r.GetCard(new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.RED));
        
        r.PlayAHand(d.TopDiscard(), d);
        
    }
    
    /**
     *
     */
    @Test
    public void testNumberonDiscard()
    {
        Deck d = new Deck(); 
        Robot r = new Robot("Steve", 0);
        ArrayList<Player> p = new ArrayList<>(); 
        p.add(r);
        d.puppetSetupDiscard(new NumberCard(5, Card.cardColor.BLUE));
        r.GetCard(new NumberCard(5, Card.cardColor.BLUE));
        r.GetCard(new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.RED));
        
        r.PlayAHand(d.TopDiscard(), d);
    }
    
    /**
     *
     */
    @Test
    public void testSpecialonDiscard()
    {
        Deck d = new Deck(); 
        Robot r = new Robot("Steve", 0);
        ArrayList<Player> p = new ArrayList<>(); 
        p.add(r);
        
        d.puppetSetupDiscard(new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.GREEN));
        r.GetCard(new NumberCard(5, Card.cardColor.BLUE));
        r.GetCard(new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.RED));
        
        r.PlayAHand(d.TopDiscard(), d);
        System.out.println("Name of class " + r.getClass().getSimpleName());
    }
    private static final Logger LOG = Logger.getLogger(RobotTest.class.getName());
}