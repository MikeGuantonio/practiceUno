public class DeckTest
{
    public static void main(String[] args)
    {
	Deck d = new Deck();
	Card c;
 
	d.CreateDeck();
	d.PrintDeck(); 
 
	c = d.DrawNext(); 
	c.Print(); 
    }
}