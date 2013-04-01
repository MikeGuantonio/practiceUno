
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceUno;

//~--- JDK imports ------------------------------------------------------------
import java.io.ByteArrayInputStream;

import java.util.Scanner;
import java.util.logging.Logger;

//~--- classes ----------------------------------------------------------------
/**
 *
 * @author mike
 */
public class Robot extends Player {

    /**
     * Field description
     */
    private static final Logger log = Logger.getLogger(Robot.class.getName());
    /**
     * Field description
     */
    private Card.cardColor[] colorValues = Card.cardColor.values();
    /**
     * Field description
     */
    private Card playingCard = null;

    /**
     *
     * @param name
     * @param pos
     */
    public Robot(String name, int pos) {
        super.name = name;
        super.playerPos = pos;
    }

    /**
     * Method description
     *
     *
     * @param c
     *
     * @return
     */
    public int FindCard(Card c) {
        return hand.indexOf(c);
    }

    /**
     * Method description
     *
     *
     * @param dex
     *
     * @return
     */
    @Override
    public Card Discard(int dex) {
        Card retC;

        if (dex == -1) {
            retC = null;
        } else {
            retC = hand.get(dex);
        }

        return retC;
    }

    /**
     *
     * @param d
     * @return
     */
    public boolean PlayAHand(Deck d) {
        log.entering("Play a hand", name);

        boolean done = false;
        boolean tried = false;

        log.info("Trying to decide");

        int state = Decide(d.TopCard());

        if (state == 1) {
            log.info("Decided " + state + " " + playingCard.toString());
        } else {
            log.info("decided " + state);
        }

        while (!done) {
            switch (state) {
                case 1:
                    log.info("Trying to play a card " + playingCard.toString() + " " + d.TopCard().toString());

                    if (playingCard.getClass().equals(WildCard.class)) {
                        state = 4;
                    }

                    d.AddDiscard(playingCard, this, null);
                    state = 5;

                    break;

                case 2:
                    log.info("Need to draw a card");
                    super.GetCard(d.DrawNext());
                    state = Decide(d.TopCard());
                    tried = true;

                    if (state == 2) {
                        state = 3;
                    }

                    break;

                case 3:
                    log.info("Pass");
                    state = 5;

                    break;

                case 4:
                    log.info("Time for a wild card. Choosing yellow");

                    ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());

                    System.setIn(in);
                    d.AddDiscard(playingCard, this, new Scanner(System.in));
                    state = 5;

                    break;

                case 5:
                    log.info("End turn");
                    done = true;

                    break;

                default:
                    log.severe("I don't know what to do");
            }
        }

        log.exiting("Play a Hand", name);

        return done;
    }

    /**
     * Method description
     *
     *
     * @param c
     *
     * @return
     */
    private int Decide(Card c) {
        log.entering("Decide", name);
        log.info("Entering robot decide function");

        int choice;

        log.info(String.format("I have %s cards. ", super.TotalCards()));
        for (int i = 0; i < hand.size(); i++)
        {
            log.info(String.format("Card %s: %s", i, hand.get(i).toString())); 
        }

        if (c != null) {
            log.info(String.format("Card at top of deck is not null %s", c.toString()));

            for (Card play : hand) {
                log.info(String.format("Inside for loop %s %s", c.toString(), play.toString()));

                if (((play.GetColor() == null) || (c.GetColor() == null)) || !play.GetColor().equals(c.GetColor())) {
                    log.info(String.format("Trying to match a color %s %s", play.GetColor().toString(),
                            c.GetColor().toString()));
                    playingCard = play;

                    break;
                } else if (play.getClass().equals(NumberCard.class) && c.getClass().equals(NumberCard.class)) {
                    log.info(String.format("Trying to match a number %s %s", play.toString(), c.toString()));

                    NumberCard n = (NumberCard) play;
                    NumberCard top = (NumberCard) c;

                    if (n.GetNumber() == top.GetNumber()) {
                        playingCard = play;

                        break;
                    }
                } else if (play.getClass().equals(SpecialCard.class) && c.getClass().equals(SpecialCard.class)) {
                    log.info(String.format("Trying to match a special card %s %s", play.toString(), c.toString()));

                    SpecialCard s = (SpecialCard) play;
                    SpecialCard top = (SpecialCard) c;

                    if (s.GetSpecial().equals(top.GetSpecial())) {
                        playingCard = play;

                        break;
                    }
                }
            }
        }

        if (playingCard != null) {
            choice = 1;
        } else {
            choice = 2;
        }

        log.info("Done deciding");
        log.exiting("Decide", name);

        return choice;
    }
}




