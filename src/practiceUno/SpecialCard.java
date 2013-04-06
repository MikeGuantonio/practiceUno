/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */

public class SpecialCard extends Card implements SpecialActions
{
    private static final Logger log = Logger.getLogger(SpecialCard.class.getName()); 
   cardValues sp; 

   
    /**
     *
     * @param newSp
     * @param newColor
     */
    public SpecialCard(cardValues newSp, cardColor newColor)
   {
       log.fine("Special card");
       sp = newSp; 
       color = newColor; 
   }
   
    /**
     *
     */
    @Override
   public void Print()
   {
       
   }
    
    /**
     *
     * @return
     */
    @Override
    public Card.cardColor GetColor()
    {
        return color; 
    }
    
    /**
     *
     * @return
     */
    public cardValues GetSpecial()
    {
        return sp; 
    }
    
    /**
     *
     * @param currentPlayerIndex
     * @param playerSize
     * @return
     */
    @Override
    public int Skip(int currentPlayerIndex, int playerSize)
    {
        int index = 0;  
        int actualPlayers = playerSize -1; 
        
        if(currentPlayerIndex == actualPlayers-2 && actualPlayers > 3) 
        { 
            index = 0;
        
        }
        else if(currentPlayerIndex == actualPlayers-1)
        {
            index = 1;
            
        }
        else if(currentPlayerIndex == actualPlayers)
        {
            index = 1; 
        }
        else 
        {
            
            index = currentPlayerIndex + 2;
        }
        
        return index; 
    }
    
    /**
     *
     * @param currentPlayerIndex
     * @param playerSize
     * @return
     */
    @Override
    public int Reverse(int currentPlayerIndex, int playerSize)
    {
        
        int index = 0;  
        int actualPlayers = playerSize-1;
        
        
        if(currentPlayerIndex <= 0)
        {
            index = actualPlayers;
            
        }
        else if(currentPlayerIndex > actualPlayers)
        {
            index = 0;
            
        }
        else
        {
            index = currentPlayerIndex-1;
            
        }
        return index;  
    }
    
    //Put in the next player. Draw two.
    //Need to do all player manipulation before the call to draw two.
    /**
     *
     * @param theDeck
     * @param p
     */
    @Override 
    public int DrawTwo(Deck theDeck, ArrayList<Player> p, int pos)
    {          
         int totalPlayers = p.size()-1;
         int affected = pos; 
         if(pos == totalPlayers)
         {
             affected = 0; 
         }
         else
         {
             affected++;
         }
         
         for (int i = 0; i < 2; i++) {
             p.get(affected).GetCard(theDeck.DrawNext());
         }
         return p.get(pos).GetPlayerPos();
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        String faceValue = String.format("%s %s", sp.toString(), color.toString()); 
        return faceValue; 
    }
    
    @Override
    public boolean match(Card c)
    {
        boolean possible = false;
        
        
        if(this.colorMatch(c))
        {
            possible = true; 
        }
        else if(c.getClass().equals(SpecialCard.class))
        {
           SpecialCard s = (SpecialCard)c;    
            if (this.GetSpecial().equals(s.GetSpecial()))
            {
               possible = true; 
            }
        }
        return possible; 
    }
    
   enum cardValues {REVERSE,SKIP,DRTWO}
    
    
}
