package uqam.inf5153.poker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

public class MainTest {

    @Before
    public void initResult() {
        Main.result = null;
    }

    @Test
    public void CardEquals() {
        Card c1 = new Card(Value.TWO, Shape.S);
        Card c2 = new Card(Value.TWO, Shape.S);
        if (c1.equals(c2)) {
            assertEquals(1, 1);
        } else {
            assertEquals(1, 2);
        }
    }

    @Test
    public void CardHash() {
        Card c1 = new Card(Value.TWO, Shape.S);
        Card c2 = new Card(Value.TWO, Shape.S);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    public void Detect1(){
        CardHand pokerHand = new CardHand("2C 4C 6C JC 8C");
        PokerCombination combination = new PokerCombination();
        String isAFlush = combination.detect(pokerHand);
        assertEquals("flush", isAFlush);
    }

    @Test
    public void Detect2(){
        CardHand pokerHand = new CardHand("TH JH QH KH AH");
        PokerCombination combination = new PokerCombination();
        String isARoyalFlush = combination.detect(pokerHand);
        assertEquals("royalFlush", isARoyalFlush);
    }

    @Test
    public void EvaluateHands(){
        CardHand pokerHand1 = new CardHand("TH JH QH KH AH");
        CardHand pokerHand2 = new CardHand("2C 4C 6C JC 8C");

        PokerCombination combination = new PokerCombination();

        String royalFlush = combination.detect(pokerHand1);
        String flush = combination.detect(pokerHand1);

        ArrayList<String> pokerHands = new ArrayList<>();
        pokerHands.add(royalFlush);
        pokerHands.add(flush);

        Dealer referee = new Dealer();

        Ranking x = referee.tellWinner(pokerHands);

        assertEquals(Ranking.royalFlush, x);
    }

    @Test
    public void Detect3(){
        CardHand pokerHand = new CardHand("QS AH 5D TC 6S");
        PokerCombination combination = new PokerCombination();
        String highCard = combination.detect(pokerHand);
        assertEquals("ACE", highCard);
    }

    @Test
    public void flush() {
        CardHand pokerHand = new CardHand("2C 4C 6C JC 8C");
        PokerCombination combination = new PokerCombination();
        boolean isAFlush = combination.flush(pokerHand);
        assertEquals(true, isAFlush);
    }

    @Test
    public void notFlush() {
        CardHand pokerHand = new CardHand("2C 4C 6D JH 8S");
        PokerCombination combination = new PokerCombination();
        boolean isAFlush = combination.flush(pokerHand);
        assertEquals(false, isAFlush);
    }

    @Test
    public void royalFlush() {
        CardHand pokerHand = new CardHand("TH JH QH KH AH");
        PokerCombination combination = new PokerCombination();
        boolean isARoyalFlush = combination.royalFlush(pokerHand);
        assertTrue(isARoyalFlush);
    }

    @Test
    public void straightFlush() {
        CardHand pokerHand = new CardHand("TH JH QH KH AH");
        PokerCombination combination = new PokerCombination();
        boolean isAStraightFlush = combination.straightFlush(pokerHand);
        assertTrue(isAStraightFlush);
    }

    @Test
    public void straight() {
        CardHand pokerHand = new CardHand("TC JC QD KH AS");
        PokerCombination combination = new PokerCombination();
        boolean isAStraight = combination.straight(pokerHand);
        assertTrue(isAStraight);
    }

    @Test
    public void notStraight() {
        CardHand pokerHand = new CardHand("2C 4C 6D JH 8S");
        PokerCombination combination = new PokerCombination();
        boolean isAStraight = combination.straight(pokerHand);
        assertEquals(false, isAStraight);
    }

    @Test
    public void fourOfAKind() {
        CardHand pokerHand = new CardHand("QH QS QD 5C QC");
        PokerCombination combination = new PokerCombination();
        boolean isAfourOfAkind = combination.fourOfAKind(pokerHand);
        assertTrue(isAfourOfAkind);
    }

    @Test
    public void threeOfAKind() {
        CardHand pokerHand = new CardHand("QH QS QD 7C 6S");
        PokerCombination combination = new PokerCombination();
        boolean isAthreeOfAkind = combination.threeOfAKind(pokerHand);
        assertTrue(isAthreeOfAkind);
    }

    @Test
    public void onePair() {
        CardHand pokerHand = new CardHand("QH QS 6D 7C 6S");
        PokerCombination combination = new PokerCombination();
        boolean isOnePair = combination.onePair(pokerHand);
        assertTrue(isOnePair);
    }

    @Test
    public void fullHouse() {
        CardHand pokerHand = new CardHand("AD AS AH 7C 7D");
        PokerCombination combination = new PokerCombination();
        boolean isFullHouse = combination.fullHouse(pokerHand);
        assertTrue(isFullHouse);
    }

    @Test
    public void twoPair() {
        CardHand pokerHand = new CardHand("QS QH 9D 9C 7D");
        PokerCombination combination = new PokerCombination();
        boolean isTwoPair = combination.twoPair(pokerHand);
        assertTrue(isTwoPair);
    }

    @Test
    public void highCard() {
        CardHand pokerHand = new CardHand("QS AH 5D TC 6S");
        PokerCombination combination = new PokerCombination();
        Value highCard = combination.highCard(pokerHand);
        assertEquals(Value.ACE, highCard);
    }


    // P1 Wins

    @Test
    public void p1F_p2H() {
        String p1 = "2D 5D QD KD 7D";
        String p2 = "1S 4C KH TD 3S";
        Main.main(new String[]{p1, p2});
        assertEquals("P1", Main.result);
    }

    @Test
    public void p1F_p2P() {
        String p1 = "2D 5D QD KD 7D";
        String p2 = "1S 4C 1H TD 3S";
        Main.main(new String[]{p1, p2});
        assertEquals("P1", Main.result);
    }

    @Test
    public void p1F_p2F() {
        String p1 = "2D 5D QD KD 7D";
        String p2 = "1H 4H JH TH 3H";
        Main.main(new String[]{p1, p2});
        assertEquals("P1", Main.result);
    }

    // P2 Wins

    @Test
    public void p2F_p1H() {
        String p1 = "1S 4C KH TD 3S";
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[]{p1, p2});
        assertEquals("P2", Main.result);
    }

