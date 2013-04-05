/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class Human extends Player
{
    private static final Logger log = Logger.getLogger(Human.class.getName());
    public Scanner input = new Scanner(System.in);
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
        log.setLevel(Level.ALL);
        
    }
    
    Human()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Change discard to take a card and just find the card. 
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
        int state = 0; 
        boolean done = false;
         
        
        while(!done)
        {
            switch(state)
            {
                case 0: Menu(d.TopDiscard());
                        state = GetPlayerChoice();
                        break;  
                    
                case 1: System.out.println("Play a card"); 
                        state = 5; 
                        break;
                    
                case 2: System.out.println("Draw a card");
                        state = 5; 
                        break;
                    
                case 3: break;
                    
                case 4: break;
                    
                case 5: done = true;
                        break;
                default: break;
            }
        }
        return true; 
    }
    
    private int GetPlayerChoice()
    {
        int choice = 0; 
        choice = input.nextInt(); 
        return choice;
    }
    
    private void Menu(Card c)
    {
        System.out.println("");
        this.ShowHand();
        System.out.println("What would you like to do? ");
        System.out.println("1. Play a card");
        System.out.println("2. Draw a card");
        System.out.println("");
        System.out.println("Top card is " + c.toString());
        
    }
}
