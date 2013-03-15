/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class UnoClone {
    /**
     *
     * @param args
     */
    Scanner input= new Scanner(System.in); 
    
    public static void main(String[] args)
    { 
        //Initalize local player variables. 
        UnoClone uno = new UnoClone(); 
        ArrayList<Player> players = new ArrayList<Player>(); 
        Deck deck = new Deck(); 
        int choice; 
       
        //Set up the playing field
        deck.Shuffle();
        SetUpPlayers(players, deck);
        deck.SetUpDiscard();
        players.get(0).SetName(uno.GetInput("What is your name"));
        
        
        System.out.println(players.get(0).GetName() + ". Welcome to UNO!" );
        while(true)
        {
            System.out.println("What would you like to do?");
            choice = uno.Menu();
            
            switch(choice)
            {
                case 1: players.get(0).ShowHand();
                        break; 
                case 2: deck.ShowDiscard();
                        break; 
                case 3: players.get(0).GetCard(deck.DrawNext());
                        break; 
                case 4: uno.Play(players.get(0), deck);
                        break; 
                default: System.out.println("Cannot process!");
            }
             
        }
    }
    
    public static void SetUpPlayers(ArrayList<Player> players, Deck deck)
    {
       for (int i = 0; i < 10; i++) 
       {
           players.add(new Player());
           players.get(i).SetPlayerPos(i);
       }

        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                /*if(j == 0 )
                {
                    players.get(j).GetCard((Card)new WildCard(WildCard.cardWild.WILDDRFOUR));
                }
                else*/
                    players.get(j).GetCard(deck.DrawNext());
            }
        }
    }
    
    public String GetInput(String prompt)
    {
        String output;
        System.out.print(prompt + ">");
        output = input.nextLine();
        return output; 
    }
    
    public int Menu()
    {
        int choice; 
        System.out.println("What would you like to do? ");
        System.out.println("1. Show Hand");
        System.out.println("2. Show Top of Deck");
        System.out.println("3. Draw A Card");
        System.out.println("4. Play A Card");
        choice = input.nextInt();
        return choice; 
    }
    
    public void Play(Player p, Deck d)
    {
        System.out.println("Which card would you like to play?");
        System.out.println("Remember index starts at 0");
        p.ShowHand();
        d.AddDiscard(p.Discard(input.nextInt()), p); 
        p.GetCard(d.DrawNext());
    }
    
}


