/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

/**
 *
 * @author mike
 */
public interface SpecialActions {
    
    /**
     *
     * @param currentPlayerIndex
     * @param playerSize
     * @return
     */
    int Skip(int currentPlayerIndex, int playerSize); //Skips the player. player index plus 1
    /**
     *
     * @param currentPlayerIndex
     * @param playerSize
     * @return
     */
    int Reverse(int currentPlayerIndex, int playerSize); //Decrement player index by 1
    /**
     *
     * @param d
     * @param p
     */
    void DrawTwo(Deck d, Player p); // Player plus 1 and draw two. 
   
}
