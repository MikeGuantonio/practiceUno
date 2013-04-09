
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

//~--- JDK imports ------------------------------------------------------------
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.ListIterator;

import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

//~--- classes ----------------------------------------------------------------
/**
 *
 * @author mike
 */
public class Robot extends Player {

    
    private static final Logger log = Logger.getLogger(Robot.class.getName());
    private Card.cardColor[] colorValues = Card.cardColor.values();
    private Card playingCard = null;
    private Stack<Card> wildSet = new Stack<>(); 

    public Robot(String name, int pos)
    {
        super.name = name;
        super.playerPos = pos;
        log.setLevel(Level.WARNING);
    }

    
    @Override
    public Card Discard(int dex) //this function needs fixed as well...
    {
        log.entering("Discard", name);
        Card retC;

        if (dex == -1)
        {
            retC = null;
        }
        else
        {
            retC = hand.get(dex);
        }
        log.exiting("Discard", name);
        return retC;
    }
            
    public void Remove(Card c)
    {
        hand.remove(c);
    }

   
    @Override
    public Card PlayAHand(Card topCard, Deck d)
    {
        log.entering("Play a hand", name);

        boolean done = false;
        boolean tried = false;
        int pos = 0; 
        Card toPlay = null; 

        log.fine("Trying to decide");

        System.out.println("Deciding");
        int state = Decide(topCard);

        if (state == 1)
        {
            log.fine(String.format("Decided %s %s", state, playingCard.toString()));
        }
        else
        {
            log.fine(String.format("decided %s", state));
        }
        System.out.println("Done decideing");
        
        while (!done)
        {
           switch (state)
           {
                case 1: log.fine(String.format("Trying to play a card %s %s", playingCard.toString(), topCard.toString()));
                        if (playingCard.getClass().equals(WildCard.class))
                        {
                            state = 4;
                        }
                        else
                        {
                            toPlay = hand.remove(FindCard(playingCard));
                            state = 5;
                        }
                        break;

                case 2: log.fine("Need to draw a card");
                        super.GetCard(d.DrawNext());
                        state = Decide(d.TopDiscard());
                        tried = true;
                        if (state == 2)
                        {
                            state = 3;
                        }
                        break;

                case 3: log.fine("Pass");
                        System.out.println(String.format("%s passes.", GetName()));
                        state = 5;
                        break;

                case 4: //We are not doing this anymore. Need to move it outside into uno.
                        log.fine("Time for a wild card.");
                        int colorChoice = (int)(Math.random() * 3);
                        ByteArrayInputStream in = new ByteArrayInputStream(colorValues[colorChoice].toString().getBytes());
                        System.setIn(in);
                        //pos = d.AddDiscard(playingCard, p, new Scanner(System.in), this.GetPlayerPos());
                        hand.remove(FindCard(playingCard));
                        state = 5;
                        break;

                case 5: log.fine("End turn");
                        done = true;
                        toPlay = playingCard;
                        //playingCard = null; // <-- may be segfault...
                        break;

                default: log.severe("I don't know what to do");
                         break;
            }
        }

        log.exiting("Play a Hand", name);
        return toPlay;
    }

    
    private int Decide(Card discard)
    {
        log.entering("Decide", name);

        int choice;
        playingCard = null;
        
        if(this.Match(discard))
        {
            choice = 1; 
        }
        else
        {
            if(this.PlayAWild())
            {
                choice = 1; 
            }
            else
            {
                choice = 2;
            }
        }

        log.exiting("Decide", name);
        return choice;
    }
    
    
    private boolean PlayAWild()
    { 
        boolean isWild = false;
        for(Card c : hand)
        {
            if(c.getClass().equals(WildCard.class))
            {
                playingCard = c;
                isWild = true;
                break;
            }
        }
        return isWild; 
    }
    
    public boolean Match(Card c)
    {
        boolean possible = false; 
        System.out.println("I have "+  this.TotalCards());
        this.ShowHand();
        for(Card inPlay : hand)
        {
            if(!inPlay.getClass().equals(WildCard.class) && c.match(inPlay))
            {
                System.out.println("Matched " + c.toString() + " " + inPlay.toString());
                playingCard = inPlay;
                possible = true;
                break;
            }
            else if(inPlay.getClass().equals(WildCard.class))
            {
                System.out.println("inplay: " + inPlay.toString());
            }
        }
        System.out.println("End of call.");
        return possible; 
    }
    
    
    
    
}
