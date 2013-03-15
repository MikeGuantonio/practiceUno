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
public interface WildActions {
    
     Card.cardColor Wild(Scanner scan); 
     Card.cardColor DrawFour(Player nextPlayer, Deck copyDeck, Scanner scan); 
    
}
