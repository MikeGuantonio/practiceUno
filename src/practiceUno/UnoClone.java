/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

/**
 *
 * @author mike
 */
public class UnoClone {
    public static void main(String[] args)
    {
        //Creating a simple player and dealer. 
        Player p = new Player(); 
        Dealer d = new Dealer(); 
        Deck deck = new Deck(); 
        
        d.ShowDeck(deck);
    }
}
