/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class Human extends Player
{
    private static final Logger log = Logger.getLogger(Human.class.getName());
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
    
    
    @Override
    public Card Discard(int dex)
    {
        Card c; 
        if(dex >= hand.size())
        {
            c = null; 
        }
        else
        {
            hand.get(dex).Print();
            c = hand.remove(dex);
        }
	return c;
    }
      
    @Override
    public boolean PlayAHand(Deck d, ArrayList<Player> p)
    {
        return true; 
    }
}
