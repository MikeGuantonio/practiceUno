public class PlayerTest
{
    public static void main(String[] args)
    {
	Player p = new Player();
	Card c = new Card(Card.cardColor.BLUE,  Card.cardFace.NINE, "none");
	System.out.println("Giving a player a card"); 
	p.GetCard(c); 
	p.ShowHand(); 
	
	System.out.println("Player giving it back"); 
	p.Discard(c); 
	p.ShowHand(); 
    } 
}