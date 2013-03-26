/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.logging.Logger;

/**
 *
 * @author mike
 */

public class SpecialCard extends Card implements SpecialActions
{
    private static final Logger LOG = Logger.getLogger(SpecialCard.class.getName()); 
   cardValues sp; 

   
    /**
     *
     * @param newSp
     * @param newColor
     */
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
    
    /**
     *
     * @return
     */
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
        
        if(currentPlayerIndex == actualPlayers-2) 
            index = 0;
        else if(currentPlayerIndex == actualPlayers-1)
            index = 1;
        else
            index = currentPlayerIndex + 2;
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
        
        System.out.println("CurrentPlayer index: " + currentPlayerIndex );
        
        if(currentPlayerIndex < 0){
            System.out.println("In 0");
            index = actualPlayers;
        }
        else if(currentPlayerIndex > actualPlayers)
        {
            index = 0;
            System.out.println("Index was actual players");
        }
        else
            index = currentPlayerIndex-1;
        return index;  
    }
    
    //Put in the next player. Draw two.
    //Need to do all player manipulation before the call to draw two.
    /**
     *
     * @param theDeck
     * @param p
     */
    public void DrawTwo(Deck theDeck, Player p)
    {          
         for (int i = 0; i < 2; i++) 
            p.GetCard(theDeck.DrawNext());   
    }
   enum cardValues {REVERSE,SKIP,DRTWO}
    
    
}
