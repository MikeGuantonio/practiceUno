/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 *
 * @author mike
 */
public class Robot extends Player
{
    private static final Logger log = Logger.getLogger(Robot.class.getName());
   private Card.cardColor[] c = Card.cardColor.values();
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
   
   
   public int FindCard(Card c)
   {
       return hand.indexOf(c);
   }
   
   @Override
   public Card Discard(int dex)
   {
       Card retC; 
       if(dex == -1)
       {
           retC = null; 
       }
       else
       {
           retC = hand.get(dex); 
       }
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
       boolean possible = false; 
       int state = Decide(d.TopCard());
       boolean tried = false; 
       
       while(!done)
       {
            switch(state)
            {
                case 1: log.info("Trying to play a card"); //need to check for wild.
                        d.AddDiscard(playingCard, this, null);
                        state = 5; 
                        break;
                    
                case 2: log.info("Need to draw a card");
                        super.GetCard(d.DrawNext());
                        state = Decide(d.TopCard());
                        tried = true;
                        if(state == 2)
                            state = 3; 
                        break; 
                    
                case 3: log.info("Pass");
                        state = 5; 
                        break;
                    
                case 4: log.info("Time for a wild card. Choosing yellow");
                        ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());
                        System.setIn(in);
                        d.AddDiscard(playingCard, this, new Scanner(System.in));
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
           
       for(Card play : hand)
       {
           if(play.GetColor().equals(c.GetColor())) //match color
           {
               playingCard = play; 
               break; 
           }
           else if(c.getClass().equals(NumberCard.class)) //match number
           {
               NumberCard n = (NumberCard)play; 
               NumberCard top = (NumberCard)c;
               if(n.GetNumber() == top.GetNumber())
               {
                   playingCard = play;
                   break; 
               }
           }
           
       }
           
       if(playingCard != null)
           choice = 1;
       else
           choice = 2; 
       
       log.exiting("Decide", name);
        return choice; 
   }
      
}
