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
    
    /**
     *
     * @param args
     */
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
            choice = uno.Menu(deck);
            
            switch(choice)
            {
                case 1: players.get(0).ShowHand();
                        break; 
                case 2: deck.ShowDiscard();
                        break; 
                case 3: uno.DrawAndPlay(players.get(0), deck);
                        break; 
                case 4: uno.Play(players.get(0), deck);
                        break; 
                case 5:  
                        break; 
                default: System.out.println("Cannot process!");
            }
             
        }
    }
    
    /**
     *
     * @param players
     * @param deck
     */
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
                players.get(j).GetCard(deck.DrawNext());
            }
        }
    }
    
    /**
     *
     * @param prompt
     * @return
     */
    public String GetInput(String prompt)
    {
        String output;
        System.out.print(prompt + "> ");
        output = input.nextLine();
        return output; 
    }
    
    /**
     *
     * @param d
     * @return
     */
    public int Menu(Deck d)
    {
        int choice; 
        System.out.print("Card on top of the deck is"); 
        d.ShowDiscard();
        System.out.println("What would you like to do? ");
        System.out.println("1. Show Hand");
        System.out.println("2. Show Top of Deck");
        System.out.println("3. Draw A Card");
        System.out.println("4. Play A Card");
        System.out.println("5. Skip turn");
        choice = input.nextInt();
        return choice; 
    }
    
    /**
     *
     * @param p
     * @param d
     */
    public void Play(Player p, Deck d)
    {
        System.out.println("Which card would you like to play?");
        System.out.println("Remember index starts at 0");
        System.out.print("The card on the top of the deck is ");
        d.ShowDiscard();
        p.ShowHand();
        d.AddDiscard(p.Discard(input.nextInt()), p); 
    }
    
    /**
     *
     * @param p
     * @param d
     */
    public void DrawAndPlay(Player p, Deck d)
    {
        int choice; 
        System.out.println("Looks like you need to draw a card!");
        p.GetCard(d.DrawNext());
        System.out.println("You drew a new card. This is your hand");
        p.ShowHand();
        System.out.println("What do you want to do?");
        System.out.println("Play a card");
        System.out.println("Skip a turn");
        choice = input.nextInt(); 
        
        switch(choice)
        {
            case 1: Play(p, d); 
                    break;
            case 2: Skip(p, d);
                    break;
        }
        
    }
    
    /**
     *
     * @param p
     * @param d
     */
    public void Skip(Player p, Deck d)
    {
        System.out.println("Turn has ended");
    }
    
}


