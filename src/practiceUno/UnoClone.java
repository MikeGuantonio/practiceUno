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
       // SetUpPlayers(players, deck, Integer.parseInt(uno.GetInput("How many people are playing?") ));//Ask for robots.
        SetUpPlayers(players, deck, 2);
        deck.SetUpDiscard(uno.input);
                
        System.out.println(players.get(0).GetName() + ". Welcome to UNO!" );
        int pos = 0; 
        
        while(true)
        {
            for (int i = 0; i < players.size(); i++)
            {
                while(!endTurn)
                {
                    endTurn = uno.PlayerTurn(players, deck, pos);
                    System.out.println("Turn over " + players.get(pos).TotalCards());
                    pos = uno.Wrap((pos+1), players.size());
                    
                }
                endTurn = false;
            }
             
        }
    }
    
    public int Wrap(int pos, int maxSize)
    {
        if(pos > maxSize-1)
            pos = 0; 
        else if(pos < 0)
            pos = maxSize; 
        return pos; 
    }
    
    public boolean PlayerTurn(ArrayList<Player> p, Deck d, int playerPos)
    {
        Scanner s = new Scanner(System.in);
        int choice = 0; 
        boolean turnStatus = false; 
        Player tmp = p.get(playerPos);
        
        if(tmp.getClass().equals(Robot.class))
        {
            Robot r = (Robot)p.get(playerPos); 
            choice = r.Decide(d.TopCard());
        }
        else if(tmp.getClass().equals(Human.class))
        {
            choice = Menu(p, d, playerPos);
        }
        
            switch(choice)
            {
                case 2: DrawAndPlay(p, d, playerPos);
                        turnStatus = true; 
                        break; 
                case 1: turnStatus = Play(p, d, playerPos);
                        break;  
                default:System.out.println("Cannot process!");
                        break; 
            }
            
            if(!turnStatus){
               DrawAndPlay(p, d, playerPos);
               turnStatus = true; 
            }
            return turnStatus; 
    }
    
    
    /**
     *
     * @param p
     * @param d
     */
    public boolean Play(ArrayList<Player> p, Deck d, int currentPlayer)
    {
        boolean done = false; 
        Player tmp = p.get(currentPlayer);
        
        if(tmp.getClass().equals(Human.class))
        {
            System.out.println("Human Turn");
            Human h = (Human)tmp; 
            
            System.out.println("Which card would you like to play?");
            System.out.println("Remember index starts at 0");
            System.out.print("The card on the top of the deck is  ");
            d.ShowDiscard();
            h.ShowHand(); 
        
            Card c = p.get(currentPlayer).Discard(input.nextInt());
            if(c != null)
            {
                if(d.AddDiscard(c, h, input))
                {
                    done = true;
                    d.SideEffect(c, p, currentPlayer);
                }
                else
                    h.GetCard(c);
            }
            else
                System.out.println("That doesn't seem to be a card.");
        }
        else if(tmp.getClass().equals(Robot.class))
        {
            boolean possible = false; 
            Robot r = (Robot)p.get(currentPlayer); 
            System.out.print(r.GetName() + "'s Turn ");
            System.out.println("Possible Matches: " + r.PossMatch() + " Cards: " + r.TotalCards());
            while(!possible && r.PossMatch() != 0)
            {
                Card printer = r.Discard(0);
                System.out.print("Trying ");
                printer.Print();
                System.out.print(" ");
                d.ShowDiscard();
                possible = d.AddDiscard(printer, tmp, input);
                if(!possible)
                {
                    r.GetCard(printer);
                }
            }
            r.Forget();
            done = true; 
        }
        return done; 
    }
    
    /**
     *
     * @param p
     * @param d
     */
    public void DrawAndPlay(ArrayList<Player> p, Deck d, int playerPos)
    {
        int choice = 0; 
        Player tmp = p.get(playerPos);
        
        if(tmp.getClass().equals(Human.class))
        {
            System.out.println("Looks like you need to draw a card!");
            p.get(playerPos).GetCard(d.DrawNext());
            System.out.println("You drew a new card. This is your hand");
            p.get(playerPos).ShowHand();
            System.out.println("Card on the top of discard is: ");
            d.ShowDiscard();
            System.out.println("");
        
            System.out.println("What do you want to do?");
            System.out.println("1. Play a card");
            System.out.println("2. Skip a turn");
            choice = input.nextInt();
        }
        else if(tmp.getClass().equals(Robot.class))
        {
            Robot r = (Robot)tmp; 
            choice = r.Decide(d.TopCard());
        }
        
        switch(choice)
        {
            case 1: Play(p, d, playerPos); 
                    break;
            case 2: Skip(p.get(playerPos), d);
                    break;
            default: System.out.println("This is not a vaild choice.");
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
           players.add(new Robot("Com"+i, i));
          /* if(i != 0)
               players.add(new Robot("Com"+i, i));
           else
               players.add(new Human(uno.GetInput("What is your name"), i)); */   
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
    public int Menu(ArrayList<Player> p, Deck d, int currPos)
    {
        int choice; 
        System.out.print("Card on top of the deck is"); 
        d.ShowDiscard();
        System.out.println(p.get(currPos).GetName() + ", what would you like to do? ");
        System.out.println("1. Play A Card");
        System.out.println("2. Draw A Card");
        choice = input.nextInt();
        return choice; 
    }
}


