package uqam.inf5153.poker;

import java.util.*;

enum Value {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10), VALET(11), QUEEN(12), KING(13), ACE(14);

    private final int value;

    Value (int value){
        this.value = value;
    }

    private int isWorth(){
        return value;
    }

}


/**
 * Card shape: C=Club, D=Diamond, H=Heart, S=Spade
 */
enum Shape {
    C, D, H, S
}

public class CardHandBuilder {

    private String error;
    ArrayList<Card> cardHand;

    public void setHand(String hand){
        try{
            String[] tokens = strHandToArray(hand);
            for (String token: tokens) {
                token = token.toUpperCase();
                char value = token.charAt(0);
                char shape = token.charAt(1);
                Card card = new Card(charToValue(value), charToShape(shape));
                this.cardHand.add(card);
            }
        } catch(Exception e){
            this.error = "Invalid Hand: setHand(): " + e;
        }
    }

    private Value charToValue(char v) {
        switch (v) {
            case '2': return Value.TWO;
            case '3': return Value.THREE;
            case '4': return Value.FOUR;
            case '5': return Value.FIVE;
            case '6': return Value.SIX;
            case '7': return Value.SEVEN;
            case '8': return Value.EIGHT;
            case '9': return Value.NINE;
            case 'T': return Value.TEN;
            case 'J': return Value.VALET;
            case 'Q': return Value.QUEEN;
            case 'K': return Value.KING;
            case 'A': return Value.ACE;
            default: return null;
        }
    }

    private Shape charToShape(char s) {
        switch (s) {
            case 'C': return Shape.C;
            case 'D': return Shape.D;
            case 'H': return Shape.H;
            case 'S': return Shape.S;
            default: return null;
        }
    }

    private String[] strHandToArray(String hand) {
        Character[] values = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };
        Set<Character> vs = new HashSet<>(Arrays.asList(values));
        Character[] shapes = { 'C', 'D', 'H', 'S' };
        Set<Character> ss = new HashSet<>(Arrays.asList(shapes));
        String[] cards = hand.split(" ");
        if (cards.length != 5)
            this.error = "ERROR: There is more thant one card.";
        for(String oneCard: cards) {
            if(oneCard.length() != 2)
                this.error = "ERROR: A card has only two attributes (value and shape).";
            if(!vs.contains(oneCard.charAt(0)) || !ss.contains(oneCard.charAt(1)))
                this.error = "ERROR: Invalid card!";
        }
        return cards;
    }

}
