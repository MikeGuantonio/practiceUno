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
        System.out.println("Entering skip function");
        int index = 0;  
        int actualPlayers = playerSize -1; 
        
        System.out.println("ActualPlayer " + actualPlayers);
        System.out.println("Current " + currentPlayerIndex);
        
        if(currentPlayerIndex == actualPlayers-2 && actualPlayers > 3) 
        { 
            index = 0;
            System.out.println("two off from start");
        }
        else if(currentPlayerIndex == actualPlayers-1)
        {
            index = 1;
            System.out.println("One off from start");
        }
        else if(currentPlayerIndex == actualPlayers)
        {
            index = 1; 
        }
        else 
        {
            System.out.println("GeneralCase");
            index = currentPlayerIndex + 2;
        }
        System.out.println("Exiting Skip function");
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
        System.out.println("Entering Reverse");
        int index = 0;  
        int actualPlayers = playerSize-1;
        System.out.println(index);
        System.out.println(actualPlayers);
        
        if(currentPlayerIndex <= 0)
        {
            index = actualPlayers;
            System.out.println("Index less than zero");
        }
        else if(currentPlayerIndex > actualPlayers)
        {
            index = 0;
            System.out.println("index is greater");
        }
        else
        {
            index = currentPlayerIndex-1;
            System.out.println("no change");
        }
        System.out.println("INDEX : " + index);
        System.out.println("Exit reverse");
        return index;  
    }
    
    //Put in the next player. Draw two.
    //Need to do all player manipulation before the call to draw two.
    /**
     *
     * @param theDeck
     * @param p
     */
    @Override //won't work for any player beyond n. Will be null...
    public int DrawTwo(Deck theDeck, Player p)
    {          
         for (int i = 0; i < 2; i++) {
             p.GetCard(theDeck.DrawNext());
         }
         return p.GetPlayerPos();
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
   enum cardValues {REVERSE,SKIP,DRTWO}
    
    
}
