package uqam.inf5153.poker;

import java.util.*;

/**
 * Card value: ZERO, ONE do not exist in this game.
 * their goal is to follow the ordinal sequence.
 */
enum Value {
    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
    EIGHT, NINE, TEN, VALET, QUEEN, KING, ACE
}

/**
 * Card color: C=Club, D=Diamond, H=Heart, S=Spade
 */
enum Color {
    C, D, H, S
}

public class CardHand {

    private ArrayList<Card> cardHand;
    private final Character[] values = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    private final Character[] shapes = {'C', 'D', 'H', 'S'};
    private final Set<Character> setOfValues = new HashSet<>(Arrays.asList(values));
    private final Set<Character> setOfShapes = new HashSet<>(Arrays.asList(shapes));

    public CardHand() { }

    public CardHand(String hand) {
        String h = hand.trim().toUpperCase();
        cardHand = new ArrayList<>();
       // if (isValidHand(h)) {
            String[] tokens = strHandToArray(h);
            for (String token : tokens) {
                Card card = new Card(charToValue().get(token.charAt(0)), charToColor().get(token.charAt(1)));
                this.cardHand.add(card);
            }
        //}
    }

    public ArrayList<Card> getHand() {
        return cardHand;
    }

    public Set<Character> getSetOfValues() {
        return setOfValues;
    }

    public Set<Character> getSetOfShapes() {
        return setOfShapes;
    }

    private static Map<Character, Value> charToValue() {
        HashMap<Character, Value> map = new HashMap<>();
        map.put('1', Value.ONE);
        map.put('2', Value.TWO);
        map.put('3', Value.THREE);
        map.put('4', Value.FOUR);
        map.put('5', Value.FIVE);
        map.put('6', Value.SIX);
        map.put('7', Value.SEVEN);
        map.put('8', Value.EIGHT);
        map.put('9', Value.NINE);
        map.put('T', Value.TEN);
        map.put('J', Value.VALET);
        map.put('Q', Value.QUEEN);
        map.put('K', Value.KING);
        map.put('A', Value.ACE);
        return map;
    }

    private static Map<Character, Color> charToColor() {
        HashMap<Character, Color> map = new HashMap<>();
        map.put('C', Color.C);
        map.put('D', Color.D);
        map.put('H', Color.H);
        map.put('S', Color.S);
        return map;
    }

    // returns the highest card in a card hand
    public Card getHighestCard() {
        Value maxVal = getHighestCardValue();
        Color col = getColor(maxVal);
        return new Card(maxVal, col);
    }

    private Color getColor(Value cardValue){
        Color col = null;
        for (Card card : this.getHand()){
            if (card.getValue().ordinal() == cardValue.ordinal())
                return (card.getColor());
        }
        return col;
    }
    private Value getHighestCardValue() {
        ArrayList<Value> values = new ArrayList<>();
        for (Card card : this.getHand())
            values.add(card.getValue());
        return(Collections.max(values));
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

    private String[] strHandToArray(String hand) {
        return (hand.split(" "));
    }

}
