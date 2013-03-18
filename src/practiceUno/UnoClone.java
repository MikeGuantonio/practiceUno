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
        boolean endTurn = false; 
        
        //Initalize local player variables. 
        UnoClone uno = new UnoClone(); 
        ArrayList<Player> players = new ArrayList<Player>(); 
        Deck deck = new Deck(); 
        
       
        //Set up the playing field
        deck.Shuffle();
        SetUpPlayers(players, deck, Integer.parseInt(uno.GetInput("How many people are playing?") ));
        deck.SetUpDiscard(uno.input);
        
        players.get(0).SetName(uno.GetInput("What is your name"));
        
        System.out.println(players.get(0).GetName() + ". Welcome to UNO!" );
        int turnCnt = 0; 
        
        while(true)
        {
            for (int i = 0; i < players.size(); i++)
            {
                while(!endTurn)
                {
                    endTurn = uno.PlayerTurn(players.get(i), deck);
                    turnCnt++;
                    System.out.println(turnCnt);
                }
                endTurn = false;
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
                case 1: DrawAndPlay(p, d);
                        turnStatus = true; 
                        break; 
                case 2: System.out.println("Before Play method");
                        Play(p, d);
                        turnStatus = true; 
                        System.out.println("Turn over");
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
           if(i != 0)
               players.get(i).SetName("Com"+i);
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
        System.out.println("1. Draw A Card");
        System.out.println("2. Play A Card");
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
        boolean done = false; 
        
        do
        {
            System.out.println("Which card would you like to play?");
            System.out.println("Remember index starts at 0");
            System.out.print("The card on the top of the deck is  ");
            d.ShowDiscard();
            p.ShowHand(); 
        
            Card c = p.Discard(input.nextInt());
            if(c != null)
            {
                if(d.AddDiscard(c, p, input))
                    done = true;
                else
                    p.GetCard(c);
            }
            else
                System.out.println("That doesn't seem to be a card.");
        }while(!done);
        
         
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
    public boolean Skip(Player p, Deck d)
    { 
        System.out.println("Turn has ended");
        return true;
    }
    
}


