
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

//~--- JDK imports ------------------------------------------------------------
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

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
    public Card Discard(int dex)
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

    
    public int PlayAHand(Deck d, ArrayList<Player> p)
    {
        log.entering("Play a hand", name);

        boolean done = false;
        boolean tried = false;
        int pos = 0; 

        log.fine("Trying to decide");

        int state = Decide(d.TopDiscard());

        if (state == 1)
        {
            log.fine(String.format("Decided %s %s", state, playingCard.toString()));
        }
        else
        {
            log.fine(String.format("decided %s", state));
        }

        while (!done)
        {
           switch (state)
           {
                case 1: log.fine(String.format("Trying to play a card %s %s", playingCard.toString(), d.TopDiscard().toString()));
                        if (playingCard.getClass().equals(WildCard.class))
                        {
                            state = 4;
                        }
                        else
                        {
                            pos = d.AddDiscard(playingCard, p, null, this.GetPlayerPos());
                            hand.remove(FindCard(playingCard));
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

                case 4: log.fine("Time for a wild card.");
                        int colorChoice = (int)(Math.random() * 3);
                        ByteArrayInputStream in = new ByteArrayInputStream(colorValues[colorChoice].toString().getBytes());
                        System.setIn(in);
                        pos = d.AddDiscard(playingCard, p, new Scanner(System.in), this.GetPlayerPos());
                        hand.remove(FindCard(playingCard));
                        state = 5;
                        break;

                case 5: log.fine("End turn");
                        done = true;
                        if(playingCard != null)
                        {
                            System.out.println(String.format("%s played %s", GetName(), playingCard.toString()));
                            playingCard = null; 
                        }
                        break;

                default: log.severe("I don't know what to do");
                         break;
            }
        }

        log.exiting("Play a Hand", name);
        return pos;
    }

    
    private int Decide(Card c)
    {
        log.entering("Decide", name);

        int choice;
        Card discard = c;
        playingCard = null;
        String cardName = discard.getClass().getSimpleName();
        
        this.Match(c);
                
        if(playingCard == null )
            PlayAWild(); 
                
        if (playingCard != null)
        {
            choice = 1;
        }
        else 
        {
            choice = 2;
        }

        log.exiting("Decide", name);
        return choice;
    }
    
    private void MakeWildDeck()
    {
        for(Card c : hand)
        {
            if(c.getClass().equals(WildCard.class))
            {
                wildSet.push(c);
            }
        }
    }
    
   
    private void PlayAWild()
    { 
        MakeWildDeck(); 
        if(playingCard == null && !wildSet.empty() )
        {
            playingCard = wildSet.pop(); 
        }
        wildSet.clear();
    }
    
    public void Match(Card c)
    {
        for(Card inPlay : hand)
        {
            if(c.match(inPlay))
            {
                System.out.println("Matched");
                System.out.println(c.toString());
                System.out.println(inPlay.toString());
                playingCard = inPlay; 
                break;
            }
        }
    }
    
    
    
    
}
