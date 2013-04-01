
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package practiceUno;

//~--- JDK imports ------------------------------------------------------------

import java.io.ByteArrayInputStream;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class Robot extends Player {
    private static final Logger log         = Logger.getLogger(Robot.class.getName());
    private Card.cardColor[]    colorValues = Card.cardColor.values();
    private Card                playingCard = null;

    /**
     *
     * @param name
     * @param pos
     */
    public Robot(String name, int pos) {
        super.name      = name;
        super.playerPos = pos;
    }

    public int FindCard(Card c) {
        return hand.indexOf(c);
    }

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
            case 1 :
                log.info("Trying to play a card " + playingCard.toString() + " " + d.TopCard().toString());    // need to check for wild
                d.AddDiscard(playingCard, this, null);
                state = 5;

                break;

            case 2 :
                log.info("Need to draw a card");
                super.GetCard(d.DrawNext());
                state = Decide(d.TopCard());
                tried = true;

                if (state == 2) {
                    state = 3;
                }

                break;

            case 3 :
                log.info("Pass");
                state = 5;

                break;

            case 4 :
                log.info("Time for a wild card. Choosing yellow");

                ByteArrayInputStream in = new ByteArrayInputStream("YELLOW".getBytes());

                System.setIn(in);
                d.AddDiscard(playingCard, this, new Scanner(System.in));
                state = 5;

                break;

            case 5 :
                log.info("End turn");
                done = true;

                break;

            default :
                log.severe("I don't know what to do");
            }
        }

        log.exiting("Play a Hand", name);

        return done;
    }

    private int Decide(Card c) {
        log.entering("Decide", name);
        System.out.println("Entering function");

        int choice;

        System.out.println("Trying to show my hand.");
        super.ShowHand();

        if (c != null) {
            System.out.println("After card check for null");

            for (Card play : hand) {
                System.out.println("Inside for loop " + c.toString() + " " + play.toString());

                if (((play.GetColor() == null) || (c.GetColor() == null)) ||!play.GetColor().equals(c.GetColor())) {
                    log.info(String.format("Trying to match a color %s %s", play.GetColor().toString(),
                                           c.GetColor().toString()));
                    playingCard = play;

                    break;
                } else if (play.getClass().equals(NumberCard.class) && c.getClass().equals(NumberCard.class)) {
                    log.info(String.format("Trying to match a number %s %s", play.toString(), c.toString()));

                    NumberCard n   = (NumberCard) play;
                    NumberCard top = (NumberCard) c;

                    if (n.GetNumber() == top.GetNumber()) {
                        playingCard = play;

                        break;
                    }
                } else if (play.getClass().equals(SpecialCard.class) && c.getClass().equals(SpecialCard.class)) {
                    log.info(String.format("Trying to match a special card %s %s", play.toString(), c.toString()));

                    SpecialCard s   = (SpecialCard) play;
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

        System.out.println("Exiting function");
        log.exiting("Decide", name);

        return choice;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
