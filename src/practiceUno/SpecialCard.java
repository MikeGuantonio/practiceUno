/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

/**
 *
 * @author mike
 */

//All actions that are not implemented in the class can be declared abstract...
//passin the buck.
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
       System.out.print(sp + " " + color);
   }
    
    public Card.cardColor GetColor()
    {
        return color; 
    }
    
    public cardValues GetSpecial()
    {
        return sp; 
    }
    
    public void Skip()
    {
        
    }
    
    public void Reverse()
    {
        
    }
    
    public void DrawTwo(Player ourPlayer, Deck theDeck)
    {
         //Need a way to handle matching colors as well. 
         for (int i = 0; i < 2; i++) 
         {
            ourPlayer.GetCard(theDeck.DrawNext());   
        }
    }
}
