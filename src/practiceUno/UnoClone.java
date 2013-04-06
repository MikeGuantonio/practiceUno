/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
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
        SetUpLogger("testScripts/unoClone.xml"); 
        
        int pos = 0; 
        boolean endGame = false; 
        
        log.config("Creating players, deck"); 
        UnoClone uno = new UnoClone();
        ArrayList<Player> players = new ArrayList<>(); 
        Deck deck = new Deck(); 
        
        log.config("Initalizing players, deck");
        deck.Shuffle();
        SetUpPlayers(players, deck, 2);
        deck.SetUpDiscard(uno.input);
        
        log.fine("Game loop");
        log.fine(String.format("Number of Players %s", players.size()));
        System.out.println(String.format("Starting a game of uno with %s players", players.size()));
        do
        {
            Player current = players.get(pos);
            System.out.println((String.format("%s's turn. %s sees %s", current.GetName(), current.GetName(), deck.TopDiscard().toString())));

            uno.Sleep(1_000); 

            log.fine(String.format("%s's turn Deck Shows:  %s", current.GetName(), deck.TopDiscard().toString()));

            pos = current.PlayAHand(deck, players); 
            endGame = uno.CheckForEndGame(current);

            if(endGame)
            {
                System.out.println(current.GetName() + " won!");
                uno.Report(players);
                break;
            }
            else
            {
                log.fine(String.format("End %s turn ", current.GetName()));
                pos = uno.Wrap((pos+1), players.size()); //Do we check a wrap as next player if there
                uno.Report(players);                     //is a side effect.
            }    
        } while(!endGame);     
    }

    
    public void Sleep(long sleepTime)
    {
        try
        {
            Thread.sleep(sleepTime);
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(UnoClone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean CheckForEndGame(Player p)
    {
        boolean done = false; 
        if(p.TotalCards() == 0)
            done = true; 
        return done; 
        
    }
    
    
    public boolean CheckForUno(ArrayList<Player> p)
    {
        boolean uno = false;
        ArrayList<Player> unoList = new ArrayList<>(); 
        
        for(Player k : p)
        {
            if(k.Uno())
            {
                uno = true;
                unoList.add(k);
            }
        }
        return uno;
        
    }
    
    public void Report(ArrayList<Player> p)
    {
        System.out.println("");
        System.out.println("End of Turn");
        System.out.println("Cards for each player");
        
        for(Player c : p)
        {
            System.out.print(String.format("%s : %s", c.GetName(), c.TotalCards())); 
            if(c.Uno())
                System.out.print(" UNO");
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static void SetUpLogger(String fileName)
    {
        //LogManager.getLogManager().reset();
        log.setLevel(Level.SEVERE);
        /*try {
            textLog = new FileHandler(fileName);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(UnoClone.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //log.addHandler(textLog);
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
           /*if(i == 0 )
               players.add(new Human("Job", i));
           else*/
               players.add(new Robot("Com"+i, i));   
       }

        for (int i = 0; i < 7; i++)
        {
            for (Player current : players)
            {
                current.GetCard(deck.DrawNext());
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
        if(pos > maxSize-1) {
            pos = 0; 
        } 
        else if(pos < 0) {
            pos = maxSize;
        } 
        log.exiting("Wrap", null);
        return pos; 
        
    }
    
}


