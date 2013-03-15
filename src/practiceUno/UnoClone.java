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
       
        //Set up the playing field
        deck.Shuffle();
        SetUpPlayers(players, deck);
        deck.SetUpDiscard();
        players.get(0).SetName(uno.GetInput("What is your name?"));
        
        
        System.out.println(players.get(0).GetName() + ". Welcome to UNO!" );
        System.out.println("Print menu...");
        players.get(1).ShowHand();
        deck.ShowDiscard(); System.out.println("");
        deck.AddDiscard(players.get(0).Discard(1), players.get(1)); 
        deck.ShowDiscard(); System.out.println("");
        
        players.get(1).ShowHand();
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
        System.out.println(prompt);
        output = input.nextLine();
        return output; 
    }
}
