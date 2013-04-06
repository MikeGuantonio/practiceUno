/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class NumberCard extends Card {
    private static final Logger log = Logger.getLogger(NumberCard.class.getName());
    private int face;
    
    public NumberCard(int newNum, Card.cardColor color)
    {
        face = newNum; 
        super.color = color; 
        log.setLevel(Level.SEVERE);
    }
     
    public int GetNumber()
    {
        return face; 
    }
    
    
    @Override
    public Card.cardColor GetColor()
    {
        return color; 
    }
    
    
    @Override
    public void Print()
    {
        String faceValue = String.format("%s %s", face, color.toString()); 
        System.out.println(faceValue); 
    }
    
    @Override
    public String toString()
    {
        String faceValue = String.format("%s %s", face, color.toString()); 
        return faceValue;
    }
}
