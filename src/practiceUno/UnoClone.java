/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 *
 * @author mike
 */
public class UnoClone {
    private static final Logger LOG = Logger.getLogger(UnoClone.class.getName()); 
    
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
        SetUpPlayers(players, deck, 2);
        deck.SetUpDiscard(uno.input);
        int pos = 0; 
        
        while(true)
        {
            for (int i = 0; i < players.size(); i++)
            {
                while(!endTurn)
                {
                    if(players.get(pos).getClass().equals(Robot.class))
                    {
                        Robot r = (Robot)players.get(pos);
                        endTurn = r.PlayAHand(deck);
                    }
                    else
                    {
                        endTurn = uno.PlayerTurn(players, deck, pos);
                    }
                    pos = uno.Wrap((pos+1), players.size());
                    
                }
                endTurn = false;
            }
             
        }
    }
    
    
    /**
     *
     * @param players
     * @param deck
     * @param numPlayers  
     */
    public static void SetUpPlayers(ArrayList<Player> players, Deck deck, int numPlayers)
    { 
       
       if(numPlayers < 2 || numPlayers > 10)
       {
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
     * @param args
     */
    Scanner input= new Scanner(System.in); 
    
    /**
     *
     * @param pos
     * @param maxSize
     * @return
     */
    public int Wrap(int pos, int maxSize)
    {
        if(pos > maxSize-1)
            pos = 0; 
        else if(pos < 0)
            pos = maxSize; 
        return pos; 
    }
    
    /**
     *
     * @param p
     * @param d
     * @param playerPos
     * @return
     */
    public boolean PlayerTurn(ArrayList<Player> p, Deck d, int playerPos)
    {
        
        int choice = 0; 
        boolean turnStatus = false; 
        Player tmp = p.get(playerPos);
        
        if(tmp.getClass().equals(Human.class))
        {
            choice = Menu(p, d, playerPos);
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
     * @param currentPlayer
     * @return  
     */
    public boolean Play(ArrayList<Player> p, Deck d, int currentPlayer)
    {
        boolean done = false; 
        Player tmp = p.get(currentPlayer);
        
        if(tmp.getClass().equals(Human.class))
        {
            Human h = (Human)tmp; 
            d.ShowDiscard();
            h.ShowHand(); 
        
            Card c = p.get(currentPlayer).Discard(input.nextInt());
        }
        return done; 
    }
    
    
    /**
     *
     * @param p
     * @param d
     * @param playerPos  
     */
    public void DrawAndPlay(ArrayList<Player> p, Deck d, int playerPos)
    {
        int choice = 0; 
        Player tmp = p.get(playerPos);
        
        if(tmp.getClass().equals(Human.class))
        {
            p.get(playerPos).GetCard(d.DrawNext());
            p.get(playerPos).ShowHand();
            d.ShowDiscard();
            choice = input.nextInt();
        }
        
    }
    
    /**
     *
     * @param p
     * @param d
     * @return  
     */
    public boolean Skip(Player p, Deck d)
    { 
        return true;
    }
    
    /**
     *
     * @param prompt
     * @return
     */
    public String GetInput(String prompt)
    {
        String output;
        output = input.nextLine();
        return output; 
    }
    
    
    /**
     *
     * @param p 
     * @param d
     * @param currPos 
     * @return
     */
    public int Menu(ArrayList<Player> p, Deck d, int currPos)
    {
        int choice; 
        d.ShowDiscard();
        choice = input.nextInt();
        return choice; 
    }
}


