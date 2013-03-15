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
        int maxPlayers = 2; //Only a testing variable
        boolean endTurn = false; 
        
        //Initalize local player variables. 
        UnoClone uno = new UnoClone(); 
        ArrayList<Player> players = new ArrayList<Player>(); 
        Deck deck = new Deck(); 
        
       
        //Set up the playing field
        deck.Shuffle();
        SetUpPlayers(players, deck, Integer.parseInt(uno.GetInput("How many people are playing?") ));
        deck.SetUpDiscard();
        players.get(0).SetName(uno.GetInput("What is your name"));
        players.get(1).SetName(uno.GetInput("What is your name player 2"));
        
        System.out.println(players.get(0).GetName() + ". Welcome to UNO!" );
        while(true)
        {
            for (int i = 0; i < maxPlayers; i++)
            {
                while(!endTurn)
                {
                    endTurn = uno.PlayerTurn(players.get(i), deck);
                }
            }
             
        }
    }
    
    
    public boolean PlayerTurn(Player p, Deck d)
    {
        int choice; 
        boolean turnStatus = false; 
        
        choice = Menu(p, d);
        
            switch(choice)
            {
                case 1: p.ShowHand();
                        break; 
                case 2: d.ShowDiscard();
                        break; 
                case 3: DrawAndPlay(p, d);
                        turnStatus = true; 
                        break; 
                case 4: Play(p, d);
                        turnStatus = true; 
                        break;  
                default: System.out.println("Cannot process!");
                        break; 
            }
            return turnStatus; 
    }
    /**
     *
     * @param players
     * @param deck
     */
    public static void SetUpPlayers(ArrayList<Player> players, Deck deck, int numPlayers)
    {
       if(numPlayers < 2 || numPlayers > 10)
       {
           System.out.println("Cannot play this game!");
           System.exit(345);
       }
       
       for (int i = 0; i < numPlayers; i++) 
       {
           players.add(new Player());
           players.get(i).SetPlayerPos(i);
       }

        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < numPlayers; j++)
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
    public int Menu(Player p, Deck d)
    {
        int choice; 
        System.out.print("Card on top of the deck is"); 
        d.ShowDiscard();
        System.out.println(p.GetName() + ", what would you like to do? ");
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
        System.out.print("The card on the top of the deck is  ");
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


