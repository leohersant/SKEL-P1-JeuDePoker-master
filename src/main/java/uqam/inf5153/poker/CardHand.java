package uqam.inf5153.poker;

import java.util.*;

enum Ranking {
    highCard, onePair, twoPair, threeOfAKind, straight,
    flush, fullHouse, fourOfAKind, straightFlush, royalFlush
}

/**
 * Card value: ZERO, ONE do not exist in this game.
 * their goal is to follow the ordinal sequence.
 */
enum Value {
    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
    EIGHT, NINE, TEN, VALET, QUEEN, KING, ACE;
}

/**
 * Card shape: C=Club, D=Diamond, H=Heart, S=Spade
 */
enum Shape {
    C, D, H, S
}

public class CardHand {

    private Character[] values = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    private Character[] shapes = {'C', 'D', 'H', 'S'};
    private Set<Character> setOfValues = new HashSet<>(Arrays.asList(values));
    private Set<Character> setOfShapes = new HashSet<>(Arrays.asList(shapes));
    private ArrayList<Card> cardHand = new ArrayList<Card>();
    String ranking;

    public CardHand(String hand) {
        String h = hand.trim().toUpperCase();
        if (isValidHand(h)) {
            String[] tokens = strHandToArray(h);
            for (String token : tokens) {
                Card card = new Card(charToValue(token.charAt(0)), charToShape(token.charAt(1)));
                this.cardHand.add(card);
            }
        }
    }

    private Value charToValue(char v) {
        switch (v) {
            case '1':
                return Value.ONE;
            case '2':
                return Value.TWO;
            case '3':
                return Value.THREE;
            case '4':
                return Value.FOUR;
            case '5':
                return Value.FIVE;
            case '6':
                return Value.SIX;
            case '7':
                return Value.SEVEN;
            case '8':
                return Value.EIGHT;
            case '9':
                return Value.NINE;
            case 'T':
                return Value.TEN;
            case 'J':
                return Value.VALET;
            case 'Q':
                return Value.QUEEN;
            case 'K':
                return Value.KING;
            case 'A':
                return Value.ACE;
            default:
                return Value.ZERO;
        }
    }

    private Shape charToShape(char s) {
        switch (s) {
            case 'C':
                return Shape.C;
            case 'D':
                return Shape.D;
            case 'H':
                return Shape.H;
            case 'S':
                return Shape.S;
            default:
                return null;
        }
    }

    public ArrayList<Card> getHand() {
        return cardHand;
    }

    public String getRanking() {
        return ranking;
    }

    // TODO:: players can not have the same card twice
    private boolean isValidHand(String hand) {
        String[] cards = hand.split(" ");
        if (cards.length != 5)
            return false;
        for (String oneCard : cards) {
            if (oneCard.length() != 2)
                return false;
            if (!setOfValues.contains(oneCard.charAt(0)) || !setOfShapes.contains(oneCard.charAt(1)))
                return false;
        }
        return true;
    }

    public void setCardHand(String ranking) {
        this.ranking = ranking;
    }

    private String[] strHandToArray(String hand) {
        return (hand.split(" "));
    }

}
