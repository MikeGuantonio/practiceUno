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
        //Need to think about edge case when you move one more past the size
        int index = 0;  
        if(currentPlayerIndex >= (playerSize -1)) //Last player to first
            index = 2; 
        else
            index = currentPlayerIndex + 2;
        return index; 
    }
    
   @Override
    public int Reverse(int currentPlayerIndex, ArrayList<Player> players)
    {
        int index = 0;  
        int direction = -1; 
        if(currentPlayerIndex >= (players.size()+ direction)) //Last player to first
            index = direction; 
        else if(currentPlayerIndex == 0) //first player to last
            index = players.size() + direction ; 
        else
            index = currentPlayerIndex + direction;
        return index; 
    }
    
    public void DrawTwo(Player affectedPlayer, Deck theDeck)
    {
         for (int i = 0; i < 2; i++) 
            affectedPlayer.GetCard(theDeck.DrawNext());   
    }
    
    
}
