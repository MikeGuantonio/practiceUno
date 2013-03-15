/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

/**
 *
 * @author mike
 */
public interface WildActions {
    
     Card.cardColor Wild(); 
     Card.cardColor DrawFour(Player nextPlayer, Deck copyDeck); 
    
}
