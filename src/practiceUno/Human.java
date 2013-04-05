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
    
    @Override
    public void ShowHand()
    {
        for (int i = 0; i < hand.size(); i++) 
        {
            System.out.println(String.format("[%s]: %s", i, hand.get(i).toString()));
        }
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
                    
                case 1: PlayMenu();
                        int card = GetPlayerChoice();
                        if(card == -1)
                            state = 2; 
                        else
                        {
                            d.AddDiscard(this.Discard(card), p, input, this.GetPlayerPos());
                            state = 5;
                        }
                        break;
                    
                case 2: Card c = d.DrawNext();
                        this.GetCard(c);
                        System.out.println("You got " + c.toString());
                        DrawMenu();
                        state = this.GetPlayerChoice();
                        break;
                    
                case 3: System.out.println(this.GetName() + " passes");
                        state = 5; 
                        break;
                    
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
    
    private void DrawMenu()
    {
        System.out.println("What would you like to do?");
        System.out.println("1. Play a Card");
        System.out.println("3. Pass ");
    }
    
    private void PlayMenu()
    {
        System.out.println("Please choose a card");
        this.ShowHand();
        System.out.println("Which card would you like to play?");
        System.out.println("Please use a -1 to draw a card");
    }
    
    private void Menu(Card c)
    {
        System.out.println("");
        System.out.println("What would you like to do? ");
        System.out.println("1. Play a card");
        System.out.println("2. Draw a card");
    }
}
