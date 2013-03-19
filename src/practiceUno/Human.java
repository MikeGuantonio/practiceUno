/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

/**
 *
 * @author mike
 */
public class Human extends Player
{
    public Human(String name, int pos)
    {
        super(); 
        super.name= name;
        super.playerPos = pos; 
    }
    
    public Card Discard(int dex)
    {
        Card c; 
        if(dex >= hand.size())
        {
            System.out.println("Card does not exsist.");
            c = null; 
        }
        else
        {
            System.out.print("Removing " );
            hand.get(dex).Print();
            System.out.println("");
            c = hand.remove(dex);
        }
	return c;
    }
}
