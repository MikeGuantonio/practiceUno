/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

/**
 *
 * @author mike
 */
public class SpecialCard extends Card 
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
       System.out.println(sp + " " + color);
   }
}
