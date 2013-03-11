public class DeckTest
{
    public static void main(String[] args)
    {
	Deck d = new Deck();
	Card c;
 
	d.PrintDeck(); 
	c = d.DrawNext(); 
	System.out.print("I got a "); 
        c.Print();
        System.out.println("");
        
    }
}