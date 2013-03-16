/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/
package practiceUno;

import java.util.Scanner;
/**
 *
 * @author mike
 */
public class WildCard extends Card implements WildActions
{
    enum cardWild {WILD, WILDDRFOUR}; 
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
        System.out.print(" " + wild);
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
     * @return
     */
    public Card.cardColor Wild(Scanner scan)
    {
        System.out.println("Please choose a color!");
        System.out.println("Red, Green, Yellow, Blue");
        Card.cardColor wildColor = null; 
        
        String input = scan.nextLine(); 
        try
        {
             wildColor = Card.cardColor.valueOf(input.toUpperCase());
        }
        catch(Exception ex)
        {
            //Find a way to ask this again.
            System.out.println("Incompatible color");
        }
       
        color = wildColor; 
        return wildColor; 
    } 
    
    /**
     *
     * @param newPlayer
     * @param copyDeck
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
}
