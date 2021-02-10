package uqam.inf5153.poker;

import java.util.ArrayList;
import java.util.Collections;

enum Ranking {
    highCard, onePair, twoPair, threeOfAKind, straight,
    flush, fullHouse, fourOfAKind, straightFlush, royalFlush
}

public class PokerCombination {

    public PokerCombination() {
    }

    public Ranking detect(CardHand pokerHand) {
        if(royalFlush(pokerHand)) return Ranking.royalFlush;
        if(straightFlush(pokerHand)) return Ranking.straightFlush;
        if(fourOfAKind(pokerHand)) return Ranking.fourOfAKind;
        if(fullHouse(pokerHand)) return Ranking.fullHouse;
        if(flush(pokerHand)) return Ranking.flush;
        if(straight(pokerHand)) return Ranking.straight;
        if(threeOfAKind(pokerHand)) return Ranking.threeOfAKind;
        if(twoPair(pokerHand)) return Ranking.twoPair;
        if(onePair(pokerHand)) return Ranking.onePair;
        return Ranking.highCard;
    }

    private boolean royalFlush(CardHand pokerHand) {
        boolean isStraight = straight(pokerHand);
        int firstCardValue = pokerHand.getHand().get(0).getValue().ordinal();
        boolean isFlush = flush(pokerHand);
        if (isStraight && isFlush && (firstCardValue == Value.TEN.ordinal()))
            return true;
        return false;
    }

    private boolean straightFlush(CardHand pokerHand) {
        boolean isStraight = straight(pokerHand);
        boolean isFlush = flush(pokerHand);
        if (isStraight && isFlush)
            return true;
        return false;
    }

    private boolean flush(CardHand pokerHand) {
        ArrayList<Card> cards = pokerHand.getHand();
        Shape shapeFirstCard = cards.get(0).getShape();
        for (Card card : cards) {
            if (card.getShape() != shapeFirstCard)
                return false;
        }
        return true;
    }

    private boolean straight(CardHand pokerHand) {
        ArrayList<Value> values = extractValuesFromPokerHand(pokerHand);
        Collections.sort(values);
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i).ordinal() + 1 != values.get(i + 1).ordinal())
                return false;
        }
        return true;
    }

    private boolean fourOfAKind(CardHand pokerHand) {
        ArrayList<Value> values = extractValuesFromPokerHand(pokerHand);
        for (Value cardValue : values) {
            int fourCardsWithSameValue = Collections.frequency(values, cardValue);
            if (fourCardsWithSameValue == 4)
                return true;
        }
        return false;
    }

    private boolean fullHouse(CardHand pokerHand) {
        boolean isThreeOfAKind = threeOfAKind(pokerHand);
        boolean isOnePair = onePair(pokerHand);
        if (isThreeOfAKind && isOnePair)
            return true;
        return false;
    }

    private boolean threeOfAKind(CardHand pokerHand) {
        ArrayList<Value> values = extractValuesFromPokerHand(pokerHand);
        for (Value cardValue : values) {
            int threeCardsWithSameValue = Collections.frequency(values, cardValue);
            if (threeCardsWithSameValue == 3)
                return true;
        }
        return false;
    }

    private boolean onePair(CardHand pokerHand) {
        ArrayList<Value> values = extractValuesFromPokerHand(pokerHand);
        for (Value cardValue : values) {
            int oneCardWithSameValue = Collections.frequency(values, cardValue);
            if (oneCardWithSameValue == 2)
                return true;
        }
        return false;
    }

    private boolean twoPair(CardHand pokerHand) {
        ArrayList<Value> values = extractValuesFromPokerHand(pokerHand);
        int numberOfPairs = 0;
        Value v = Value.ZERO;
        for (Value cardValue : values) {
            int twoCardsWithSameValue = Collections.frequency(values, cardValue);
            if (twoCardsWithSameValue == 2) {
                if (v != cardValue) {
                    numberOfPairs++;
                    v = cardValue;
                }
            }
        }
        if (numberOfPairs == 2)
            return true;
        return false;
    }

    private Value highCard(CardHand pokerHand) {
        ArrayList<Value> values = extractValuesFromPokerHand(pokerHand);
        return Collections.max(values);
    }

    private ArrayList<Value> extractValuesFromPokerHand(CardHand pokerHand) {
        ArrayList<Card> cards = pokerHand.getHand();
        ArrayList<Value> values = new ArrayList<>();
        for (Card card : cards) {
            values.add(card.getValue());
        }
        return values;
    }

}
