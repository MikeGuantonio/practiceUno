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
    
   @Override
    public int Skip(int currentPlayerIndex, int playerSize)
    {
        int index = 0;  
        int actualPlayers = playerSize -1; 
        
        if(currentPlayerIndex == actualPlayers-2) 
            index = 0;
        else if(currentPlayerIndex == actualPlayers-1)
            index = 1;
        else
            index = currentPlayerIndex + 2;
        return index; 
    }
    
   @Override
    public int Reverse(int currentPlayerIndex, int playerSize)
    {
        int index = 0;  
        int actualPlayers = playerSize -1; 
        
        if(currentPlayerIndex == 0) 
            index = actualPlayers;
        else if(currentPlayerIndex == actualPlayers)
            index = 0;
        else
            index = currentPlayerIndex-1;
        return index;  
    }
    
    public void DrawTwo(Player affectedPlayer, Deck theDeck)
    {
         for (int i = 0; i < 2; i++) 
            affectedPlayer.GetCard(theDeck.DrawNext());   
    }
    
    
}
