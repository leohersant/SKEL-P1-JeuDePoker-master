package uqam.inf5153.poker;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {


    public Dealer() {

    }

    public Ranking tellWinner(ArrayList<String> listOfPokerHands) {

        ArrayList<Ranking> handsPlaying = new ArrayList<>();

        for(String pokerHand: listOfPokerHands){

            handsPlaying.add(strToRanking(pokerHand));
        }
        return Collections.max(handsPlaying);
    }

    private Ranking strToRanking(String pokerHand){
        switch (pokerHand) {
            case "highCard":
                return Ranking.highCard;
            case "onePair":
                return Ranking.onePair;
            case "twoPair":
                return Ranking.twoPair;
            case "threeOfAKind":
                return Ranking.threeOfAKind;
            case "straight":
                return Ranking.straight;
            case "flush":
                return Ranking.flush;
            case "fullHouse":
                return Ranking.fullHouse;
            case "fourOfAKind":
                return Ranking.fourOfAKind;
            case "straightFlush":
                return Ranking.straightFlush;
            case "royalFlush":
                return Ranking.royalFlush;
            default:
                return Ranking.highCard;
        }
    }

}
/*
* , , , , ,
    , , , ,
*
* */