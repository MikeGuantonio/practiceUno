/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.Scanner;
import java.util.Stack;


/**
 *
 * @author mike
 */
public class Robot extends Player
{
   private Card.cardColor[] c = Card.cardColor.values();
   private Stack<Card> possibleMatches = new Stack<Card>(); 
   
 
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
   
   public int PossMatch()
   {
       return possibleMatches.size(); 
   }
   
   public void Forget()
   {
       possibleMatches.clear();
   }
   
   public boolean PlayAHand(Deck d)
   {
       boolean done = false; 
       boolean possible = false; 
       int state = Decide(d.TopCard());
       Card c; 
       
       while(!done)
       {
            switch(state)
            {
                case 1: if(possibleMatches.size() != 0)
                        {
                            c = Discard(0);
                            possible = d.AddDiscard(c, this, new Scanner(System.in));
                            if(!possible)
                                super.GetCard(c);
                            else
                            {
                                Forget(); 
                                state = 4;
                            }
                        }
                        else
                           state = 2; 
                        break;
                    
                case 2: super.GetCard(d.DrawNext());
                        state = Decide(d.TopCard());
                        if(state == 2)
                            state = 3; 
                        break; 
                    
                case 3: state = 4; 
                        break;
                    
                case 4: done = true; 
                        break; 
            }
        }
       return done; 
   }
   
    private int Decide(Card c)
    {
       int choice = 0;  
       for (int i = 0; i < hand.size(); i++) {
           
           if(hand.get(i).getClass().equals(c.getClass()))
               possibleMatches.push(hand.remove(i));
       }
       
       if(possibleMatches.size() != 0 )
           choice = 1;
       else
           choice = 2; 
        return choice; 
   }
      
}
