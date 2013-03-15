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
    
    private static  Scanner scan = new Scanner(System.in);
    
    public WildCard(cardWild w)
    {
        wild = w; 
    }
    
    public void Print()
    {
        System.out.print(wild);
    }
    
    public Card.cardColor GetColor()
    {
        return color; 
    }
    
    public cardWild GetWild()
    {
        return wild;
    }
    
    public Card.cardColor Wild()
    {
        System.out.println("Please choose a color!");
        System.out.println("Red, Green, Yellow, Blue");
        
        String input = scan.nextLine(); 
        Card.cardColor wildColor = Card.cardColor.valueOf(input.toUpperCase());
        
        color = wildColor; 
        return wildColor; 
    } 
    
    public Card.cardColor DrawFour(Player newPlayer, Deck copyDeck)
    {
        Card.cardColor newColor; 
        newColor = Wild();
        
        for (int i = 0; i < 4; i++) {
            newPlayer.GetCard(copyDeck.DrawNext());
            
        }
        return newColor; 
    }
}
