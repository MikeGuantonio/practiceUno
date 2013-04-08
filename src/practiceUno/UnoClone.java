/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

import java.util.ArrayList;
import java.util.ListIterator;
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
    public void main(String[] args)
    {  
        SetUpLogger("testScripts/unoClone.xml"); 
        
        log.config("Creating players, deck");
        boolean endGame = false;
        UnoClone uno = new UnoClone(); 
        ArrayList<Player> players = new ArrayList<>(); 
        UnoIter iter = new UnoIter(players);
        Deck deck = new Deck();  
        Player current = null; 
        Card inPlayCard = null; 
        
        log.config("Initalizing players, deck");
        deck.Shuffle();
        SetUpPlayers(players, deck, 2);
        deck.SetUpDiscard(uno.input);
        
        log.fine("Game loop");
        log.fine(String.format("Number of Players %s", players.size()));
        
        System.out.println(String.format("Starting a game of uno with %s players", players.size()));
        
        current = iter.CurrentPlayer();
        do
        {
            inPlayCard = current.PlayAHand(deck.TopDiscard());
            uno.Sleep(1_000);
            if(inPlayCard == null)
            {
                System.out.println(current.GetName() + " passes");
            }
            else
            {
                switch(inPlayCard.getClass().getSimpleName())
                {
                    case "NumberCard" : iter.Move();
                                        break;
                        
                    case "SpecialCard" : SpecialCard sp = (SpecialCard)c;
                                         sp.PlaySpecial(players, deck, pos); //Look at what a special needs
                                         //Possible...
                                            iter.Move();
                                         //Or
                                            iter.Move(newPos); 
                                         break;
                        
                    case "WildCard": WildCard w = (WildCard)c;
                                     w.PlayWild(input, players, deck, pos)// look at what a wild needs.
                                     iter.Move();
                                     break;
                }
                deck.AddDiscard(inPlayCard);
            }
            
            endGame = uno.CheckForEndGame(current);
            if(endGame)
                System.out.println(current.GetName() + " won!");
            uno.Report(players);
               
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
    
    
    public class UnoIter 
    {
        private ListIterator<Player> iter;
        private boolean forward = true; 
        
        UnoIter(ArrayList<Player> p)
        {
            iter = p.listIterator();
        }
        
        public Player GoToStart()
        {
            Player p; 
            while(iter.hasPrevious())
            {
                iter.previous();
            }
            p = (Player)iter;
            return p;
        }
        
        public Player GoToEnd()
        {
            Player p = null; 
            while(iter.hasNext())
            {
                iter.next();
            }           
            p = (Player)iter; 
            return p;
        }
        
        public void Move()
        {
            if(forward)
            {
                this.Next();
            }
            else
            {
                this.Previous();
            }
        }
        
        public Player Next()
        {
            Player p = null; 
            if(iter.hasNext())
                p = iter.next();
            else
                p = this.GoToStart();
            return p;
        }
        
        public void Previous()
        {
            Player p = null; 
            if(iter.hasPrevious())
                p = iter.previous();
            else
                p = this.GoToEnd();
        }
        
        public void Set(Player p)
        {
            iter.set(p);
        }
        
        public boolean isClockWise()
        {
            return forward;
        }
        
        public void setMotion(Boolean move)
        {
            forward = move; 
        }
        
        public Player CurrentPlayer()
        {
            return (Player)iter; 
        }
        
    }
    
}


