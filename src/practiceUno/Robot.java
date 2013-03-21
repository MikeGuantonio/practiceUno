/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;
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
   
   public int Decide(Card c)
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
   
   public void SM(int state, Deck d, ArrayList<Player> p)
   {
       boolean done = false; 
       
       while(!done)
       {
            switch(state)
            {
                case 1: System.out.println("Play a Card");
                        break;
                case 2: System.out.println("Draw a card");
                        break; 
                case 3: System.out.println("Wild request");
                        break;
            }
        }
   }
   
}
