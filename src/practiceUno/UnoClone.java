/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


/**
 *
 * @author mike
 */
public class UnoClone {
    static private FileHandler textLog; 
    private static final Logger log = Logger.getLogger(UnoClone.class.getName()); 
    public Scanner input= new Scanner(System.in); 
    
    /**
     *
     * @param args
     */
    public static void main(String[] args)
    { 
        //LogManager.getLogManager().reset();
        log.setLevel(Level.ALL);
        try {
            textLog = new FileHandler("unoClone.xml");
        } catch (IOException ex) {
            Logger.getLogger(UnoClone.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(UnoClone.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.addHandler(textLog);
        boolean endTurn = false; 
        int pos = 0; 
        
        
        log.config("Creating players, deck"); 
        UnoClone uno = new UnoClone(); 
        ArrayList<Player> players = new ArrayList<Player>(); 
        Deck deck = new Deck(); 
        
        log.config("Initalizing players, deck");
        deck.Shuffle();
        SetUpPlayers(players, deck, 2);
        deck.SetUpDiscard(uno.input);
        
        log.fine("Game loop");
        System.out.println("Players " + players.size());
        for (int i = 0; i < players.size(); i++)
        {
               
            log.info("Start of player turn");
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
            log.info("End player turn");
        
            endTurn = false;
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
       log.entering("SetupPlayers", "Main");
       if(numPlayers < 2 || numPlayers > 10)
       {
           System.exit(345);
       }
       
       for (int i = 0; i < numPlayers; i++) 
       {
           players.add(new Robot("Com"+i, i));   
       }

        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < numPlayers; j++)
            {
                players.get(j).GetCard(deck.DrawNext());
            }
        }
        log.exiting("SetupPlayers", "Main");
    }
    
   
    
    /**
     *
     * @param pos
     * @param maxSize
     * @return
     */
    public int Wrap(int pos, int maxSize)
    {
        log.entering("Wrap", null);
        if(pos > maxSize-1)
            pos = 0; 
        else if(pos < 0)
            pos = maxSize; 
        log.exiting("Wrap", null);
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
        log.entering("Player Turn", null);
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
            log.exiting("Player Turn", null);
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
        log.entering("Play", null);
        boolean done = false; 
        Player tmp = p.get(currentPlayer);
        
        if(tmp.getClass().equals(Human.class))
        {
            Human h = (Human)tmp; 
            d.ShowDiscard();
            h.ShowHand(); 
        
            Card c = p.get(currentPlayer).Discard(input.nextInt());
        }
        log.exiting("Play", null);
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
        log.entering("Draw and Play", null);
        int choice = 0; 
        Player tmp = p.get(playerPos);
        
        if(tmp.getClass().equals(Human.class))
        {
            p.get(playerPos).GetCard(d.DrawNext());
            p.get(playerPos).ShowHand();
            d.ShowDiscard();
            choice = input.nextInt();
        }
        log.exiting("Draw and Play", null);
        
    }
    
    /**
     *
     * @param p
     * @param d
     * @return  
     */
    public boolean Skip(Player p, Deck d)
    { 
        log.entering("Skip -- Needs removed", null);
        return true;
    }
    
    /**
     *
     * @param prompt
     * @return
     */
    public String GetInput(String prompt)
    {
        log.entering("GetInput", prompt);
        String output = input.nextLine();
        log.exiting("Get Input", output);
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
        log.entering("menu", null);
        int choice; 
        d.ShowDiscard();
        choice = input.nextInt();
        log.exiting("menu", null);
        return choice; 
    }
}


