/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;

/**
 *
 * @author mike
 */
public interface SpecialActions {
    
    int Skip(int currentPlayerIndex, int playerSize); //Skips the player. player index plus 1
    int Reverse(int currentPlayerIndex, int playerSize); //Decrement player index by 1
    void DrawTwo(Player affectedPlayer, Deck d); // Player plus 1 and draw two. 
   
}
