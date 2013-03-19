/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

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
       for(Card v : hand)
       {
           if(v.getClass().equals(c.getClass()))
               possibleMatches.push(v);
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
   
   
}
