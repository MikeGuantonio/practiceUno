/*TODO: 
  Find a way to return the face and what not in a way that makes sense so 
  * that it can be removed from the deck test and still retain functionality.
  */
package practiceUno; 
  

/**
 *
 * @author mike
 */
abstract class Card
{
   
   protected cardColor color; 

    abstract void Print();
    abstract cardColor GetColor(); 
    enum cardColor {BLUE,RED,GREEN, YELLOW}; 
}