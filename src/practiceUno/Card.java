
/*TODO:
 Find a way to return the face and what not in a way that makes sense so
 * that it can be removed from the deck test and still retain functionality.
 */
package practiceUno;

/**
 *
 * @author mike
 */
abstract class Card {

    protected cardColor color;
  
    enum cardColor {

        BLUE, RED, GREEN, YELLOW
    }
   
    abstract void Print();    
    abstract cardColor GetColor();
    
    @Override
    public abstract String toString();
    public abstract boolean match(Card c); 
    
    public boolean colorMatch(Card c)
    {
        
        boolean possible = false; 
        if(c.GetColor() != null && this.color != null && this.color.equals(c.GetColor()))
        {
            possible = true; 
        }
        return possible;
    }
}
