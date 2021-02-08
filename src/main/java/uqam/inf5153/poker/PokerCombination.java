package uqam.inf5153.poker;

import java.util.ArrayList;
import java.util.Collections;

public class PokerCombination {

    private String pokerCombination;

    public PokerCombination() {
    }

    public String findCombination(CardHand pokerHand) {
        ArrayList<Card> cards = pokerHand.getHand();
        return "the combination";
    }

    public boolean straightFlush(CardHand pokerHand) {
        boolean isStraight = straight(pokerHand);
        boolean isFlush = flush(pokerHand);
        if (isStraight && isFlush) {
            return true;
        }
        return false;
    }

    public boolean flush(CardHand pokerHand) {
        ArrayList<Card> cards = pokerHand.getHand();
        Shape shapeFirstCard = cards.get(0).getShape();
        for (Card card : cards) {
            if (card.getShape() != shapeFirstCard) {
                return false;
            }
        }
        return true;
    }

    public boolean straight(CardHand pokerHand) {
        ArrayList<Card> cards = pokerHand.getHand();

        ArrayList<Value> cardValues = new ArrayList<>();
        for (Card card : cards) {
            cardValues.add(card.getValue());
        }
        Collections.sort(cardValues);

        for (int i = 0; i < cardValues.size() - 1; i++) {
            if (cardValues.get(i).ordinal() + 1 != cardValues.get(i + 1).ordinal())
                return false;
        }
        return true;
    }

/*
    public boolean fourOfAKind(CardHand pokerHand)
    {
        ArrayList<Value> values = new ArrayList<>();
        ArrayList<Card> cards = pokerHand.getHand();
        for(Card card: cards) {
            values.add(card.getValue());
        }

        int clubs = countOcurrences(shapes,"C");
        int diamonds = countOcurrences(shapes,"D");
        int hearts = countOcurrences(shapes,"H");
        int spades = countOcurrences(shapes,"S");

        if (clubs == 4 || diamonds == 4 || hearts == 4 || spades == 4)
            return true;
        else
            return false;
    }
*/


    /**
     * @param shapes
     * @param figure
     * @return int the number of same shapes in the hand
     */
    private int shapeOcurrences(ArrayList<Shape> shapes, String figure) {
        int occurrences = 0;
        for (Shape shape : shapes) {
            if (shape.name() == figure) {
                occurrences++;
            }
        }
        return occurrences;
    }

    /**
     * find the highest card in a given hand
     * @param cs the hand to analyse, e.g., ["1C", "2C", "TD", "JH", "JS"]
     * @return the highest card code, here "J"
     */
    /*
    private static char maxVal(String[] cs) {
        int max = getTable().get(cs[0].charAt(0));
        char result = cs[0].charAt(0);
        for(String c: cs) {
            if (getTable().get(c.charAt(0)) > max) {
                max = getTable().get(c.charAt(0));
                result = c.charAt(0);
            }
        }
        return result;
    }
*/

    /**
     * Find the highest combination in a hand. We detect flush (F), pair (P), and highest card (H)
     * for now. The result is the combination detected, followed by its highest card (to avoid ex-aequo).
     * @param cs the hand, e.g., ["1C", "2C", "TD", "JH", "JS"]
     * @return the highest combination, e.g., "P J" (as the example contains a Pair of Jacks)
     */
    /*
    private static String findComb(String[] cs) {
        // Detect a flush
        char col = cs[0].charAt(1);
        boolean f = true;
        for(String c: cs) {
            if (c.charAt(1) != col)
                f = false;
        }
        if(f)
            return "F " + maxVal(cs);

        // Detect a pair
        char vMax = 'X';
        for(int i = 0; i < cs.length; i++) {
            for(int j = i+1; j < cs.length; j++) {
                if (cs[i].charAt(0) == cs[j].charAt(0)
                        && (vMax == 'X' || getTable().get(cs[i].charAt(0)) > getTable().get(vMax))) {
                    vMax = cs[i].charAt(0);
                }
            }
        }
        if(vMax != 'X')
            return "P " + vMax;

        // Nothing interesting => Highest card
        return "H " + maxVal(cs);
    }
*/

}
