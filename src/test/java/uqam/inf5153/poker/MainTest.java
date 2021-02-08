package uqam.inf5153.poker;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MainTest {


    @Before public void initResult() { Main.result = null; }

    @Test public void CardEquals(){
        Card c1 = new Card(Value.TWO, Shape.S);
        Card c2 = new Card(Value.TWO, Shape.S);
        if (c1.equals(c2)){
            assertEquals(1, 1);
        } else{
            assertEquals(1, 2);
        }
    }

    @Test public void CardHash(){
        Card c1 = new Card(Value.TWO, Shape.S);
        Card c2 = new Card(Value.TWO, Shape.S);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test public void flush(){
        CardHand pokerHand = new CardHand("2C 4C 6C JC 8C");
        PokerCombination combination = new PokerCombination();
        boolean isAFlush = combination.flush(pokerHand);
        assertEquals(true, isAFlush);
    }

    @Test public void notFlush(){
        CardHand pokerHand = new CardHand("2C 4C 6D JH 8S");
        PokerCombination combination = new PokerCombination();
        boolean isAFlush = combination.flush(pokerHand);
        assertEquals(false, isAFlush);
    }

    @Test public void straightFlush(){
        CardHand pokerHand = new CardHand("TH JH QH KH AH");
        PokerCombination combination = new PokerCombination();
        boolean isAStraightFlush = combination.straight(pokerHand);
        assertTrue(isAStraightFlush);
    }

    @Test public void straight(){
        CardHand pokerHand = new CardHand("TC JC QD KH AS");
        PokerCombination combination = new PokerCombination();
        boolean isAStraight = combination.straight(pokerHand);
        assertTrue(isAStraight);
    }

    @Test public void notStraight(){
        CardHand pokerHand = new CardHand("2C 4C 6D JH 8S");
        PokerCombination combination = new PokerCombination();
        boolean isAStraight = combination.straight(pokerHand);
        assertEquals(false, isAStraight);
    }

/*
    @Test public void fourOfAKind(){
        CardHand pokerHand = new CardHand("QH QS QD QC 5D");
        PokerCombination combination = new PokerCombination();
        boolean isAquad = combination.fourOfAKind(pokerHand);
        assertTrue(isAquad);
    }
*/

    // P1 Wins

    @Test public void p1F_p2H() {
        String p1 = "2D 5D QD KD 7D";
        String p2 = "1S 4C KH TD 3S";
        Main.main(new String[] {p1, p2});
        assertEquals("P1", Main.result);
    }

    @Test public void p1F_p2P() {
        String p1 = "2D 5D QD KD 7D";
        String p2 = "1S 4C 1H TD 3S";
        Main.main(new String[] {p1, p2});
        assertEquals("P1", Main.result);
    }

    @Test public void p1F_p2F() {
        String p1 = "2D 5D QD KD 7D" ;
        String p2 = "1H 4H JH TH 3H" ;
        Main.main(new String[] {p1, p2});
        assertEquals("P1", Main.result);
    }

    // P2 Wins

    @Test public void p2F_p1H() {
        String p1 = "1S 4C KH TD 3S";
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[] {p1, p2});
        assertEquals("P2", Main.result);
    }

    @Test public void p2F_p1P() {
        String p1 = "1S 4C 1H TD 3S" ;
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[] {p1, p2});
        assertEquals("P2", Main.result);
    }

    @Test public void p2F_p1F() {
        String p1 = "1H 4H JH TH 3H";
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[] {p1, p2});
        assertEquals("P2", Main.result);
    }

    // Tie cases

    @Test public void tie_F() {
        String p1 = "3H 8H JH KH 7H";
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[] {p1, p2});
        assertEquals("TIE", Main.result);
    }

    @Test public void tie_P() {
        String p1 = "1S 4C 1H TD 3S" ;
        String p2 = "1C 5H 1D KS 7C";
        Main.main(new String[] {p1, p2});
        assertEquals("TIE", Main.result);
    }

    @Test public void tie_H() {
        String p1 = "1S 4D JH TD 3H";
        String p2 = "2D 5S JD 3D 7D";
        Main.main(new String[] {p1, p2});
        assertEquals("TIE", Main.result);
    }

    // Error cases

    @Test public void tooManyCards() {
        String p1 = "3H 8H JH KH 7H 7C";
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[] {p1, p2});
        assertEquals("ERROR", Main.result);
    }

    @Test public void notEnoughCards() {
        String p1 = "3H 8H JH KH 7H";
        String p2 = "2D 5D QD KD";
        Main.main(new String[] {p1, p2});
        assertEquals("ERROR", Main.result);
    }

    @Test public void unknownValue() {
        String p1 = "3H XH JH KH 7H";
        String p2 = "2D 5D QD KD 7D";
        Main.main(new String[] {p1, p2});
        assertEquals("ERROR", Main.result);
    }

    @Test public void unknownSuit() {
        String p1 = "3H 8H JH KH 7H";
        String p2 = "2D 5D QX KD 7D";
        Main.main(new String[] {p1, p2});
        assertEquals("ERROR", Main.result);
    }

    // Extension : Detect cheating

    @Ignore
    @Test public void sameCardTwice() {
        String p1 = "3H 8H JH KH 7H";
        String p2 = "2D 8H QH KD 7D";
        Main.main(new String[] {p1, p2});
        assertEquals("ERROR", Main.result);
    }

}
