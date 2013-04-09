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
        private ArrayList<Player> players = null; 
        private int pos = -1 ; //start at -1 in order to start at 0;
        private boolean forward = true; 
        
        UnoIter(ArrayList<Player> players)
        {
            this.players = players;
        }
        
        private Player GoToStart()
        {
            Player p; 
            p = players.get(0);
            pos = 0; 
            return p;
            
        }
        
        private Player GoToEnd()
        { 
            Player p; 
            p = players.get(players.size()-1);
            pos = players.size()-1;
            return p;
        }
        
        public Player Move()
        {
            System.out.println("Entering Move of iter.");
            Player p;
            if(forward)
            {
                p = this.Next();
            }
            else
            {
                p = this.Previous();
            }
            System.out.println("Moved -> " + p.GetName());
            return p;
           
        }
        
        private Player Next()
        {
            int newPos = pos +1;
            Player p; 
            if(newPos >= players.size())
            {
               p = this.GoToStart();
            }
            else
            {
                pos++;
                p = players.get(pos);
            }
            return p;
        }
        
        private Player Previous()
        { 
            int newPos = pos-1;
            Player p; 
            if(newPos < 0)
            {
                p = this.GoToEnd();
            }
            else
            {
                p = this.Previous();
            }
            return p;
        }
        
        public void Set(Player p)
        {
           players.get(pos).clone(p); 
        }
        
        public boolean isClockWise()
        {
            return forward;
        }
        
        public void setMotion(Boolean move)
        {
            forward = move; 
        }
       
    }
    
    public static void main(String[] args)
    {  
        SetUpLogger("testScripts/unoClone.xml"); 
        
        log.config("Creating players, deck");
        boolean endGame = false;
        UnoClone uno = new UnoClone(); 
        ArrayList<Player> players = new ArrayList<>(); 
        UnoClone.UnoIter iter = new UnoClone().new UnoIter(players);
        
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
        System.out.println("The card on the discard deck is " + deck.TopDiscard().toString());
        
        do
        {
            System.out.println("Before moving the current iter");
            current = iter.Move();
            inPlayCard = current.PlayAHand(deck.TopDiscard(), deck);
            
            
            uno.Sleep(1_000);
            if(inPlayCard == null)
            {
                System.out.println(current.GetName() + " passes");
            }
            else
            {
                System.out.println(current.GetName() +  " played " + inPlayCard.toString() + " against " + deck.TopDiscard().toString());
                iter.Set(current);
                
                switch(inPlayCard.getClass().getSimpleName())
                {
                    case "NumberCard" : //iter.Move();
                                        iter.Move();
                                        break;
                        
                    case "SpecialCard" : SpecialCard sp = (SpecialCard)inPlayCard;
                                         //sp.PlaySpecial(players, deck, pos); //Look at what a special needs
                                         iter.Move();
                                         break;
                        
                    case "WildCard": WildCard w = (WildCard)inPlayCard;
                                     //w.PlayWild(input, players, deck, pos)// look at what a wild needs.
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
    
}


