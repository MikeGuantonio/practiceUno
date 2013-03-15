/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;

/**
 *
 * @author mike
 */

public class SpecialCard extends Card implements SpecialActions
{
   enum cardValues {REVERSE,SKIP,DRTWO} 
   cardValues sp; 

   
   public SpecialCard(cardValues newSp, cardColor newColor)
   {
       sp = newSp; 
       color = newColor; 
   }
   
    /**
     *
     */
    @Override
   public void Print()
   {
       System.out.print(" " + sp + " " + color);
   }
    
    public Card.cardColor GetColor()
    {
        return color; 
    }
    
    public cardValues GetSpecial()
    {
        return sp; 
    }
    
    public int Skip(int currentPlayerIndex)
    {
        return (currentPlayerIndex++);
    }
    
    public int Reverse(int currentPlayerIndex)
    {
        return (currentPlayerIndex -1); 
    }
    
    public void DrawTwo(ArrayList<Player> players, Deck theDeck, int pos)
    {
         //Need a way to handle matching colors as well. 
         for (int i = 0; i < 2; i++) 
         {
            players.get(pos +1).GetCard(theDeck.DrawNext());   
         }
    }
}
