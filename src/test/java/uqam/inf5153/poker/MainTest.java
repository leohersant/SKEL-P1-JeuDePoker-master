package uqam.inf5153.poker;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class MainTest {

    /*
    @Before
    public void initResult() {
        Main.result = null;
    }
*/
    @Test
    public void CardEquals() {
        Card c1 = new Card(Value.TWO, Color.S);
        Card c2 = new Card(Value.TWO, Color.S);
        if (c1.equals(c2)) {
            assertEquals(1, 1);
        } else {
            assertEquals(1, 2);
        }
    }

    @Test
    public void CardHash() {
        Card c1 = new Card(Value.TWO, Color.S);
        Card c2 = new Card(Value.TWO, Color.S);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    public void highestCard2Players() {

        PokerPlayer player1 = new PokerPlayer("P1", "1S 4C KH TD 3S"); // highest
        PokerPlayer player2 = new PokerPlayer("P2", "AH QS 6S 5D TC"); // highest wins

        ArrayList<PokerPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        PokerReferee pokerReferee = new PokerReferee(players);
        String temp = pokerReferee.getMessage();
        assertEquals("highCard pour P2 avec ACEH", temp);
    }

    @Test
    public void findTie2Players() {
        PokerPlayer player1 = new PokerPlayer("P1", "2C 4C 6C JC 8C"); // flush
        PokerPlayer player2 = new PokerPlayer("P2", "2C 4C 6C JC 8C"); // royalFlush

        ArrayList<PokerPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        PokerReferee pokerReferee = new PokerReferee(players);
        String temp = pokerReferee.getMessage();
        assertEquals("Égalité entre les joueurs P1 et P2 avec un flush.", temp);
    }

    @Test
    public void findTie3Players() {
        PokerPlayer player1 = new PokerPlayer("P1", "2C 4C 6C JC 8C"); // flush
        PokerPlayer player2 = new PokerPlayer("P2", "2C 4C 6C JC 8C"); // royalFlush
        PokerPlayer player3 = new PokerPlayer("P2", "2C 4C 6C JC 8C"); // royalFlush

        ArrayList<PokerPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        PokerReferee pokerReferee = new PokerReferee(players);
        String temp = pokerReferee.getMessage();

        Ranking a = player1.getPokerCombination();
        Ranking b = player1.getPokerCombination();
        Ranking c = player1.getPokerCombination();

        boolean x = false;
        if (a == b && b == c)
            x = true;
        assertTrue(x);
    }

    @Test
    public void findWinner2Players() {
        PokerPlayer player1 = new PokerPlayer("P1", "2C 4C 6C JC 8C"); // flush
        PokerPlayer player2 = new PokerPlayer("P2", "TH JH QH KH AH"); // royalFlush

        ArrayList<PokerPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        PokerReferee pokerReferee = new PokerReferee(players);
        //referee.verdict(players);
        String temp = pokerReferee.getMessage();
        assertEquals("royalFlush pour P2 bat flush pour P1.", temp);
    }

    @Test
    public void findWinner3Players() {
        PokerPlayer player1 = new PokerPlayer("P1", "2C 4C 6C JC 8C"); // flush looser
        PokerPlayer player2 = new PokerPlayer("P2", "TH JH QH KH AH"); // royalFlush winner
        PokerPlayer player3 = new PokerPlayer("P2", "TH JH QH KH AH"); // winner

        ArrayList<PokerPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        PokerReferee pokerReferee = new PokerReferee(players);

        boolean x = false;
        if (player2.getPokerCombination() == Ranking.royalFlush && player2.getPokerCombination() == Ranking.royalFlush) {
            x = true;
        }

        assertTrue(x);
    }


    @Test
    public void scoreTie2Players() {
        PokerPlayer player1 = new PokerPlayer("P1", "2C 4C 6C JC 8C"); // flush
        PokerPlayer player2 = new PokerPlayer("P2", "2C 4C 6C JC 8C"); // royalFlush

        ArrayList<PokerPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        PokerReferee pokerReferee = new PokerReferee(players);
        assertEquals(player1.getScore(), player2.getScore());
    }

    @Test
    public void scoreWinner2Players() {
        PokerPlayer player1 = new PokerPlayer("P1", "2C 4C 6C JC 8C"); // flush
        PokerPlayer player2 = new PokerPlayer("P2", "TH JH QH KH AH"); // royalFlush

        ArrayList<PokerPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        PokerReferee pokerReferee = new PokerReferee(players);
        assertEquals(1, player2.getScore());
    }


    @Test
    public void DetectAFlush() {
        CardHand pokerHand = new CardHand("2C 4C 6C JC 8C");
        PokerCombination combination = new PokerCombination();
        Ranking isAFlush = combination.detect(pokerHand);
        assertEquals(Ranking.flush, isAFlush);
    }

    /* Principe de substitution de Liskov: DetectRoyalFlush et DetectRoyalFlush2 */
    @Test
    public void DetectRoyalFlush() {
        CardHand pokerHand = new CardHand("TH JH QH KH AH");
        PokerCombination combination = new PokerCombination();
        Ranking isARoyalFlush = combination.detect(pokerHand);
        assertEquals(Ranking.royalFlush, isARoyalFlush);
    }

    @Test
    public void DetectRoyalFlush2() {
        PokerCombination combination = new PokerCombination("TH JH QH KH AH");
        Ranking isARoyalFlush = combination.detect(combination);
        assertEquals(Ranking.royalFlush, isARoyalFlush);
    }

    @Test
    public void DetectHighCard() {
        PokerCombination combination = new PokerCombination("QS AH 5D TC 6S");
        Ranking highCard = (combination.detect(combination));
        assertEquals(Ranking.highCard, highCard);
    }

    @Test
    public void DetectAFlushErreur() {
        CardHand pokerHand = new CardHand("2C 4C 6C JC");
        PokerCombination combination = new PokerCombination();
        Ranking isAFlush = combination.detect(pokerHand);
        assertEquals(Ranking.flush, isAFlush);
    }

    // P1 Wins
/*
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
*/
}
