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
    }
}
