/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Logger;


/**
 *
 * @author mike
 */
public class Robot extends Player
{
    private static final Logger log = Logger.getLogger(Robot.class.getName());
   private Card.cardColor[] c = Card.cardColor.values();
   private Stack<Card> possibleMatches = new Stack<Card>(); 
   private Card playingCard = null; 
   
 
    /**
     *
     * @param name
     * @param pos
     */
    public Robot(String name, int pos)
   {
       super.name = name; 
       super.playerPos = pos;
   }
   
   
   
   @Override
   public Card Discard(int dex)
   {
       Card retC; 
       
       if(possibleMatches.size() != 0)
       {
           retC = possibleMatches.pop(); 
       }
       else
           retC = null;
      return retC; 
   }
   
    /**
     *
     * @return
     */
    public int PossMatch()
   {
       return possibleMatches.size(); 
   }
   
    /**
     *
     */
    public void Forget()
   {
       possibleMatches.clear();
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
       boolean possible = false; 
       int state = Decide(d.TopCard());
       Card c = null; 
       
       while(!done)
       {
            switch(state)
            {
                case 1: log.info("Looing for possible match"); //May just return match.
                        if(possibleMatches.size() != 0)
                        {
                            c = Discard(0); 
                            if(c.getClass().equals(WildCard.class))
                            {
                                log.info("Found a wild card. Moving to state 4");
                                state = 4;
                            }
                            else
                            {
                                possible = d.AddDiscard(c, this, new Scanner(System.in));
                                if(!possible)
                                {
                                    super.GetCard(c);
                                    log.info("Card was not match. Return to hand");
                                }
                                else
                                {
                                    log.info("Found Match");
                                    Forget(); 
                                    state = 5;
                                }
                            }
                        }
                        else
                        {
                            state = 2;
                            log.info("No possible matches. Drawing a card.");
                        }
                        break;
                    
                case 2: log.info("No possible matches found. Drawing a card");
                        log.info("Then going to try to play card again");
                        super.GetCard(d.DrawNext());
                        state = Decide(d.TopCard());
                        if(state == 2)
                            state = 3; 
                        break; 
                    
                case 3: log.info("Pass");
                        state = 5; 
                        break;
                    
                case 4: log.info("Time for a wild card. Choosing yellow");
                        ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
                        System.setIn(in);
                        d.AddDiscard(c, this, new Scanner(System.in));
                        state = 5;
                        break; 
                    
                case 5: log.info("End turn");
                        done = true; 
                        break; 
            }
        }
       log.exiting("Play a Hand", name);
       return done; 
   }
   
    private int Decide(Card c)
    {
       log.entering("Decide", name);
       int choice = 0;  
       Forget(); 
       for (int i = 0; i < hand.size(); i++)
       {
           
           if(hand.get(i).getClass().equals(c.getClass()))
           {
               possibleMatches.push(hand.remove(i));
           }
           
       }
       
       if(possibleMatches.size() != 0 )
           choice = 1;
       else
           choice = 2; 
       
       log.exiting("Decide", name);
        return choice; 
   }
      
}
