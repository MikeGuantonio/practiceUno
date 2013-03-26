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
public class NumberCard extends Card {
    private static final Logger LOG = Logger.getLogger(NumberCard.class.getName());
    
    private int face;
    
    /**
     *
     * @param newNum
     * @param color
     */
    public NumberCard(int newNum, Card.cardColor color)
    {
        if(newNum >= 0 && newNum <= 9)
        {
            face = newNum;
             super.color = color;
        }
        else
            System.out.println("Cannot create a card");
        
    }
    
    /**
     *
     * @return
     */
    public int GetNumber()
    {
        return face; 
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
     */
    @Override
    public void Print()
    {
        System.out.print(" " + face + " " + color);
    }
}
