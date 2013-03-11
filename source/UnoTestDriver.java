public class UnoTestDriver
{
    public static void main(String[] args)
    {
	Dealer d = new Dealer();
	Player p = new Player();
	Deck cards  = new Deck(); 
	
	cards.ValidateDeck(); 

	System.out.println("Shuffling the deck as the dealer"); 
	d.Shuffle(cards); 
	
	System.out.println("I am the player. I have " + p.TotalCards() + " cards."); 
	System.out.println("Dealing the cards to the player. " + cards.totalCardNotPlayed + " in all ");
	for(int i = 0; i < 7; i++)
	{
	    p.GetCard(d.Deal(cards));
        }
	System.out.println("Total left: " + cards.totalCardNotPlayed); 
	System.out.println("I am the player. I have " + p.TotalCards() + " cards."); 

	System.out.println("Player please show your hand");
	p.ShowHand();  
    }
}