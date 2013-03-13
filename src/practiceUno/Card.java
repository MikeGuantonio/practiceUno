/*TODO: 
  Find a way to return the face and what not in a way that makes sense so 
  * that it can be removed from the deck test and still retain functionality.
  */
package practiceUno; 
  

/**
 *
 * @author mike
 */
public abstract class Card
{
   enum cardColor {BLUE,RED,GREEN, YELLOW, WILD}; 
   
    protected cardColor color; 

    abstract void Print();
}