package uqam.inf5153.poker;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {

    private String winner;
    // problem of ties
    // problem of loosers

    public Dealer(){};
    public Player findWinner(ArrayList<Player> players) {

        ArrayList<Ranking> rankings = new ArrayList<>(); // hold the ranking of each player
        for(Player player: players) {
            rankings.add(player.getPokerCombination());
        }
        Ranking highestRank = this.findHighestHand(rankings);

        for(Player player: players) {
            if(player.getPokerCombination() == highestRank){
                this.winner = player.getName();
                return player;
            }
        }
        return null; // comeback!!!
    }

    // Score
    public Player addToScore(Player player) {
        int score = player.getScore() + 2;
        player.setScore(score);
        return player;
    }

    private Ranking findHighestHand(ArrayList<Ranking> rankings) {
        return Collections.max(rankings);
    }

    public String tellWinner(){
        return this.winner;
    }

}