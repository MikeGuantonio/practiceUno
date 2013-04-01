
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

    /**
     * Field description
     */
    protected cardColor color;

    /**
     * Enum description
     *
     */
    enum cardColor {

        BLUE, RED, GREEN, YELLOW
    }

    /**
     * Method description
     *
     */
    abstract void Print();

    /**
     * Method description
     *
     *
     * @return
     */
    abstract cardColor GetColor();

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public abstract String toString();
;
}
