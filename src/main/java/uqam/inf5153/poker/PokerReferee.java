package uqam.inf5153.poker;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PokerReferee {

    private String message; // tell winner/tie
    private ArrayList<PokerPlayer> players;

    public PokerReferee(ArrayList<PokerPlayer> players) {
        Ranking highestRank = getHighestHand(getAllCombinations(players));
        if (highestRank == Ranking.highCard) {
            Value key = Collections.max(getHighestCardValues(players));
            this.message = buildWinnerHighCardMessage(players, key);
        } else if (tie(players)) {
            this.message = buildTieMessage(players);
            this.players = addToScore(players);
        } else {
            ArrayList winners = findWinners(players, highestRank);
            this.message = buildWinnerMessage(winners, findLoosers(players, highestRank));
            this.players = addToScore(winners);
        }
    }

    public String getMessage() {
        return this.message;
    }
    public ArrayList<PokerPlayer> getPlayers() {
        return players;
    }


    private ArrayList<Ranking> getAllCombinations(ArrayList<PokerPlayer> players) {
        ArrayList<Ranking> pokerCombinations = new ArrayList<>(); // it holds the ranking of each player
        for (PokerPlayer player : players)
            pokerCombinations.add(player.getPokerCombination());
        return pokerCombinations;
    }

    private Ranking getHighestHand(ArrayList<Ranking> rankings) {
        return Collections.max(rankings);
    }

    /*
     *  Find who are the loosers with less than highest combination
     */
    private ArrayList<PokerPlayer> findLoosers(ArrayList<PokerPlayer> players, Ranking highestRank) {
        ArrayList<PokerPlayer> playerList = new ArrayList<>();
        for (PokerPlayer player : players) {
            if (player.getPokerCombination() != highestRank) {
                playerList.add(player);
            }
        }
        return playerList;
    }

    /*
     *  Find who are the winners with the highest combination
     *  The bigger pokerhand always win...     W : win, L : loose, T : tie
     *  W L L (one winner, two loosers)
     *  T T L (two winners, one looser)
     *  W T T (one winner, two loosers)
     */
    private ArrayList<PokerPlayer> findWinners(ArrayList<PokerPlayer> players, Ranking highestRank) {
        ArrayList<PokerPlayer> playerList = new ArrayList<>();
        for (PokerPlayer player : players) {
            if (player.getPokerCombination() == highestRank) {
                playerList.add(player);
            }
        }
        return playerList;
    }

    private boolean tie(ArrayList<PokerPlayer> players) {
        ArrayList<Ranking> results = new ArrayList<>();
        for (PokerPlayer player : players)
            results.add(player.getPokerCombination());
        return Collections.frequency(results, players.get(0).getPokerCombination()) == players.size();
    }

    private String buildTieMessage(ArrayList<PokerPlayer> players) {
        StringBuilder msg = new StringBuilder("Égalité entre les joueurs ");
        for (PokerPlayer player : players)
            msg.append(player.getName()).append(" et ");
        msg = new StringBuilder(msg.substring(0, msg.length() - 4));
        msg.append(" avec un ").append(players.get(0).getPokerCombination()).append(".");
        return msg.toString();
    }

    // "couleur à trèfle pour J1 bat une paire de rois pour J2"
    private String buildWinnerMessage(ArrayList<PokerPlayer> winners, ArrayList<PokerPlayer> loosers) {
        return winnersMsg(winners) + " bat " + loosersMsg(loosers);
    }

    private String winnersMsg(ArrayList<PokerPlayer> winners) {
        StringBuilder msg = new StringBuilder();
        for (PokerPlayer winner : winners)
            msg.append(winner.getPokerCombination()).append(" pour ").append(winner.getName()).append(", ");
        return (msg.substring(0, msg.length() - 2));
    }

    private String loosersMsg(ArrayList<PokerPlayer> loosers) {
        StringBuilder msg = new StringBuilder();
        for (PokerPlayer looser : loosers)
            msg.append(looser.getPokerCombination()).append(" pour ").append(looser.getName()).append(", ");
        return (msg.substring(0, msg.length() - 2) + ".");
    }

    public ArrayList<PokerPlayer> addToScore(ArrayList<PokerPlayer> players) {
        for (Player player : players) {
            AtomicInteger score = new AtomicInteger(player.getScore());
            score.getAndIncrement();
            player.setScore(score.get());
        }
        return players;
    }

    private ArrayList<Value> getHighestCardValues(ArrayList<PokerPlayer> players) {
        ArrayList<Value> highestValues = new ArrayList<>();
        for (PokerPlayer player : players) {
            Card highestCard = player.getHighestCard();
            highestValues.add(highestCard.getValue());
        }
        return highestValues;
    }

    private String buildWinnerHighCardMessage(ArrayList<PokerPlayer> players, Value max) {
        for (PokerPlayer player : players) {
            if (player.getHighestCard().getValue() == max) {
                return (player.getPokerCombination() + " pour " + player.getName() + " avec " + player.getHighestCard().toString());
            }
        }
        return null;
    }

}