    @Test
    public void p2F_p1P() {
        String p1 = "1S 4C 1H TD 3S";
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[]{p1, p2});
        assertEquals("P2", Main.result);
    }

    @Test
    public void p2F_p1F() {
        String p1 = "1H 4H JH TH 3H";
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[]{p1, p2});
        assertEquals("P2", Main.result);
    }

    // Tie cases

    @Test
    public void tie_F() {
        String p1 = "3H 8H JH KH 7H";
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[]{p1, p2});
        assertEquals("TIE", Main.result);
    }

    @Test
    public void tie_P() {
        String p1 = "1S 4C 1H TD 3S";
        String p2 = "1C 5H 1D KS 7C";
        Main.main(new String[]{p1, p2});
        assertEquals("TIE", Main.result);
    }

    @Test
    public void tie_H() {
        String p1 = "1S 4D JH TD 3H";
        String p2 = "2D 5S JD 3D 7D";
        Main.main(new String[]{p1, p2});
        assertEquals("TIE", Main.result);
    }

    // Error cases

    @Test
    public void tooManyCards() {
        String p1 = "3H 8H JH KH 7H 7C";
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[]{p1, p2});
        assertEquals("ERROR", Main.result);
    }

    @Test
    public void notEnoughCards() {
        String p1 = "3H 8H JH KH 7H";
        String p2 = "2D 5D QD KD";
        Main.main(new String[]{p1, p2});
        assertEquals("ERROR", Main.result);
    }

    @Test
    public void unknownValue() {
        String p1 = "3H XH JH KH 7H";
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[]{p1, p2});
        assertEquals("ERROR", Main.result);
    }

    @Test
    public void unknownSuit() {
        String p1 = "3H 8H JH KH 7H";
        String p2 = "2D 5D QX KD 7D";
        Main.main(new String[]{p1, p2});
        assertEquals("ERROR", Main.result);
    }

    // Extension : Detect cheating

    @Ignore
    @Test
    public void sameCardTwice() {
        String p1 = "3H 8H JH KH 7H";
        String p2 = "2D 8H QH KD 7D";
        Main.main(new String[]{p1, p2});
        assertEquals("ERROR", Main.result);
    }

}
