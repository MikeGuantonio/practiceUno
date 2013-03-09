public class DealerTest
{
    public static void main(String[] args)  
    {
	Dealer d = new Dealer(); 
	Deck cards = new Deck(); 
	
	cards.ValidateDeck(); 
	d.ShowDeck(cards);

	d.Shuffle(cards); 
	d.ShowDeck(cards); 
    }
}