/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

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
           if(c.equals(v))
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
           possibleMatches.clear(); 
       }
       else
           retC = null;
      return retC; 
   }
   
   
}
