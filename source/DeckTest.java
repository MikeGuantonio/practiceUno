public class DeckTest
{
    public static void main(String[] args)
    {
	Deck d = new Deck();
	Dealer deal = new Dealer(); 
	Card c;
 
	d.ValidateDeck();
	System.out.println(d.deck.size());
	deal.Shuffle(d); 
 
	c = d.DrawNext(); 
	c.Print(); 
    }
}