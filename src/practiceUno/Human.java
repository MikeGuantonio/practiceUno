/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class Human extends Player
{
    private static final Logger LOG = Logger.getLogger(Human.class.getName());
    /**
     *
     * @param name
     * @param pos
     */
    public Human(String name, int pos)
    {
        super(); 
        super.name= name;
        super.playerPos = pos; 
    }
    
    Human()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
