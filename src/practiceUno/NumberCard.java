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
        System.out.println(this.toString()); 
    }
    
    @Override
    public String toString()
    {
        String termColor = super.ReturnColor(color);
        String faceValue = String.format("%s%s \033[0m", termColor, face); 
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
        else if((c.getClass().equals(NumberCard.class)))
        {
           
            NumberCard toMatch = (NumberCard)c;
            if(this.GetNumber() == toMatch.GetNumber())
            {
            possible = true;
            }
        }
        return possible;
    }
}
