/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/
package practiceUno;

import java.util.Scanner;
import java.util.logging.Logger;
/**
 *
 * @author mike
 */
public class WildCard extends Card implements WildActions
{
    private static final Logger log = Logger.getLogger(WildCard.class.getName()); 
    cardWild wild; 
    
    
    /**
     *
     * @param w
     */
    public WildCard(cardWild w)
    {
        wild = w; 
        color = null; 
    }
    
    /**
     *
     */
    public void Print()
    {
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
    public cardWild GetWild()
    {
        return wild;
    }
    
    /**
     *
     * @param scan 
     * @return
     */
    public Card.cardColor Wild(Scanner scan)
    {
        log.info("Call for wild called");
        Card.cardColor wildColor = null; 
        
        String input = scan.nextLine(); 
        try
        {
             wildColor = Card.cardColor.valueOf(input.toUpperCase());
        }
        catch(Exception ex)
        {
            log.severe("The color value is null!");
        }
       
        color = wildColor; 
        return wildColor; 
    } 
    
    /**
     *
     * @param newPlayer
     * @param copyDeck
     * @param scan 
     * @return
     */
    public Card.cardColor DrawFour(Player newPlayer, Deck copyDeck, Scanner scan)
    {
        Card.cardColor newColor; 
        newColor = Wild(scan);
        
        for (int i = 0; i < 4; i++) {
            newPlayer.GetCard(copyDeck.DrawNext());
            
        }
        return newColor; 
    }
    
    public String toString()
    {
        String faceValue = wild.toString() +" " +color.toString(); 
        return faceValue; 
    }
    enum cardWild {WILD, WILDDRFOUR};
}
