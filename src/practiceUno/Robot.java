
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

//~--- JDK imports ------------------------------------------------------------
import java.io.ByteArrayInputStream;
import java.text.MessageFormat;

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

    /**
     *
     * @param name
     * @param pos
     */
    public Robot(String name, int pos)
    {
        super.name = name;
        super.playerPos = pos;
        log.setLevel(Level.WARNING);
    }

    /**
     * Method description
     *
     *
     * @param c
     *
     * @return
     */
    public int FindCard(Card c) 
    {
        log.entering("Find Card", name);
        return hand.indexOf(c);
    }
    
    
    /**
     * Method description
     *
     *
     * @param dex
     *
     * @return
     */
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

    /**
     *
     * @param d
     * @return
     */
    public boolean PlayAHand(Deck d)
    {
        log.entering("Play a hand", name);

        boolean done = false;
        boolean tried = false;

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
                            d.AddDiscard(playingCard, this, null);
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
                        d.AddDiscard(playingCard, this, new Scanner(System.in));
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
        return done;
    }

    /**
     * Method description
     *
     *
     * @param c
     *
     * @return
     */
    private int Decide(Card c) {
        log.entering("Decide", name);

        int choice;
        Card discard = c;  

        if(discard.getClass().equals(NumberCard.class))
        {
            NumberCard n = (NumberCard)discard; 
                     
            for(Card inPlay : hand)
            {
                 if(!inPlay.getClass().equals(WildCard.class) && inPlay.GetColor().equals(discard.GetColor()))
                 {
                    playingCard = inPlay; 
                    break;
                 }
                 else if(inPlay.getClass().equals(NumberCard.class))
                 {
                    NumberCard play = (NumberCard)inPlay;
                    if(play.GetNumber() == n.GetNumber())
                    {
                        playingCard = inPlay; 
                        break;
                    }
                  }                        
            }
        }
        else if(discard.getClass().equals(SpecialCard.class))
        {
            SpecialCard sp = (SpecialCard)discard; 
                    
            for(Card inPlay : hand)
            {
                if(!inPlay.getClass().equals(WildCard.class) && inPlay.GetColor().equals(discard.GetColor()))
                {
                    playingCard = inPlay; 
                    break;
                }
                else if(inPlay.getClass().equals(SpecialCard.class))
                {
                    SpecialCard play = (SpecialCard)inPlay;
                    if(play.GetSpecial().equals(sp.GetSpecial()))
                    {
                        playingCard = inPlay; 
                        break;
                    }
                }
                else
                {
                            log.fine("No special match");
                }
            }
        }
        else if(discard.getClass().equals(WildCard.class))
        {
            
            for(Card inPlay : hand)
            {
                    if(!inPlay.getClass().equals(WildCard.class) && inPlay.GetColor().equals(discard.GetColor()))
                    {
                        playingCard = inPlay; 
                        break;
                    }
            }
        }
        else
        {
            log.severe("This card should never exsist!");
        }

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
    
    //May just remove the idea of having a wild deck. No real reason as I can 
    //just loop through my hand in order to set things up.
    private void PlayAWild()
    { 
        MakeWildDeck(); 
        if(playingCard == null && !wildSet.empty() )
        {
            playingCard = wildSet.pop(); 
        }
        wildSet.clear();
    }
}
